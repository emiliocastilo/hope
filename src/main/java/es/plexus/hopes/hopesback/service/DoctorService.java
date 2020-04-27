package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.repository.DoctorRepository;
import es.plexus.hopes.hopesback.repository.model.Doctor;
import es.plexus.hopes.hopesback.service.mapper.DoctorDTOMapper;
import es.plexus.hopes.hopesback.service.mapper.DoctorMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

	private static final Logger LOGGER = LogManager.getLogger(DoctorService.class);

	private final DoctorMapper doctorMapper;
	
	private final DoctorRepository doctorRepository;

	@Autowired
	public DoctorService(DoctorMapper doctorMapper, DoctorDTOMapper doctorDTOMapper, final DoctorRepository doctorRepository) {
		this.doctorMapper = doctorMapper;
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
		DoctorDTO doctorDTO = DoctorDTOMapper.INSTANCE.jsonToDoctorDTOConventer(doctor);	
		LOGGER.debug("Check DTO...");

    	ExampleMatcher matcher = ExampleMatcher.matchingAll().
    			withIgnoreCase(true).withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    	
    	Doctor doctorEx = doctorMapper.doctorDTOToDoctorConverterSearch(doctorDTO);
 
    	
		Example<Doctor> spec = Example.of(doctorEx, matcher);
			 
		Page<Doctor> doctorList = doctorRepository.findAll(spec, pageable);

		return doctorList.map(doctorMapper::doctorToDoctorDTOConverter);
	}

}
