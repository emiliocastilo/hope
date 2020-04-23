package es.plexus.hopes.hopesback.service;

import java.lang.reflect.Field;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.repository.DoctorRepository;
import es.plexus.hopes.hopesback.repository.DoctorSpecificationsBuilder;
import es.plexus.hopes.hopesback.repository.model.Doctor;
import es.plexus.hopes.hopesback.service.mapper.DoctorDTOMapper;
import es.plexus.hopes.hopesback.service.mapper.DoctorMapper;

@Service
public class DoctorService {

	private static final Logger LOGGER = LogManager.getLogger(DoctorService.class);

	private final DoctorMapper doctorMapper;

	private final DoctorDTOMapper doctorDTOMapper;
	
	private final DoctorRepository doctorRepository;

	@Autowired
	public DoctorService(DoctorMapper doctorMapper, DoctorDTOMapper doctorDTOMapper, final DoctorRepository doctorRepository) {
		this.doctorMapper = doctorMapper;
		this.doctorDTOMapper = doctorDTOMapper;
		this.doctorRepository = doctorRepository;
	}

	public Page<DoctorDTO> getAllDoctors(final Pageable pageable) {
		LOGGER.debug("Calling DB...");
		Page<Doctor> doctorList = doctorRepository.findAll(pageable);

		return doctorList.map(doctorMapper::doctorToDoctorDTOConverter);
	}

	public Page<DoctorDTO> findDoctorsBySearch(String search, final Pageable pageable) {
		LOGGER.debug("Calling DB...");
		Page<Doctor> doctorList = doctorRepository.findDoctorsBySearch(search, pageable);

		return doctorList.map(doctorMapper::doctorToDoctorDTOConverter);
	}
	
	public DoctorDTO getOneDoctor(Long id) {
		LOGGER.debug(String.format("Calling DB with id = %d ...", id));
		Optional<Doctor> doctor = doctorRepository.findById(id);

		DoctorDTO doctorDTO = null;

		if (doctor.isPresent()) {
			doctorDTO = doctorMapper.doctorToDoctorDTOConverter(doctor.get());
		} else {
			LOGGER.debug(String.format("Doctor with id = %s not found ...", id));
		}

		return doctorDTO;
	}

	public DoctorDTO addDoctor(DoctorDTO doctorDTO) {
		Doctor doctor = doctorMapper.doctorDTOToDoctorConverter(doctorDTO);
		LOGGER.debug("Calling DB...");
		doctor = doctorRepository.save(doctor);

		return doctorMapper.doctorToDoctorDTOConverter(doctor);
	}

	public DoctorDTO updateDoctor(DoctorDTO doctorDTO) {
		Doctor doctor = doctorMapper.doctorDTOToDoctorConverter(doctorDTO);
		LOGGER.debug("Calling DB...");
		doctor = doctorRepository.save(doctor);

		return doctorMapper.doctorToDoctorDTOConverter(doctor);
	}

	public void deleteDoctor(Long id) {
		LOGGER.debug("Calling DB...");
		doctorRepository.deleteById(id);
	}
	
	public Page<DoctorDTO> filterDoctors(String doctor, final Pageable pageable) {
		DoctorDTO doctorDTO = doctorDTOMapper.jsonToDoctorDTOConventer(doctor);	
		LOGGER.debug("Check DTO...");
		DoctorSpecificationsBuilder builder = checkFieldsNull(doctorDTO);
		Specification<Doctor> spec = builder.build();

		LOGGER.debug("Calling DB...");
		Page<Doctor> doctorList = doctorRepository.findAll(spec, pageable);

		return doctorList.map(doctorMapper::doctorToDoctorDTOConverter);
	}
	
	private DoctorSpecificationsBuilder checkFieldsNull(DoctorDTO doctorDTO) {
		DoctorSpecificationsBuilder builder = new DoctorSpecificationsBuilder();
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
}
