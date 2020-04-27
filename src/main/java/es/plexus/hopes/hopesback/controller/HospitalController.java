package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.HospitalDTO;
import es.plexus.hopes.hopesback.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(HospitalController.HOSPITAL_MAPPING)
public class HospitalController {

	static final String HOSPITAL_MAPPING = "/hospital";

	private final HospitalService hospitalService;

	@Autowired
	public HospitalController(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}

	@GetMapping
	public List<HospitalDTO> getAllHospitals() {
		return hospitalService.getAllHospitals();
	}

}


