package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
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

import java.util.Optional;

@Log4j2
@Service
public class DoctorService {

	private final UserService userService;
	private final DoctorMapper doctorMapper;
	private final ServiceService serviceService;
	private final DoctorRepository doctorRepository;

	@Autowired
	public DoctorService(final UserService userService, final DoctorMapper doctorMapper, final ServiceService serviceService,
						 final DoctorRepository doctorRepository) {
		this.userService = userService;
		this.doctorMapper = doctorMapper;
		this.serviceService = serviceService;
		this.doctorRepository = doctorRepository;
	}

	public Page<DoctorDTO> getAllDoctors(final Pageable pageable) {
		log.debug("Calling DB...");
		Page<Doctor> doctorList = doctorRepository.findAll(pageable);

		return doctorList.map(doctorMapper::doctorToDoctorDTOConverter);
	}

	public DoctorDTO getOneDoctor(final Long id) {
		final Optional<Doctor> doctor = getOneDoctorCommon(id);

		DoctorDTO doctorDTO = null;

		if (doctor.isPresent()) {
			doctorDTO = doctorMapper.doctorToDoctorDTOConverter(doctor.get());
		} else {
			log.debug(String.format("Doctor with id = %s not found ...", id));
		}

		return doctorDTO;
	}

	public Page<DoctorDTO> findDoctorsBySearch(final String search, final Pageable pageable) {
		log.debug("Calling DB...");
		Page<Doctor> doctorList = doctorRepository.findDoctorsBySearch(search, pageable);

		return doctorList.map(doctorMapper::doctorToDoctorDTOConverter);
	}

	public Page<DoctorDTO> filterDoctors(final String doctor, final Pageable pageable) {
		DoctorDTO doctorDTO = DoctorDTOMapper.INSTANCE.jsonToDoctorDTOConventer(doctor);
		log.debug("Check DTO...");

		ExampleMatcher matcher = ExampleMatcher.matchingAll().
				withIgnoreCase(true).withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Doctor doctorEx = doctorMapper.doctorDTOToDoctorConverter(doctorDTO);

		Example<Doctor> spec = Example.of(doctorEx, matcher);

		Page<Doctor> doctorList = doctorRepository.findAll(spec, pageable);

		return doctorList.map(doctorMapper::doctorToDoctorDTOConverter);
	}

	public DoctorDTO addDoctor(final DoctorDTO doctorDTO) throws ServiceException {
		Doctor doctor = addDoctorCommon(doctorDTO);
		log.debug("Calling DB...");
		doctor = doctorRepository.save(doctor);

		return doctorMapper.doctorToDoctorDTOConverter(doctor);
	}

	public DoctorDTO updateDoctor(final DoctorDTO doctorDTO) throws ServiceException {
		final Optional<Doctor> storedDoctor = getOneDoctorCommon(doctorDTO.getId());

		Doctor doctor = addDoctorCommon(doctorDTO);

		//ToDo Hasta el 23-04-2020 solo se puede desactivar un usuario por BD
		if (storedDoctor.isPresent()) {
			doctor.setActive(storedDoctor.get().getActive());
		}

		log.debug("Calling DB...");
		doctor = doctorRepository.save(doctor);

		return doctorMapper.doctorToDoctorDTOConverter(doctor);
	}

	public void deleteDoctor(final Long id) {
		log.debug("Calling DB...");
		doctorRepository.deleteById(id);
	}

	public Optional<Doctor> getOneDoctorCommon(Long id) {
		log.debug(String.format("Calling DB with id = %d ...", id));
		return doctorRepository.findById(id);
	}

	private Doctor addDoctorCommon(DoctorDTO doctorDTO) throws ServiceException {
		final User user = userService.addUserAndReturnEntity(doctorDTO.getUser());
		final Optional<es.plexus.hopes.hopesback.repository.model.Service> service = serviceService
				.getOneServiceById(doctorDTO.getService().getId());

		if (!service.isPresent()) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(
					String.format("Service with id %d not found. Service is mandatory for the doctor",
							doctorDTO.getService().getId()));
		}

		Doctor doctor = doctorMapper.doctorDTOToDoctorConverter(doctorDTO);
		doctor.setUser(user);
		doctor.setService(service.get());

		return doctor;
	}
}
