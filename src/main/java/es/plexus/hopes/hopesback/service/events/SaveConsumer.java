package es.plexus.hopes.hopesback.service.events;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.plexus.hopes.hopesback.controller.model.DataHistoricDinamycFormDTO;
import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.controller.model.GraphHistorifyDinamycFormDTO;
import es.plexus.hopes.hopesback.controller.model.InputDTO;
import es.plexus.hopes.hopesback.controller.model.MonitoringDrugRowDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.repository.*;
import es.plexus.hopes.hopesback.repository.model.Indication;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import es.plexus.hopes.hopesback.service.*;
import es.plexus.hopes.hopesback.service.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
@Log4j2
@RequiredArgsConstructor
public class SaveConsumer {

    private static final String START_DIAGNOSIS = "Starting migration diagnosis data";
    private static final String END_DIAGNOSIS = "Finished migration diagnosis data";
    private static final String START_PHARMACOLOGY_TREATMENT = "Starting migration pharmacology treatment data";
    private static final String END_TREATMENT = "Finished migration treatment data";

    private static final String TEMPLATE_DIAGNOSIS = "principal-diagnosis";
    private static final String TEMPLATE_PHARMACOLOGY_TREATMENT = "farmacology-treatment";
    private static final String TEMPLATE_NO_PHARMACOLOGY_TREATMENT = "phototherapy";

    private static final String TAGNAME_DATE_PRINCIPAL_DIAGNOSES = "datePrincipalDiagnoses";
    private static final String TAGNAME_DATE_SYMPTOM = "dateSymptom";
    private static final String TAGNAME_PRINCIPAL_INDICATION = "principalIndication";
    private static final String TAGNAME_ANOTHER_PSORIASIS = "anotherPsoriasis";
    private static final String TAGNAME_CIE_CODE = "cie";
    private static final String TAGNAME_CIE_DESCRIPTION = "cieDescription";
    private static final String TAGNAME_DATE_DERIVATION = "dateDerivation";
    private static final String TAGNAME_PSORIASIS_ARTHRITIS = "psoriaticArthritis";
    private static final String TAGNAME_INDICATION = "indication";
    private static final String TAGNAME_DOSE = "dose";
    private static final String TAGNAME_OTHERDOSE = "otherDosis";
    private static final String TAGNAME_REGIMEN_TREATMENT = "regimenTreatment";
    private static final String TAGNAME_DATE_START = "dateStart";
    private static final String TAGNAME_REASON_CHANGE_OR_SUSPENSION = "reasonChangeOrSuspension";
    private static final String TAGNAME_DATE_SUSPENSION = "dateSuspension";
    private static final String TAGNAME_TYPE = "treatmentType";
    private static final String TAGNAME_MASTER_FORMULA_CHECK = "opcionFormulaMagistral";
    private static final String TAGNAME_MASTER_FORMULA_DESCRIPTION = "descripcionFormulaMagistral";
    private static final String TAGNAME_MASTER_FORMULA_DOSES = "dosisFormulaMagistral";
    private static final String TAGNAME_MEDICINE = "medicine";
    public static final String TAGNAME_NAME = "name";
    public static final String TYPE_TREATMENT_PHOTOTHERAPY = "FOTOTERAPIA";

    private static final String LOG_ERROR = "Template: {} PatientId: {} TagName: {} . Error: {}";

    private final FormService formService;
    private final PatientService patientService;
    private final PatientDiagnosisService patientDiagnosisService;
    private final PatientTreatmentService patientTreatmentService;
    private final PatientRepository patientRepository;
    private final IndicationRepository indicationRepository;
    private final PatientDiagnosisRepository patientDiagnosisRepository;
    private final MedicineRepository medicineRepository;
    private final PatientTreatmentRepository patientTreatmentRepository;

    @EventListener(condition = "#saveEvent.name eq 'principal-diagnosis'")
    public void handleSavePrincipalDiagnosis(SaveEvent saveEvent) throws ParseException {

        FormDTO form = saveEvent.getForm();

        Optional<Patient> patient = patientRepository.findById(form.getPatientId().longValue());
        if (patient.isPresent()) {
            PatientDiagnose patientDiagnose = patientDiagnosisRepository.findByPatient(patient.get());
            Optional<Indication> indication = indicationRepository.findByCode(obtainStringValue(form, TAGNAME_PRINCIPAL_INDICATION));

            if (patientDiagnose == null) {
                patientDiagnose = new PatientDiagnose();
            }

            patientDiagnose.setPatient(patient.get());
            patientDiagnose.setIndication(indication.orElse(null));
            patientDiagnose.setOthersIndications(obtainStringValue(form, TAGNAME_ANOTHER_PSORIASIS));
            patientDiagnose.setInitDate(obtainLocalDateTimeValue(form, TAGNAME_DATE_PRINCIPAL_DIAGNOSES));
            patientDiagnose.setSymptomsDate(obtainLocalDateTimeValue(form, TAGNAME_DATE_SYMPTOM));
            patientDiagnose.setDerivationDate(obtainLocalDateTimeValue(form, TAGNAME_DATE_DERIVATION));
            patientDiagnose.setCieCode(obtainStringValue(form, TAGNAME_CIE_CODE));
            patientDiagnose.setCieDescription(obtainStringValue(form, TAGNAME_CIE_DESCRIPTION));
            patientDiagnose.setPsoriaticArthritis(("true").equalsIgnoreCase(obtainStringValue(form,TAGNAME_PSORIASIS_ARTHRITIS)));


            patientDiagnosisService.save(patientDiagnose);

        }
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
            log.error(LOG_ERROR,
                    form.getTemplate(), form.getPatientId(), tagName, e.getMessage());
            return null;
        }
    }

    private String obtainStringValue(FormDTO form, String tagName) {
        String valueString = null;
        try {
            Object value = getValueByTagNameFromForm(form, tagName);
            valueString = value != null ? value.toString() : null;
        } catch (Exception e) {
            log.error(LOG_ERROR,
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
        } else {
            log.error("Template: {} PatientId: {} Error: No se ha encontrado la etiqueta: {}",
                    form.getTemplate(), form.getPatientId(), tagName);
            return null;
        }
    }



}


