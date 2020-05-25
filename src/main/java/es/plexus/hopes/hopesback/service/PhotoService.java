package es.plexus.hopes.hopesback.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import es.plexus.hopes.hopesback.controller.model.photo.PhotoDTO;
import es.plexus.hopes.hopesback.controller.model.photo.PhotoViewDTO;
import es.plexus.hopes.hopesback.repository.PhotoRepository;
import es.plexus.hopes.hopesback.repository.model.Pathology;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.Photo;
import es.plexus.hopes.hopesback.repository.model.User;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.PhotoMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class PhotoService {

	private final PhotoMapper photoMapper;
	private final PhotoRepository photoRepository;
	private final PathologyService pathologyService;
	private final PatientService patientService;
	private final UserService userService;

	@Value("${imagegallery.url}")
	private String imageGalleryUrl;

	@Autowired
	public PhotoService(final PhotoMapper photoMapper, final PhotoRepository photoRepository,
						final PathologyService pathologyService, final PatientService patientService,
						final UserService userService) {
		this.photoMapper = photoMapper;
		this.photoRepository = photoRepository;
		this.pathologyService = pathologyService;
		this.patientService = patientService;
		this.userService = userService;
	}

	@Transactional
	public List<PhotoViewDTO> getAllPhotos(Long pacId, Long pthId) throws ServiceException {
		if (pacId == null && pthId == null) {
			final String message = String.format("El identificador de paciente id:%o o patología son incorrectos id:%o",
					pacId, pthId);
			throw ServiceExceptionCatalog.INVALID_REQUEST_EXCEPTION.exception(message);
		}

		log.debug("Llamando a la BD para recuperar todas las fotos con id de paciente = " + pacId +
				" y id de patología = " + pthId);
		List<Photo> photos = photoRepository.findAllByPatientAndPathology(pthId, pacId);
		return photos.stream().map(photoMapper::photoToPhotoViewDTOConverter).collect(Collectors.toList());
	}

	public PhotoViewDTO getOnePhotoById(final Long id) {
		log.debug("Llamando a la BD para recuperar la foto con id = " + id);
		final Optional<Photo> photo = getEntityPhotoById(id);
		return photo.map(photoMapper::photoToPhotoViewDTOConverter).orElse(null);
	}

	private Optional<Photo> getEntityPhotoById(Long id) {
		return photoRepository.findById(id);
	}

	public void deleteAllPhotos() {
		log.debug("Llamando a la BD para eliminar todas las fotos");
		photoRepository.deleteAll();
	}

	public void deletePhotoById(final Long id) throws ServiceException {
		validatePhotoExistence(id);

		log.debug("Llamando a la BD para eliminar la foto con id = " + id);
		photoRepository.deleteById(id);
	}

	private void validatePhotoExistence(Long id) throws ServiceException {
		Optional<Photo> photo = getEntityPhotoById(id);

		if (!photo.isPresent()) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
					.exception("Foto no encontrada con id:" + id);
		}
	}

	@Transactional
	public PhotoViewDTO addPhoto(final PhotoDTO photoDTO) throws ServiceException {
		Photo photo = photoMapper.photoDTOToPhotoConverter(photoDTO);

		if (photoDTO.getPathologyId() != null) {
			photo.setPathology(resolvePathology(photoDTO.getPathologyId()));
		}

		if (photoDTO.getPatientId() != null) {
			photo.setPatient(resolvePatient(photoDTO.getPatientId()));
		}

		if (photoDTO.getUserId() != null) {
			photo.setUserName(resolveUser(photoDTO).getUsername());
		}

		log.debug("Llamando a la BD para guardar una nueva foto");
		photo = photoRepository.save(photo);

		return photoMapper.photoToPhotoViewDTOConverter(photo);
	}

	public Pathology resolvePathology(Long id) {
		Optional<Pathology> pathology = pathologyService.getOnePathologyById(id);
		return pathology.orElse(null);
	}

	public Patient resolvePatient(Long id) throws ServiceException {
		Optional<Patient> patient = patientService.getEntityPatientById(id);
		return patient.orElse(null);
	}

	private User resolveUser(PhotoDTO photoDTO) {
		Optional<User> user = userService.getOneUserCommon(photoDTO.getUserId());
		return user.orElse(null);
	}

	@Transactional
	public PhotoViewDTO updatePhoto(final PhotoDTO photoDTO) throws ServiceException {
		validatePhotoExistence(photoDTO.getId());
		Photo photo = photoMapper.photoDTOToPhotoConverter(photoDTO);
		checkPhotoChanges(photoDTO, photo);

		log.debug("Llamando a la BD para actualizar una nueva foto");
		photo = photoRepository.save(photo);

		return photoMapper.photoToPhotoViewDTOConverter(photo);
	}

	private void checkPhotoChanges(PhotoDTO photoDTO, Photo photo) throws ServiceException {
		final Optional<Photo> storedPhoto = getEntityPhotoById(photoDTO.getId());

		if (storedPhoto.isPresent()) {
			if (photoDTO.getTitle() == null) {
				photo.setTitle(storedPhoto.get().getTitle());
			}
			if (photoDTO.getDescription() == null) {
				photo.setDescription(storedPhoto.get().getDescription());
			}
			if (photoDTO.getPathologyId() == null) {
				photo.setPathology(storedPhoto.get().getPathology());
			} else {
				photo.setPathology(resolvePathology(photoDTO.getPathologyId()));
			}
			if (photoDTO.getPatientId() == null) {
				photo.setPatient(storedPhoto.get().getPatient());
			} else {
				photo.setPatient(resolvePatient(photoDTO.getPatientId()));
			}
			if (photoDTO.getUserId() == null) {
				photo.setUserName(storedPhoto.get().getUserName());
			} else {
				photo.setUserName(resolveUser(photoDTO).getUsername());
			}
			if (photo.getName() == null) {
				photo.setName(storedPhoto.get().getName());
			}
			if (photo.getPhotoBytes() == null) {
				photo.setPhotoBytes(storedPhoto.get().getPhotoBytes());
			}
			if (photo.getTypePhoto() == null) {
				photo.setTypePhoto(storedPhoto.get().getTypePhoto());
			}
		}
	}

	public String generateQRCodeImage(final Long pacId, final Long pthId, final String token,
									  final HttpServletResponse response) throws Exception {

		//ToDO: llamar al servicio para generar el nuevo token con los id's configurados
		final String endPoint = imageGalleryUrl.concat("?token=").concat(token.replace("Bearer ", ""));

		final ByteArrayOutputStream byteArrayOutputStream = generateQR(endPoint);
		byteArrayOutputStream.flush();

		byte[] bytes = byteArrayOutputStream.toByteArray();
		byteArrayOutputStream.close();

		response.setContentType(MediaType.IMAGE_PNG_VALUE);

		return Base64.getEncoder().encodeToString(bytes);
	}

	private ByteArrayOutputStream generateQR(String endPoint) throws WriterException, IOException {
		final QRCodeWriter barcodeWriter = new QRCodeWriter();

		final BitMatrix bitMatrix = barcodeWriter.encode(endPoint, BarcodeFormat.QR_CODE, 250, 250);

		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);

		return byteArrayOutputStream;
	}
}
