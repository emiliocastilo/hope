package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.controller.model.DoctorUpdateDTO;
import es.plexus.hopes.hopesback.controller.model.DoctorViewDTO;
import es.plexus.hopes.hopesback.controller.model.ServiceDTO;
import es.plexus.hopes.hopesback.controller.model.UserUpdateDTO;
import es.plexus.hopes.hopesback.repository.DoctorRepository;
import es.plexus.hopes.hopesback.repository.model.Doctor;
import es.plexus.hopes.hopesback.repository.model.User;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.DoctorDTOMapper;
import es.plexus.hopes.hopesback.service.mapper.DoctorMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.COLLEGE_NUMBER_VIOLATION_CONSTRAINT_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.DNI_VIOLATION_CONSTRAINT_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.EMAIL_VIOLATION_CONSTRAINT_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.PHONE_VIOLATION_CONSTRAINT_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.USERNAME_DUPLICATE_MESSAGE;

@Log4j2
@Service
public class DoctorService {

	private final UserService userService;
	private final DoctorMapper doctorMapper;
	private final ServiceService serviceService;
	private final DoctorRepository doctorRepository;
	private final PasswordManagementService passwordManagementService;

	@Autowired
	public DoctorService(final UserService userService, final DoctorMapper doctorMapper, final ServiceService serviceService,
						 final DoctorRepository doctorRepository, final PasswordManagementService passwordManagementService) {
		this.userService = userService;
		this.doctorMapper = doctorMapper;
		this.serviceService = serviceService;
		this.doctorRepository = doctorRepository;
		this.passwordManagementService = passwordManagementService;
	}

	public Page<DoctorViewDTO> getAllDoctors(final Pageable pageable) {
		log.debug("Llamando a la BD para recuperar todos los registros de médicos...");
		Page<Doctor> doctorList = doctorRepository.findAll(pageable);

		return doctorList.map(doctorMapper::doctorToDoctorDTOConverter);
	}

	public DoctorViewDTO getOneDoctor(final Long id) {
		final Optional<Doctor> doctor = getOneDoctorCommon(id);

		DoctorViewDTO doctorDTO = null;

		if (doctor.isPresent()) {
			doctorDTO = doctorMapper.doctorToDoctorDTOConverter(doctor.get());
		} else {
			log.debug(String.format("Médico con id=%s no encontrado...", id));
		}

		return doctorDTO;
	}

	private Optional<Doctor> getOneDoctorCommon(Long id) {
		log.debug(String.format("Llamando a la BD para buscar el registro en médicos con id=%d...", id));
		return doctorRepository.findById(id);
	}

	public Page<DoctorViewDTO> findDoctorsBySearch(final String search, final Pageable pageable) {
		log.debug(String.format("Llamando a la BD para buscar el registro de médico filtrado por %s...", search));
		Page<Doctor> doctorList = doctorRepository.findDoctorsBySearch(search, pageable);

		return doctorList.map(doctorMapper::doctorToDoctorDTOConverter);
	}

	public Page<DoctorViewDTO> filterDoctors(final String doctor, final Pageable pageable) {
		DoctorDTO doctorDTO = DoctorDTOMapper.INSTANCE.jsonToDoctorDTOConventer(doctor);
		log.debug("Comprobando DTO...");

		ExampleMatcher matcher = ExampleMatcher.matchingAll().
				withIgnoreCase(true).withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Doctor doctorEx = doctorMapper.doctorDTOToDoctorConverter(doctorDTO);

		Example<Doctor> spec = Example.of(doctorEx, matcher);

		Page<Doctor> doctorList = doctorRepository.findAll(spec, pageable);

		return doctorList.map(doctorMapper::doctorToDoctorDTOConverter);
	}

	public void deleteDoctor(final Long id) {
		log.debug(String.format("Llamando a la BD para borrar el registro de médicos con id=%d...", id));
		doctorRepository.deleteById(id);
	}

	@Transactional
	public DoctorViewDTO addDoctor(final DoctorDTO doctorDTO) throws ServiceException {
		checkServiceExistence(doctorDTO.getServiceDTO());

		Doctor doctor = doctorMapper.doctorDTOToDoctorConverter(doctorDTO);

		final User user = userService.addUserAndReturnEntity(doctorDTO.getUserDTO());
		final String password = userService.setGeneratedPasswordForUser(user);
		doctor.setUser(user);

		log.debug("Llamando a la BD para añadir un nuevo registro de médico...");
		validateAddDoctor(doctor);
		doctor = doctorRepository.save(doctor);

		userService.sendCredentialsEmail(user, password);

		return doctorMapper.doctorToDoctorDTOConverter(doctor);
	}

	private void checkServiceExistence(ServiceDTO serviceDTO) throws ServiceException {
		if (serviceDTO != null && serviceDTO.getId() != null) {
			final Optional<es.plexus.hopes.hopesback.repository.model.Service> service = serviceService
					.getOneServiceById(serviceDTO.getId());

			if (!service.isPresent()) {
				throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(
						String.format("Servicio con el id %d no encontrado. El servicio es requerido en médicos",
								serviceDTO.getId()));
			}
		}
	}

	@Transactional
	public DoctorViewDTO updateDoctor(final DoctorUpdateDTO doctorDTO) throws ServiceException {
		checkServiceExistence(doctorDTO.getServiceDTO());

		final Doctor storedDoctor = getOneDoctorCommon(doctorDTO.getId()).orElse(null);

		if (storedDoctor == null) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(
					String.format("Servicio con el id %d no encontrado.",
							doctorDTO.getId()));
		}

		Doctor doctor = doctorMapper.doctorUpdateDTOToDoctorConverter(doctorDTO);
		checkDoctorChanges(doctorDTO, storedDoctor, doctor);
		checkRelationshipData(doctorDTO, storedDoctor, doctor);
		validateUpdateDoctor(storedDoctor, doctor);
		log.debug("Llamando a la BD para actualizar un nuevo registro de médico...");
		doctor = doctorRepository.save(doctor);

		return doctorMapper.doctorToDoctorDTOConverter(doctor);
	}

	private void checkDoctorChanges(DoctorUpdateDTO doctorUpdateDTO, Doctor storedDoctor, Doctor doctor) {
		if (doctorUpdateDTO.getName() == null) {
			doctor.setName(storedDoctor.getName());
		}

		if (doctorUpdateDTO.getSurname() == null) {
			doctor.setSurname(storedDoctor.getSurname());
		}

		if (doctorUpdateDTO.getPhone() == null) {
			doctor.setPhone(storedDoctor.getPhone());
		}

		if (doctorUpdateDTO.getDni() == null) {
			doctor.setDni(storedDoctor.getDni());
		}

		if (doctorUpdateDTO.getCollegeNumber() == null) {
			doctor.setCollegeNumber(storedDoctor.getCollegeNumber());
		}

		//ToDo Hasta el 23-04-2020 solo se puede desactivar un usuario por BD
		doctor.setActive(storedDoctor.getActive());
	}

	private void checkRelationshipData(DoctorUpdateDTO doctorUpdateDTO, Doctor storedDoctor, Doctor doctor)
			throws ServiceException {

		final UserUpdateDTO userUpdateDTO = doctorUpdateDTO.getUserDTO();
		final User storedUser = storedDoctor.getUser();

		if (userUpdateDTO != null) {
			doctor.setUser(userService.addUserAndReturnEntity(doctorUpdateDTO.getUserDTO()));

			User user = doctor.getUser();

			if (userUpdateDTO.getUsername() == null) {
				user.setUsername(storedUser.getUsername());
			}

			if (userUpdateDTO.getEmail() == null) {
				user.setUsername(storedUser.getEmail());
			}

			if (userUpdateDTO.getHospitalId() == null) {
				user.setHospital(storedUser.getHospital());
			}

			user.setRoles(storedUser.getRoles());

		} else {
			doctor.setUser(storedUser);
		}

		if (doctorUpdateDTO.getServiceDTO() == null) {
			doctor.setService(storedDoctor.getService());
		}else{
			doctor.setService(serviceService.getOneServiceById(doctorUpdateDTO.getServiceDTO().getId()).orElse(null));
		}
	}

	private void validateAddDoctor(Doctor doctor) {

		if (doctorRepository.existsByDni(doctor.getDni())){
			throw ServiceExceptionCatalog.DNI_VIOLATION_CONSTRAINT_EXCEPTION
					.exception(DNI_VIOLATION_CONSTRAINT_MESSAGE);
		}

		if(doctorRepository.existsByCollegeNumber(doctor.getCollegeNumber())) {
			throw ServiceExceptionCatalog.COLLEGE_NUMBER_VIOLATION_CONSTRAINT_EXCEPTION
					.exception(COLLEGE_NUMBER_VIOLATION_CONSTRAINT_MESSAGE);
		}

		if(doctorRepository.existsByPhone(doctor.getPhone())) {
			throw ServiceExceptionCatalog.PHONE_VIOLATION_CONSTRAINT_EXCEPTION
					.exception(PHONE_VIOLATION_CONSTRAINT_MESSAGE);
		}
	}

	private void validateUpdateDoctor(Doctor storedDoctor, Doctor updatedoctor) {
		if(!storedDoctor.getUser().getUsername().equals(updatedoctor.getUser().getUsername())
				&& userService.existUsername(updatedoctor.getUser().getUsername())){
			throw ServiceExceptionCatalog.USERNAME_DUPLICATE_EXCEPTION
					.exception(USERNAME_DUPLICATE_MESSAGE);
		}

		if(!storedDoctor.getUser().getEmail().equals(updatedoctor.getUser().getEmail())
				&& userService.existUserEmail(updatedoctor.getUser().getEmail())){
			throw ServiceExceptionCatalog.EMAIL_VIOLATION_CONSTRAINT_EXCEPTION
					.exception(EMAIL_VIOLATION_CONSTRAINT_MESSAGE);
		}

		if (!storedDoctor.getDni().equals(updatedoctor.getDni())
				&& doctorRepository.existsByDni(updatedoctor.getDni())){
			throw ServiceExceptionCatalog.DNI_VIOLATION_CONSTRAINT_EXCEPTION
					.exception(DNI_VIOLATION_CONSTRAINT_MESSAGE);
		}

		if(!storedDoctor.getCollegeNumber().equals(updatedoctor.getCollegeNumber())
				&& doctorRepository.existsByCollegeNumber(updatedoctor.getCollegeNumber())) {
			throw ServiceExceptionCatalog.COLLEGE_NUMBER_VIOLATION_CONSTRAINT_EXCEPTION
					.exception(COLLEGE_NUMBER_VIOLATION_CONSTRAINT_MESSAGE);
		}

		if(!storedDoctor.getPhone().equals(updatedoctor.getPhone())
				&& doctorRepository.existsByPhone(updatedoctor.getPhone())) {
			throw ServiceExceptionCatalog.PHONE_VIOLATION_CONSTRAINT_EXCEPTION
					.exception(PHONE_VIOLATION_CONSTRAINT_MESSAGE);
		}
	}

}
