package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.photo.PhotoDTO;
import es.plexus.hopes.hopesback.controller.model.photo.PhotoViewDTO;
import es.plexus.hopes.hopesback.service.PhotoService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

@Api(value = "Controlador de fotos", tags = "photo")
@Log4j2
@RestController
@RequestMapping(PhotoController.PHOTO_MAPPING)
public class PhotoController {

	static final String PHOTO_MAPPING = "/photos";

	private final PhotoService photoService;

	@Autowired
	public PhotoController(final PhotoService photoService) {
		this.photoService = photoService;
	}

	@ApiOperation("Recuperar todas las fotos")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public List<PhotoViewDTO> findAllPhoto(
			@ApiParam(value = "identificador paciente", required = true) @RequestParam("pac") Long pacId,
			@ApiParam(value = "identificador patologia", required = true) @RequestParam("pth") Long pthId)
			throws ServiceException {

		return photoService.getAllPhotos(pacId, pthId);
	}

	@ApiOperation("Recuperar una foto por id")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public PhotoViewDTO findOnePhoto(@ApiParam(value = "identificador", required = true) @PathVariable Long id) {
		return photoService.getOnePhotoById(id);
	}

	@ApiOperation("Añadir una nueva foto")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public PhotoViewDTO addPhoto(@RequestBody final PhotoDTO photoDTO) throws ServiceException {
		return photoService.addPhoto(photoDTO);
	}

	@ApiOperation("Actualizar una foto")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public PhotoViewDTO updatePhoto(@RequestBody final PhotoDTO photoDTO) throws ServiceException {
		return photoService.updatePhoto(photoDTO);
	}

	@ApiOperation("Eliminar una foto por id")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteById(@ApiParam(value = "identificador", required = true) @PathVariable Long id)
			throws ServiceException {

		photoService.deletePhotoById(id);
	}

	@ApiOperation("Eliminar todas las fotos")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping()
	public void deleteAllPhotos() {
		photoService.deleteAllPhotos();
	}

	@ApiOperation("Generar código QR")
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(value = "/qr")
	public OutputStream generateQR(@ApiParam(value = "identificador paciente", required = true) @RequestParam("pac") Long pacId,
								   @ApiParam(value = "identificador patologia", required = true) @RequestParam("pth") Long pthId,
								   @RequestHeader(name = "Authorization") final String token,
								   final HttpServletResponse response) throws Exception {

		return photoService.generateQRCodeImage(pacId, pthId, token, response);
	}
}
