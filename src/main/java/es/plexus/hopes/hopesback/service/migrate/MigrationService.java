/**
 * 
 */
package es.plexus.hopes.hopesback.service.migrate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.controller.model.InputDTO;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import es.plexus.hopes.hopesback.service.FormService;
import es.plexus.hopes.hopesback.service.IndicationService;
import es.plexus.hopes.hopesback.service.PatientDiagnosisService;
import es.plexus.hopes.hopesback.service.PatientService;
import es.plexus.hopes.hopesback.service.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author jose.estevezbarroso
 *
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class MigrationService {
	
	private static final String START_DIAGNOSIS = "Starting migration diagnosis data";
	private static final String END_DIAGNOSIS = "Finished migration diagnosis data";
	private static final String TEMPLATE_DIAGNOSIS = "DIAGNOSTICO";
	
	private static final String TAGNAME_MAIN_DIAGNOSIS_DATE = "mainDiagnosisDate";
	private static final String TAGNAME_SPECIALTY_CARE_DATE = "specialtyCareDate";
	private static final String TAGNAME_FIRST_SYMPTOMS_DATE = "firstSymptomsDate";
	private static final String TAGNAME_PSORIASIS_TYPE = "psoriasisType";
	
	@Autowired
	private final FormService formService;
	
	@Autowired
	private final PatientDiagnosisService patientDiagnosisService;
	
	@Autowired
	private final PatientService patientService;
	
	@Autowired
	private final IndicationService indicationService;
	
	@Autowired
	private final PatientMapper patientMapper;
	
	
	@Scheduled(cron = "${cronexpression.diagnosis}")
	public void migrationDataDiagnosisFromNoRelationalToRelational() {
		log.debug(START_DIAGNOSIS);
		List<FormDTO> formsDTO = formService.findByTemplate(TEMPLATE_DIAGNOSIS);
		
		formsDTO.stream()
		.forEach(f -> {
			PatientDiagnose patientDiagnose = new PatientDiagnose();
			patientDiagnose.setPatient(patientMapper.dtoToEntity(patientService.findById(f.getPatientId().longValue())));
			patientDiagnose.setIndication(indicationService.getIndicationByDescription(getValueByTagNameFromForm(f, TAGNAME_PSORIASIS_TYPE)));
			patientDiagnose.setInitDate(obtainLocalDateTime(f, TAGNAME_MAIN_DIAGNOSIS_DATE));
			patientDiagnose.setSymptomsDate(obtainLocalDateTime(f, TAGNAME_SPECIALTY_CARE_DATE));
			patientDiagnose.setDerivationDate(obtainLocalDateTime(f, TAGNAME_FIRST_SYMPTOMS_DATE));
			patientDiagnosisService.save(patientDiagnose);
		});
		
		log.debug(END_DIAGNOSIS);
	}

	private LocalDateTime obtainLocalDateTime(FormDTO form, String tagName) {
		String result = getValueByTagNameFromForm(form, tagName);
		try {
			return Objects.nonNull(result) ? LocalDateTime.parse(result) : null;
		} catch (Exception e) {
			// Don't stop the migration, just inform the problem related to date
			log.error("Template: {} PatientId: {} TagName: {} . Error: {}", form.getTemplate(), form.getPatientId(), tagName, e.getMessage());
			return null;
		}
	}

	private String getValueByTagNameFromForm(FormDTO form, String tagName) {
		Optional<InputDTO> result = form.getData().stream()
				.filter(inputDTO -> inputDTO.getName().equals(tagName))
				.findFirst();
		if (result.isPresent()) {
			return result.get().getValue();
		}
		else {
			log.error("Template: {} PatientId: {} Error: No se ha encontrado la etiqueta: {}", form.getTemplate(), form.getPatientId(), tagName);
			return null;
		}
	}
}
