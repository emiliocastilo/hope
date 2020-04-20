package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.repository.DoctorRepository;
import es.plexus.hopes.hopesback.repository.model.Doctor;
import es.plexus.hopes.hopesback.service.mapper.DoctorMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

	private static final Logger LOGGER = LogManager.getLogger(DoctorService.class);

	private final DoctorMapper doctorMapper;

	private final DoctorRepository doctorRepository;

	@Autowired
	public DoctorService(DoctorMapper doctorMapper, final DoctorRepository doctorRepository) {
		this.doctorMapper = doctorMapper;
		this.doctorRepository = doctorRepository;
	}

	public List<DoctorDTO> getAllDoctors() {
		LOGGER.debug("Calling DB...");
		List<Doctor> doctorList = doctorRepository.findAll();

		return doctorMapper.doctorListToDoctorDTOListConverter(doctorList);
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
		validateIfDoctorIsPresent(doctorDTO.getId());

		Doctor doctor = doctorMapper.doctorDTOToDoctorConverter(doctorDTO);
		LOGGER.debug("Calling DB...");
		doctor = doctorRepository.save(doctor);

		return doctorMapper.doctorToDoctorDTOConverter(doctor);
	}

	public void deleteDoctor(Long id) {
		validateIfDoctorIsPresent(id);
		LOGGER.debug("Calling DB...");
		doctorRepository.deleteById(id);
	}

	private void validateIfDoctorIsPresent(Long id) {
		Optional<Doctor> storeDoctor = doctorRepository.findById(id);

		if (!storeDoctor.isPresent()) {
			throw new ServiceException(String.format("Doctor with id = %s not found", id));
		}
	}
}
