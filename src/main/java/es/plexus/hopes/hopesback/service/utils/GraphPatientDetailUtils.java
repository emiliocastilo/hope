package es.plexus.hopes.hopesback.service.utils;

import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.model.HealthOutcome;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j2
public final class GraphPatientDetailUtils {

    private static final String EMPTY_STRING = "";
    public static final String DELIMITER_MEDICINES = ", ";
    public static final String TARGET_DELIMITER_MEDICINES = ", , ";
    private static final String DEBUG_QUERY_GRAPH_PATIENT_DETAIL = "----------------------------------- %s ------------------------------------------------------------------------";

    public static Page<GraphPatientDetailDTO> doPaginationGraphPatientDetailDTO(List<GraphPatientDetailDTO> graphPatientDetailList, Pageable pageable) {

        int start = Long.valueOf(pageable.getOffset()).intValue();
        int end = (start + pageable.getPageSize()) > graphPatientDetailList.size() ?
                graphPatientDetailList.size() : (start + pageable.getPageSize());

        List<GraphPatientDetailDTO> resultList = graphPatientDetailList.subList(start, end);

        if (pageable.getSort().get().findFirst().isPresent()) {
            orderGraphPatientDetailList(resultList, pageable.getSort());
        }

        return new PageImpl<>(resultList, pageable, graphPatientDetailList.size());
    }

    public static List<GraphPatientDetailDTO> fillGraphPatientDetailDtoList(List<Patient> patients) {
        List<GraphPatientDetailDTO> graphPatientDetailDTOS = new ArrayList<>();
        patients = patients.stream().distinct().collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(patients)) {
            log.debug(String.format(DEBUG_QUERY_GRAPH_PATIENT_DETAIL, "INIT: Find Patients"));
            patients.forEach(
                    patient -> {

                        GraphPatientDetailDTO graphPatientDetailDTO = new GraphPatientDetailDTO();
                        graphPatientDetailDTO.setId(patient.getId());
                        graphPatientDetailDTO.setNhc(patient.getNhc());
                        graphPatientDetailDTO.setHealthCard(patient.getHealthCard());
                        graphPatientDetailDTO.setFullName(String
                                .format("%s %s %s", patient.getName(), patient.getFirstSurname(),
                                        !StringUtils.isEmpty(patient.getLastSurname())?patient.getLastSurname(): EMPTY_STRING));

                        fillPatientDiagnoseInfoIntoGraphPatientDetailDTO(patient, graphPatientDetailDTO);

                        log.debug(String.format(DEBUG_QUERY_GRAPH_PATIENT_DETAIL, "INIT: Find PASI"));
                        HealthOutcome pasiHealtOutcome = patient.getHealthOutcomes().stream()
                                .filter(healthOutcome -> "PASI".equals(healthOutcome.getIndexType()))
                                .max(Comparator.comparing(HealthOutcome::getDate))
                                .orElseGet(HealthOutcome::new);

                        graphPatientDetailDTO.setPasi(pasiHealtOutcome.getResult());
                        graphPatientDetailDTO.setPasiDate(pasiHealtOutcome.getDate());
                        log.debug(String.format(DEBUG_QUERY_GRAPH_PATIENT_DETAIL, "INIT: Find PASI"));

                        log.debug(String.format(DEBUG_QUERY_GRAPH_PATIENT_DETAIL, "INIT: Find DLQI"));

                        HealthOutcome dlqiHealtOutcome = patient.getHealthOutcomes().stream()
                                .filter(healthOutcome -> "DLQI".equals(healthOutcome.getIndexType()))
                                .max(Comparator.comparing(HealthOutcome::getDate))
                                .orElseGet(HealthOutcome::new);

                        graphPatientDetailDTO.setDlqi(dlqiHealtOutcome.getResult());
                        graphPatientDetailDTO.setDlqiDate(dlqiHealtOutcome.getDate());
                        graphPatientDetailDTOS.add(graphPatientDetailDTO);

                        log.debug(String.format(DEBUG_QUERY_GRAPH_PATIENT_DETAIL, "END: Fin DQLI"));

                        log.debug(String.format(DEBUG_QUERY_GRAPH_PATIENT_DETAIL, "INIT: Find Patients"));

                    }
            );
        }
        log.debug("--------------------------------------------------- Finish Graph ---------------------------------------------------");

        return graphPatientDetailDTOS;
    }
    public static void fillPatientDiagnoseInfoIntoGraphPatientDetailDTO(Patient patient, GraphPatientDetailDTO graphPatientDetailDTO) {
        log.debug(String.format(DEBUG_QUERY_GRAPH_PATIENT_DETAIL, "INIT: Find PatientDiagnose"));
        if(CollectionUtils.isNotEmpty(patient.getDiagnoses())) {
            patient.getDiagnoses().forEach(patientDiagnose -> {
                graphPatientDetailDTO.setPrincipalDiagnose(null!=patientDiagnose.getCie9()?
                        patientDiagnose.getCie9().getDescription(): EMPTY_STRING);
                graphPatientDetailDTO.setPrincipalIndication(null!=patientDiagnose.getIndication()?
                        patientDiagnose.getIndication().getDescription(): EMPTY_STRING);
                graphPatientDetailDTO.setPrincipalDiagnoseCie10(null!=patientDiagnose.getCie10()?
                        patientDiagnose.getCie10().getDescription(): EMPTY_STRING);
                fillPatientTreatmentInfoIntoGraphPatientDetailDTO(graphPatientDetailDTO, patientDiagnose);
            });
        }
        log.debug(String.format(DEBUG_QUERY_GRAPH_PATIENT_DETAIL, "END: Find PatientDiagnose"));
    }

    public static void fillPatientTreatmentInfoIntoGraphPatientDetailDTO(GraphPatientDetailDTO graphPatientDetailDTO, PatientDiagnose patientDiagnose) {
        log.debug(String.format(DEBUG_QUERY_GRAPH_PATIENT_DETAIL, "INIT: Find PatientTreatment"));
        if(CollectionUtils.isNotEmpty(patientDiagnose.getTreatments())) {
            String treatments = patientDiagnose.getTreatments()
                    .stream()
                    .filter(PatientTreatment::isActive)
                    .map(pt->null!=pt.getMedicine()
                            &&!StringUtils.isEmpty(pt.getMedicine().getActIngredients())?
                            pt.getMedicine().getActIngredients(): EMPTY_STRING)
                    .collect(Collectors.joining(DELIMITER_MEDICINES))
                    .replace(TARGET_DELIMITER_MEDICINES, EMPTY_STRING);
            graphPatientDetailDTO.setTreatment(treatments);
        }
        log.debug(String.format(DEBUG_QUERY_GRAPH_PATIENT_DETAIL, "END: Find PatientDiagnose"));
    }
    public static void orderGraphPatientDetailList(List<GraphPatientDetailDTO> graphPatientDetailList, Sort sort){
        Optional<Sort.Order> sortOrder = sort.get().findFirst();
        if (sortOrder.isPresent()){
            Sort.Order order = sortOrder.get();
            switch (order.getProperty()){
                case "nhc":
                    graphPatientDetailList
                            .sort(obtainComparatorString(order, GraphPatientDetailDTO::getNhc));
                    break;

                case "healthCard":
                    graphPatientDetailList
                            .sort(obtainComparatorString(order, GraphPatientDetailDTO::getHealthCard));
                    break;

                case "fullName":
                    graphPatientDetailList
                            .sort(obtainComparatorString(order, GraphPatientDetailDTO::getFullName));
                    break;

                case "principalIndication":
                    graphPatientDetailList
                            .sort(obtainComparatorString(order, GraphPatientDetailDTO::getPrincipalIndication));
                    break;

                case "principalDiagnose":
                    graphPatientDetailList
                            .sort(obtainComparatorString(order, GraphPatientDetailDTO::getPrincipalDiagnose));
                    break;

                case "principalDiagnoseCie10":
                    graphPatientDetailList
                            .sort(obtainComparatorString(order, GraphPatientDetailDTO::getPrincipalDiagnoseCie10));
                    break;

                case "treatment":
                    graphPatientDetailList
                            .sort(obtainComparatorString(order, GraphPatientDetailDTO::getTreatment));
                    break;

                case "pasi":
                    graphPatientDetailList
                            .sort(obtainComparatorString(order, GraphPatientDetailDTO::getPasi));
                    break;

                case "pasiDate":
                    graphPatientDetailList
                            .sort(obtainComparatorDate(order, GraphPatientDetailDTO::getPasiDate));
                    break;

                case "dlqi":
                    graphPatientDetailList
                            .sort(obtainComparatorString(order, GraphPatientDetailDTO::getDlqi));
                    break;

                case "dlqiDate":
                    graphPatientDetailList
                            .sort(obtainComparatorDate(order, GraphPatientDetailDTO::getDlqiDate));
                    break;

                default:
                    break;
            }
        }
    }

    private static Comparator<GraphPatientDetailDTO> obtainComparatorString(Sort.Order order, Function<GraphPatientDetailDTO, String> sortBy) {
        return order.isAscending()?
                Comparator.nullsLast(Comparator.comparing(sortBy)):Comparator.nullsLast(Comparator.comparing(sortBy)).reversed();
    }

    private static Comparator<GraphPatientDetailDTO> obtainComparatorDate(Sort.Order order, Function<GraphPatientDetailDTO, LocalDateTime> sortBy) {
        return order.isAscending()?
                Comparator.nullsLast(Comparator.comparing(sortBy)):Comparator.nullsLast(Comparator.comparing(sortBy)).reversed();
    }

}
