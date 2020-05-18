package es.plexus.hopes.hopesback.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.plexus.hopes.hopesback.controller.model.DetailGraphDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDosesInfoDTO;
import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;
import es.plexus.hopes.hopesback.service.GraphsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/graphs")
public class GraphsController {
	private static final String CALLING_SERVICE = "Calling service...";

	private final GraphsService graphsService;

	@GetMapping("/patients-under-treatment")
	public List<TreatmentInfoDTO> patientsUnderTreatment(@RequestParam(value = "type", required = true) String type, @RequestParam(value = "indication", required = false) String indication) {
		log.debug(CALLING_SERVICE);
		return graphsService.patientsUnderTreatment(type, indication);
	}
	
	@GetMapping("/info-patients-doses")
	public List<PatientDosesInfoDTO> infoPatientsDoses() {
		log.debug(CALLING_SERVICE);
		return graphsService.infoPatientsDoses();
	}
	
	@GetMapping("/details-graphs")
	public Page<DetailGraphDTO> detailsGrapths(@RequestParam(value = "type", required = true) String type, @RequestParam(value = "indication", required = false) String indication, @PageableDefault(size = 5) final Pageable pageable) {
		log.debug(CALLING_SERVICE);
		return graphsService.detailsGrapths(type, indication, pageable);
	}
}
