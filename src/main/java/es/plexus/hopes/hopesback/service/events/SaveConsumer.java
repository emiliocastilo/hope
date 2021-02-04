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
import es.plexus.hopes.hopesback.repository.model.*;
import es.plexus.hopes.hopesback.service.*;
import es.plexus.hopes.hopesback.service.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
@Transactional
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

    @EventListener(condition = "#saveEvent.name eq 'farmacology-treatment'")
    public void handleSaveTreatment(SaveEvent saveEvent) throws ParseException {

        FormDTO form = saveEvent.getForm();

        //TODO Cuando haya más de una patología puede haber problemas porque hay que buscar por indicación también

        if (  !StringUtils.isEmpty(saveEvent.getIndication()) ){
            PatientDiagnose patientDiagnose = patientDiagnosisRepository.findByPatientIdAndIndicationId(form.getPatientId().longValue(),indicationRepository.findByDescription(saveEvent.getIndication()).get().getId()).get();
            List<PatientTreatment> patientTreatmentsPostgres = patientTreatmentRepository.findByPatientDiagnose(patientDiagnose)
                    .stream()
                    .filter(patientTreatment -> !TYPE_TREATMENT_PHOTOTHERAPY.equalsIgnoreCase(patientTreatment.getType()))
                    .collect(Collectors.toList());
            ArrayList<LinkedHashMap<String, Object>> patientTreatmentsInMongo = (ArrayList<LinkedHashMap<String, Object>>) form.getData().get(0).getValue();
            List<PatientTreatment> patientTreatmentsMongo = patientTreatmentsInMongo
                    .stream()
                    .map(patientTreatmentMongo -> getPatientTreatmentInMongo(patientTreatmentMongo, form))
                    .collect(Collectors.toList());
            // Eliminar tratamientos en Postgres que no estén en Mongo
            deletePatientTreatmentInPostgres(patientTreatmentsPostgres, patientTreatmentsMongo);
            upsertPatientTreatmentInPostgres(patientTreatmentsInMongo, form);
        }

        // Mapeamos el HasMap de Mongo al modelo PatientTreatment

        // Actualizamos o insertamos tratamientos en Postgres

        formService.updateData(form, null);

    }

    @EventListener(condition = "#saveEvent.name eq 'phototherapy'")
    public void handleSaveTreatmentPhototherapy(SaveEvent saveEvent) throws ParseException {
        FormDTO form = saveEvent.getForm();
        //TODO Cuando haya más de una patología puede haber problemas porque hay que buscar por indicación también
        PatientDiagnose patientDiagnose = patientDiagnosisRepository.findByPatientIdAndIndicationId(form.getPatientId().longValue(),indicationRepository.findByDescription(saveEvent.getIndication()).get().getId()).get();
        List<PatientTreatment> patientTreatmentsPostgres = patientTreatmentRepository.findByPatientDiagnose(patientDiagnose)
                .stream()
                .filter(patientTreatment -> TYPE_TREATMENT_PHOTOTHERAPY.equalsIgnoreCase(patientTreatment.getType()))
                .collect(Collectors.toList());
        ArrayList<LinkedHashMap<String, Object>> patientTreatmentsInMongo = (ArrayList<LinkedHashMap<String, Object>>) form.getData().get(0).getValue();

        // Mapeamos el HasMap de Mongo al modelo PatientTreatment
        List<PatientTreatment> patientTreatmentsMongo = patientTreatmentsInMongo
                .stream()
                .map(patientTreatmentMongo -> getPatientTreatmentInMongo(patientTreatmentMongo, form))
                .collect(Collectors.toList());
        // Eliminar tratamientos en Postgres que no estén en Mongo
        deletePatientTreatmentInPostgres(patientTreatmentsPostgres, patientTreatmentsMongo);

        // Actualizamos o insertamos tratamientos en Postgres
        upsertPatientTreatmentInPostgres(patientTreatmentsInMongo, form);

        form.setJob(false);
        formService.updateData(form, null);
    }



        private void deletePatientTreatmentInPostgres(List<PatientTreatment> patientTreatmentsPostgres, List<PatientTreatment> patientTreatmentsMongo) {
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(patientTreatmentsPostgres)) {

            List<PatientTreatment> patientTreatmentsToDelete = patientTreatmentsPostgres.stream()
                    .filter(ptPostgres -> patientTreatmentsMongo.stream().noneMatch(ptMongo -> isSamePatientTreatment(ptPostgres, ptMongo)))
                    .collect(Collectors.toList());

            if (CollectionUtils.isNotEmpty(patientTreatmentsToDelete)) {
                patientTreatmentRepository.deleteAll(patientTreatmentsToDelete);
            }
        }
    }

    private void upsertPatientTreatmentInPostgres(ArrayList<LinkedHashMap<String, Object>> patientTreatmentsInMongo, FormDTO fPharmacologyTreatment) {
        patientTreatmentsInMongo.forEach(patientTreatmentMongoMap -> {

            PatientTreatment patientTreatmentMongo = getPatientTreatmentInMongo(patientTreatmentMongoMap, fPharmacologyTreatment);
            String masterFormulaCheck = obtainStringValue(fPharmacologyTreatment, patientTreatmentMongoMap, TAGNAME_MASTER_FORMULA_CHECK);
            PatientTreatment patientTreatment = new PatientTreatment();

            if (patientTreatmentMongo.getPatientDiagnose() != null) {
                if (masterFormulaCheck != null && !masterFormulaCheck.isEmpty()) {
                    patientTreatment = patientTreatmentRepository
                            .findByPatientDiagnoseAndMasterFormulaIgnoreCaseAndMasterFormulaDoseIgnoreCaseAndTypeIgnoreCase(
                                    patientTreatmentMongo.getPatientDiagnose(),
                                    patientTreatmentMongo.getMasterFormula(),
                                    patientTreatmentMongo.getMasterFormulaDose(),
                                    patientTreatmentMongo.getType())
                            .orElse(new PatientTreatment());
                } else if (patientTreatmentMongo.getMedicine() != null) {
                    // Averiguar qué Tratamiento tengo que actualizar, mirar medicamento y diagnóstico.
                    patientTreatment = patientTreatmentRepository
                            .findByPatientDiagnoseAndMedicineAndInitDateAndTypeIgnoreCase(
                                    patientTreatmentMongo.getPatientDiagnose(),
                                    patientTreatmentMongo.getMedicine(),
                                    patientTreatmentMongo.getInitDate(),
                                    patientTreatmentMongo.getType()).orElse(new PatientTreatment());
                }

                patientTreatment.setPatientDiagnose(patientTreatmentMongo.getPatientDiagnose());
                patientTreatment.setMedicine(patientTreatmentMongo.getMedicine());
                patientTreatment.setType(patientTreatmentMongo.getType());
                patientTreatment.setDose(patientTreatmentMongo.getDose());
                patientTreatment.setMasterFormula(patientTreatmentMongo.getMasterFormula());
                patientTreatment.setMasterFormulaDose(patientTreatmentMongo.getMasterFormulaDose());
                patientTreatment.setRegimen(patientTreatmentMongo.getRegimen());
                patientTreatment.setInitDate(patientTreatmentMongo.getInitDate());
                patientTreatment.setFinalDate(patientTreatmentMongo.getFinalDate());
                patientTreatment.setReason(patientTreatmentMongo.getReason());
                patientTreatment.setEndCause(patientTreatmentMongo.getEndCause());
                patientTreatment.setActive(patientTreatmentMongo.isActive());

                patientTreatmentService.save(patientTreatment);
            }
        });
    }

    private PatientTreatment getPatientTreatmentInMongo(LinkedHashMap<String, Object> patientTreatmentMongo, FormDTO formDTO) {

        PatientTreatment patientTreatment = new PatientTreatment();
        patientTreatment.setType(obtainStringValue(formDTO, patientTreatmentMongo, TAGNAME_TYPE));
        patientTreatment.setInitDate(obtainLocalDateTimeValue(formDTO, patientTreatmentMongo, TAGNAME_DATE_START));
        patientTreatment.setFinalDate(obtainLocalDateTimeValue(formDTO, patientTreatmentMongo, TAGNAME_DATE_SUSPENSION));
        patientTreatment.setReason(obtainStringValue(formDTO, patientTreatmentMongo, TAGNAME_REASON_CHANGE_OR_SUSPENSION));

        if (formDTO.getTemplate().equalsIgnoreCase(TEMPLATE_PHARMACOLOGY_TREATMENT)) {

            Object medicineString = getValueByTagName(formDTO, patientTreatmentMongo, TAGNAME_MEDICINE);
            if (medicineString != null && !StringUtils.isEmpty(medicineString.toString())) {
                String medicineId = obtainStringValue(formDTO, patientTreatmentMongo, TAGNAME_MEDICINE, "id");
                Medicine medicine = medicineRepository.findById(Long.valueOf(medicineId)).orElse(null);
                patientTreatment.setMedicine(medicine);
            }

            String dose = obtainStringValue(formDTO, patientTreatmentMongo, TAGNAME_DOSE, TAGNAME_NAME);
            if (dose != null && dose.equals("Otra")) {
                dose = obtainStringValue(formDTO, patientTreatmentMongo, TAGNAME_OTHERDOSE);
            }
            patientTreatment.setDose(dose);
            patientTreatment.setMasterFormula(obtainStringValue(formDTO, patientTreatmentMongo, TAGNAME_MASTER_FORMULA_DESCRIPTION));
            patientTreatment.setMasterFormulaDose(obtainStringValue(formDTO, patientTreatmentMongo, TAGNAME_MASTER_FORMULA_DOSES));
            patientTreatment.setRegimen(obtainStringValue(formDTO, patientTreatmentMongo, TAGNAME_REGIMEN_TREATMENT));
        }

        if (patientTreatmentMongo.get(TAGNAME_INDICATION) != null) {
            // TODO: Revisar cual se ha de usar
            Indication indication = indicationRepository.findByCode(patientTreatmentMongo.get(TAGNAME_INDICATION).toString()).orElse(null);
            PatientDiagnose patientDiagnose = indication != null ? patientDiagnosisRepository.findByPatientIdAndIndicationId(formDTO.getPatientId().longValue(), indication.getId()).orElse(null) : null;
            patientTreatment.setPatientDiagnose(patientDiagnose);
//			List<FormDTO> formsDTO = formService.findByTemplateAndPatientId(TEMPLATE_DIAGNOSIS, formDTO.getPatientId());
//			if (!CollectionUtils.isEmpty(formsDTO)) {
//				Optional<Indication> indication = indicationRepository.findByCode(patientTreatmentMongo.get(TAGNAME_INDICATION).toString());
//				PatientDiagnose patientDiagnose = indication.flatMap(value -> patientDiagnosisRepository.findByPatientIdAndIndicationId(formDTO.getPatientId().longValue(), value.getId())).orElse(null);
//				patientTreatment.setPatientDiagnose(patientDiagnose);
//			}
        }

        if (patientTreatment.getFinalDate() != null) {
            patientTreatment.setEndCause("Suspension");
            patientTreatment.setActive(false);
        } else {
            patientTreatment.setEndCause("Cambio");
            patientTreatment.setActive(true);
        }

        return patientTreatment;
    }

    private boolean isSamePatientTreatment(PatientTreatment patientTreatmentPostgres, PatientTreatment patientTreatmentMongo) {
        if (!TYPE_TREATMENT_PHOTOTHERAPY.equalsIgnoreCase(patientTreatmentPostgres.getType())) {
            return patientTreatmentPostgres.getPatientDiagnose() != null && patientTreatmentPostgres.getPatientDiagnose().getIndication() != null
                    && patientTreatmentMongo.getPatientDiagnose() != null && patientTreatmentMongo.getPatientDiagnose().getIndication() != null
                    && patientTreatmentPostgres.getPatientDiagnose().getIndication().getDescription().equals(patientTreatmentMongo.getPatientDiagnose().getIndication().getDescription())
                    && (patientTreatmentPostgres.getMedicine() != null && patientTreatmentMongo.getMedicine() != null && patientTreatmentPostgres.getMedicine().getId().equals(patientTreatmentMongo.getMedicine().getId())
                    || (patientTreatmentPostgres.getMasterFormula() != null && patientTreatmentPostgres.getMasterFormula().equals(patientTreatmentMongo.getMasterFormula())))
                    && patientTreatmentPostgres.getInitDate().isEqual(patientTreatmentMongo.getInitDate())
                    && patientTreatmentPostgres.getType().equals(patientTreatmentMongo.getType())
                    && (patientTreatmentPostgres.getFinalDate() == null || patientTreatmentPostgres.getFinalDate().isEqual(patientTreatmentMongo.getFinalDate()));
        } else {
            return patientTreatmentPostgres.getPatientDiagnose() != null && patientTreatmentPostgres.getPatientDiagnose().getIndication() != null
                    && patientTreatmentMongo.getPatientDiagnose() != null && patientTreatmentMongo.getPatientDiagnose().getIndication() != null
                    && patientTreatmentPostgres.getPatientDiagnose().getIndication().getDescription().equals(patientTreatmentMongo.getPatientDiagnose().getIndication().getDescription())
                    && patientTreatmentPostgres.getInitDate().isEqual(patientTreatmentMongo.getInitDate())
                    && patientTreatmentPostgres.getType().equals(patientTreatmentMongo.getType())
                    && (patientTreatmentPostgres.getFinalDate() == null || patientTreatmentPostgres.getFinalDate().isEqual(patientTreatmentMongo.getFinalDate()));
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

    private LocalDateTime obtainLocalDateTimeValue(FormDTO form, LinkedHashMap<String, Object> field, String tagName) {
        Object result = getValueByTagName(form, field, tagName);
        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd[ HH:mm:ss]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter();
            if (result != null && result.toString().contains("T")) {
                formatter = new DateTimeFormatterBuilder()
                        .appendPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                        .toFormatter();
            }
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

    private String obtainStringValue(FormDTO form, LinkedHashMap<String, Object> field, String tagName) {
        String valueString = null;
        try {
            Object value = getValueByTagName(form, field, tagName);
            valueString = value != null ? value.toString() : null;
        } catch (Exception e) {
            log.error(LOG_ERROR,
                    form.getTemplate(), form.getPatientId(), tagName, e.getMessage());
        }
        return valueString;
    }

    private String obtainStringValue(FormDTO form, LinkedHashMap<String, Object> field, String
            firtsTagName, String secondTagName) {
        String valueString = null;
        try {
            Object value = getValueByTagName(form, field, firtsTagName);
            if (value != null) {
                value = getValueByTagName(form, (LinkedHashMap<String, Object>) value, secondTagName);
                valueString = value != null ? value.toString() : null;
            }
        } catch (Exception e) {
            log.error(LOG_ERROR,
                    form.getTemplate(), form.getPatientId(), firtsTagName, e.getMessage());
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

    private Object getValueByTagName(FormDTO form, LinkedHashMap<String, Object> field, String tagName) {
        Object value = field.get(tagName);
        if (value == null) {
            log.error("Template: {} PatientId: {} Error: No se ha encontrado la etiqueta: {}",
                    form.getTemplate(), form.getPatientId(), tagName);
        }
        return value;
    }



}


