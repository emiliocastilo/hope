package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.HospitalDTO;
import es.plexus.hopes.hopesback.repository.HospitalRepository;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.service.mapper.HospitalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<HospitalDTO> getAllHospitals() {
		return hospitalRepository.findAll().stream().map(hospitalMapper::hospitalToHospitalDTOConverter)
				.collect(Collectors.toList());
	}

	public Optional<Hospital> getOneHospitalById(Long id) {
		return hospitalRepository.findById(id);
	}
}
