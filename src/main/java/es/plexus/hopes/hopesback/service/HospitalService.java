package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.HospitalDTO;
import es.plexus.hopes.hopesback.repository.HospitalRepository;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.HospitalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospitalService {

	private final HospitalRepository hospitalRepository;
	private final HospitalMapper hospitalMapper;

	@Autowired
	public HospitalService(final HospitalRepository hospitalRepository, final HospitalMapper hospitalMapper) {
		this.hospitalRepository = hospitalRepository;
		this.hospitalMapper = hospitalMapper;
	}

	@Transactional
	public List<HospitalDTO> getAllHospitals() {
		return hospitalRepository.findAll().stream().map(hospitalMapper::hospitalToHospitalDTOConverter)
				.collect(Collectors.toList());
	}

	@Transactional
	public Optional<Hospital> getOneHospitalById(Long id) {
		return hospitalRepository.findById(id);
	}

	@Transactional
	public HospitalDTO findById(Long id) {
		Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
		if (!optionalHospital.isPresent()) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(
					String.format("Hospital con id %d no encontrado. El hospital es requerido.", id));
		}
		return hospitalMapper.hospitalToHospitalDTOConverter(optionalHospital.get());

	}

	public HospitalDTO findByName(String name) {
		Optional<Hospital> hospital = hospitalRepository.findByName(name);
		if (hospital.isPresent()) {
			return hospitalMapper.hospitalToHospitalDTOConverter(hospital.get());
		} else {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(
					MessageFormat.format("Hospital con id {0} no encontrado. El hospital es requerido.", name));
		}
	}
}
