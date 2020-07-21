/**
 * 
 */
package es.plexus.hopes.hopesback.service.migrate;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.controller.model.InputDTO;
import es.plexus.hopes.hopesback.controller.model.MedicineDTO;
import es.plexus.hopes.hopesback.repository.model.Indication;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import es.plexus.hopes.hopesback.service.FormService;
import es.plexus.hopes.hopesback.service.IndicationService;
import es.plexus.hopes.hopesback.service.MedicineService;
import es.plexus.hopes.hopesback.service.PatientDiagnosisService;
import es.plexus.hopes.hopesback.service.PatientService;
import es.plexus.hopes.hopesback.service.PatientTreatmentService;
import es.plexus.hopes.hopesback.service.mapper.MedicineMapper;
import es.plexus.hopes.hopesback.service.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
	private static final String START_PHARMACOLOGY_TREATMENT = "Starting migration pharmacology treatment data";
	private static final String END_PHARMACOLOGY_TREATMENT= "Finished migration pharmacology treatment data";

	private static final String TEMPLATE_DIAGNOSIS = "principal-diagnosis";
	private static final String TEMPLATE_PHARMACOLOGY_TREATMENT = "farmacology-treatment";
	
	private static final String TAGNAME_DATE_PRINCIPAL_DIAGNOSES = "datePrincipalDiagnoses";
	private static final String TAGNAME_DATE_SYMPTOM = "dateSymptom";
	private static final String TAGNAME_PSORIASIS_TYPE = "psoriasisType";
	private static final String TAGNAME_ANOTHER_PSORIASIS = "anotherPsoriasis";
	private static final String TAGNAME_CIE_CODE = "cieCode";
	private static final String TAGNAME_CIE_DESCRIPTION = "cieDescription";
	private static final String TAGNAME_DATE_DERIVATION = "dateDerivation";
	private static final String TAGNAME_INDICATION = "indication";
	private static final String TAGNAME_NATIONAL_CODE = "cn";
	private static final String TAGNAME_DOSE = "dose";
	private static final String TAGNAME_REGIMEN_TREATMENT = "regimenTreatment";
	private static final String TAGNAME_DATE_START = "dateStart";
	private static final String TAGNAME_TREATMENT_CONTINUE = "treatmentContinue";
	private static final String TAGNAME_REASON_CHANGE_OR_SUSPENSION = "reasonChangeOrSuspension";
	private static final String TAGNAME_DATE_SUSPENSION = "dateSuspension";
	private static final String TAGNAME_TYPE = "type";
	private static final String TAGNAME_MASTER_FORMULA = "masterFormula";
	private static final String TAGNAME_MASTER_FORMULA_DESCRIPTION = "masterFormulaDescription";
	private static final String TAGNAME_MASTER_FORMULA_DOSES = "masterFormulaDosis";

	private final FormService formService;
	private final PatientDiagnosisService patientDiagnosisService;
	private final PatientService patientService;
	private final IndicationService indicationService;
	private final PatientTreatmentService patientTreatmentService;
	private final MedicineService medicineService;
	
	@Scheduled(cron = "${cronexpression.diagnosis}")
	public void migrationDataDiagnosisFromNoRelationalToRelational() {
		log.debug(START_DIAGNOSIS);
		List<FormDTO> formsDTO = formService.findByTemplate(TEMPLATE_DIAGNOSIS);
		
		formsDTO
		.forEach(f -> {

			Patient patient = PatientMapper.INSTANCE.dtoToEntity(patientService.findById(f.getPatientId().longValue()));
			PatientDiagnose patientDiagnose = patientDiagnosisService.findByPatient(patient);

			if (patientDiagnose == null) {
				patientDiagnose = new PatientDiagnose();
			}

			patientDiagnose.setPatient(patient);
			patientDiagnose.setIndication(indicationService.getIndicationByDescription(obtainStringValue(f, TAGNAME_PSORIASIS_TYPE)));
			patientDiagnose.setOthersIndications(obtainStringValue(f, TAGNAME_ANOTHER_PSORIASIS));
			patientDiagnose.setInitDate(obtainLocalDateTimeValue(f, TAGNAME_DATE_PRINCIPAL_DIAGNOSES));
			patientDiagnose.setSymptomsDate(obtainLocalDateTimeValue(f, TAGNAME_DATE_SYMPTOM));
			patientDiagnose.setDerivationDate(obtainLocalDateTimeValue(f, TAGNAME_DATE_DERIVATION));
			patientDiagnose.setCieCode(obtainStringValue(f, TAGNAME_CIE_CODE));
			patientDiagnose.setCieDescription(obtainStringValue(f, TAGNAME_CIE_DESCRIPTION));

			patientDiagnosisService.save(patientDiagnose);
		});
		
		log.debug(END_DIAGNOSIS);
	}

	@Scheduled(cron = "${cronexpression.treatment}")
	public void migrationDataPharmacologyTreatmentFromNoRelationalToRelational() {
		log.debug(START_PHARMACOLOGY_TREATMENT);
		List<FormDTO> formsDTO = formService.findByTemplate(TEMPLATE_PHARMACOLOGY_TREATMENT);

		formsDTO
				.forEach(f -> {
					Patient patient = PatientMapper.INSTANCE.dtoToEntity(patientService.findById(f.getPatientId().longValue()));
					Indication indication = indicationService.getIndicationByDescription(obtainStringValue(f, TAGNAME_INDICATION));
					PatientDiagnose patientDiagnose = patientDiagnosisService.findByPatientAndIndication(patient, indication);
					PatientTreatment patientTreatment;
					MedicineDTO medicine = null;
					LocalDateTime initDateTreatment = obtainLocalDateTimeValue(f, TAGNAME_DATE_START);
					String masterFormula = obtainStringValue(f, TAGNAME_MASTER_FORMULA_DESCRIPTION);
					String masterFormulaDose = obtainStringValue(f, TAGNAME_MASTER_FORMULA_DOSES);

					if (Boolean.TRUE.equals(getValueByTagNameFromForm(f, TAGNAME_MASTER_FORMULA))) {
						patientTreatment = patientTreatmentService.findByMasterFormulaAndMasterFormulaDose(masterFormula, masterFormulaDose);
					} else {
						medicine = medicineService.findByNationalCode(obtainStringValue(f, TAGNAME_NATIONAL_CODE));
						// Averiguar qué Tratamiento tengo que actualizar, mirar medicamento y diagnóstico.
						patientTreatment = patientTreatmentService.findByPatientDiagnoseAndMedicineAndInitDate(patientDiagnose, MedicineMapper.INSTANCE.dtoToEntity(medicine), initDateTreatment);
					}

					if (patientTreatment == null) {
						patientTreatment = new PatientTreatment();
					}

					patientTreatment.setPatientDiagnose(patientDiagnose);
					patientTreatment.setMedicine(MedicineMapper.INSTANCE.dtoToEntity(medicine));
					patientTreatment.setActive(Boolean.TRUE.equals(getValueByTagNameFromForm(f, TAGNAME_TREATMENT_CONTINUE)));
					patientTreatment.setType(obtainStringValue(f, TAGNAME_TYPE));
					patientTreatment.setDose(obtainStringValue(f, TAGNAME_DOSE));
					patientTreatment.setMasterFormula(masterFormula);
					patientTreatment.setMasterFormulaDose(masterFormulaDose);
					patientTreatment.setRegimen(obtainStringValue(f, TAGNAME_REGIMEN_TREATMENT));
					patientTreatment.setInitDate(initDateTreatment);
					patientTreatment.setFinalDate(obtainLocalDateTimeValue(f, TAGNAME_DATE_SUSPENSION));
					patientTreatment.setReason(obtainStringValue(f, TAGNAME_REASON_CHANGE_OR_SUSPENSION));

					if (patientTreatment.getFinalDate() != null) {
						patientTreatment.setEndCause("Suspension");
					} else {
						patientTreatment.setEndCause("Cambio");
					}

					patientTreatmentService.save(patientTreatment);
				});

		log.debug(END_PHARMACOLOGY_TREATMENT);
	}

	private LocalDateTime obtainLocalDateTimeValue(FormDTO form, String tagName) {
		Object result = getValueByTagNameFromForm(form, tagName);
		try {
			DateTimeFormatter formatter = new DateTimeFormatterBuilder()
					.appendPattern("yyyy-MM-dd[ HH:mm:ss]")
					.parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
					.parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
					.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
					.toFormatter();
			return Objects.nonNull(result) ? LocalDateTime.parse(result.toString(), formatter) : null;
		} catch (Exception e) {
			// Don't stop the migration, just inform the problem related to date
			log.error("Template: {} PatientId: {} TagName: {} . Error: {}",
						form.getTemplate(), form.getPatientId(), tagName, e.getMessage());
			return null;
		}
	}
	
	private String obtainStringValue(FormDTO form, String tagName){
		String valueString = null;
		try {
			Object value = getValueByTagNameFromForm(form, tagName);
			valueString = value != null ? value.toString() : null;
		} catch (Exception e){
			log.error("Template: {} PatientId: {} TagName: {} . Error: {}",
						form.getTemplate(), form.getPatientId(), tagName, e.getMessage());
		}
		return valueString;
	}

	private Object getValueByTagNameFromForm(FormDTO form, String tagName) {
		Optional<InputDTO> result = form.getData().stream()
				.filter(inputDTO -> tagName.equals(inputDTO.getName()))
				.findFirst();
		if (result.isPresent()) {
			return result.get().getValue();
		}
		else {
			log.error("Template: {} PatientId: {} Error: No se ha encontrado la etiqueta: {}",
						form.getTemplate(), form.getPatientId(), tagName);
			return null;
		}
	}
}
