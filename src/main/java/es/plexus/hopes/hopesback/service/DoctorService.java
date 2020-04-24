package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.repository.DoctorRepository;
import es.plexus.hopes.hopesback.repository.DoctorSpecificationsBuilder;
import es.plexus.hopes.hopesback.repository.model.Doctor;
import es.plexus.hopes.hopesback.repository.model.User;
import es.plexus.hopes.hopesback.service.mapper.DoctorDTOMapper;
import es.plexus.hopes.hopesback.service.mapper.DoctorMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Optional;

@Service
public class DoctorService {

	private static final Logger LOGGER = LogManager.getLogger(DoctorService.class);

	private final UserService userService;
	private final DoctorMapper doctorMapper;
	private final ServiceService serviceService;
	private final DoctorDTOMapper doctorDTOMapper;
	private final DoctorRepository doctorRepository;

	@Autowired
	public DoctorService(final UserService userService, final DoctorMapper doctorMapper, final ServiceService serviceService,
						 final DoctorDTOMapper doctorDTOMapper, final DoctorRepository doctorRepository) {
		this.userService = userService;
		this.doctorMapper = doctorMapper;
		this.serviceService = serviceService;
		this.doctorDTOMapper = doctorDTOMapper;
		this.doctorRepository = doctorRepository;
	}

	public Page<DoctorDTO> getAllDoctors(final Pageable pageable) {
		LOGGER.debug("Calling DB...");
		Page<Doctor> doctorList = doctorRepository.findAll(pageable);

		return doctorList.map(doctorMapper::doctorToDoctorDTOConverter);
	}

	public DoctorDTO getOneDoctor(final Long id) {
		final Optional<Doctor> doctor = getOneDoctorCommon(id);

		DoctorDTO doctorDTO = null;

		if (doctor.isPresent()) {
			doctorDTO = doctorMapper.doctorToDoctorDTOConverter(doctor.get());
		} else {
			LOGGER.debug(String.format("Doctor with id = %s not found ...", id));
		}

		return doctorDTO;
	}

	public Page<DoctorDTO> findDoctorsBySearch(final String search, final Pageable pageable) {
		LOGGER.debug("Calling DB...");
		Page<Doctor> doctorList = doctorRepository.findDoctorsBySearch(search, pageable);

		return doctorList.map(doctorMapper::doctorToDoctorDTOConverter);
	}

	public Page<DoctorDTO> filterDoctors(final String doctor, final Pageable pageable) {
		final DoctorDTO doctorDTO = doctorDTOMapper.jsonToDoctorDTOConventer(doctor);

		LOGGER.debug("Check DTO...");
		final DoctorSpecificationsBuilder builder = checkFieldsNull(doctorDTO);
		final Specification<Doctor> spec = builder.build();

		LOGGER.debug("Calling DB...");
		final Page<Doctor> doctorList = doctorRepository.findAll(spec, pageable);

		return doctorList.map(doctorMapper::doctorToDoctorDTOConverter);
	}

	public DoctorDTO addDoctor(final DoctorDTO doctorDTO) {
		Doctor doctor = addDoctorCommon(doctorDTO);
		LOGGER.debug("Calling DB...");
		doctor = doctorRepository.save(doctor);

		return doctorMapper.doctorToDoctorDTOConverter(doctor);
	}

	public DoctorDTO updateDoctor(final DoctorDTO doctorDTO) {
		final Optional<Doctor> storedDoctor = getOneDoctorCommon(doctorDTO.getId());

		Doctor doctor = addDoctorCommon(doctorDTO);

		//ToDo Hasta el 23-04-2020 solo se puede desactivar un usuario por BD
		if (storedDoctor.isPresent()) {
			doctor.setActive(storedDoctor.get().getActive());
		}

		LOGGER.debug("Calling DB...");
		doctor = doctorRepository.save(doctor);

		return doctorMapper.doctorToDoctorDTOConverter(doctor);
	}

	public void deleteDoctor(final Long id) {
		LOGGER.debug("Calling DB...");
		doctorRepository.deleteById(id);
	}

	private Optional<Doctor> getOneDoctorCommon(Long id) {
		LOGGER.debug(String.format("Calling DB with id = %d ...", id));
		return doctorRepository.findById(id);
	}

	private DoctorSpecificationsBuilder checkFieldsNull(DoctorDTO doctorDTO) {
		final DoctorSpecificationsBuilder builder = new DoctorSpecificationsBuilder();

		try {
			for (Field f : doctorDTO.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				if (f.get(doctorDTO) != null) {
					builder.with(f.getName(), f.get(doctorDTO));
				}
			}
		} catch (Exception e) {
			throw new ServiceException("Error chequeando los valores a filtrar" + e.getMessage());
		}

		return builder;
	}

	private Doctor addDoctorCommon(DoctorDTO doctorDTO) {
		final User user = userService.addUserAndReturnEntity(doctorDTO.getUser());
		final Optional<es.plexus.hopes.hopesback.repository.model.Service> service = serviceService
				.getOneServiceById(doctorDTO.getService().getId());

		if (!service.isPresent()) {
			throw new ServiceException(
					String.format("Service with id %d not found. Service is mandatory for the doctor",
							doctorDTO.getService().getId()));
		}

		Doctor doctor = doctorMapper.doctorDTOToDoctorConverter(doctorDTO);
		doctor.setUser(user);
		doctor.setService(service.get());

		return doctor;
	}
}
