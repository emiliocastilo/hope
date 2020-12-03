package es.plexus.hopes.hopesback.service;


import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.PatientClinicalDataRepository;
import es.plexus.hopes.hopesback.repository.model.PatientClinicalData;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class PatientsClinicalDataService {


    private final String PATIENS_BY_CVP_LESSER_THAN_20 = "<20";
    private final String PATIENS_BY_CVP_BETWEEN_20_AND_50 = "<Entre 20 y <50";
    private final String PATIENS_BY_CVP_BETWEEN_50_AND_200 = "Entre 50 y <200";
    private final String PATIENS_BY_CVP_BETWEEN_200_AND_500 = "Entre 200 y <500";
    private final String PATIENS_BY_CVP_BETWEEN_500_AND_1000 = "Entre 500 y <1000";
    private final String PATIENS_BY_CVP_GREATER_THAN_1000 = "Entre 500 y <1000";
    private final String  PATIENTS_BY_CVP_UNIT = " copias/uL";

    private final PatientClinicalDataRepository patientClinicalDataRepository;

    public PatientsClinicalDataService(PatientClinicalDataRepository patientClinicalDataRepository) {
        this.patientClinicalDataRepository = patientClinicalDataRepository;
    }

    public Map<String,Long> getPatientsCVP() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName("CPV");
        return getPatientsGroupByCPV(patients);
    }

    private Map<String, Long> getPatientsGroupByCPV(List<PatientClinicalData> patients) {
        Map<String, Long> patientsMapped = new HashMap<>();
        long patientsWihtMinusThan20 = 0;
        long patientsBetween20And50 = 0;
        long patientsBetween50And200 = 0;
        long patientsBetween200And500 = 0;
        long patientsBetween500And1000 = 0;
        long patientsWhitMoreThan1000 = 0;

        for ( PatientClinicalData pdc : patients){
            int value = Integer.parseInt(pdc.getValue());
            if ( value < 20){
                patientsWihtMinusThan20++;
            } else if (value < 50) {
                patientsBetween20And50++;
            } else if (value < 200){
                patientsBetween50And200++;
            } else if (value < 500) {
                patientsBetween200And500++;
            } else if ( value < 1000 ){
                patientsBetween500And1000++;
            } else {
                patientsWhitMoreThan1000++;
            }
        }

        patientsMapped.put(PATIENS_BY_CVP_LESSER_THAN_20 + PATIENTS_BY_CVP_UNIT, patientsWihtMinusThan20);
        patientsMapped.put(PATIENS_BY_CVP_BETWEEN_20_AND_50 + PATIENTS_BY_CVP_UNIT, patientsBetween20And50);
        patientsMapped.put(PATIENS_BY_CVP_BETWEEN_50_AND_200 + PATIENTS_BY_CVP_UNIT, patientsBetween50And200);
        patientsMapped.put(PATIENS_BY_CVP_BETWEEN_200_AND_500 + PATIENTS_BY_CVP_UNIT , patientsBetween200And500);
        patientsMapped.put(PATIENS_BY_CVP_BETWEEN_500_AND_1000 + PATIENTS_BY_CVP_UNIT , patientsBetween500And1000);
        patientsMapped.put(PATIENS_BY_CVP_GREATER_THAN_1000+ PATIENTS_BY_CVP_UNIT, patientsWhitMoreThan1000);

        return patientsMapped;
    }

    /*public List<PatientClinicalData> getPatientsCVP(Collection<String> values) {
      return fillGraphPatientDetailDtoList(patientClinicalDataRepository.findByPCDNameAndValues("CPV", values));
    }*/

    public Page<GraphPatientDetailDTO> getPatientsCVP(Collection<String> values, final Pageable pageable) {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDNameAndValues("CPV", values);
       // List<GraphPatientDetailDTO> graphPatientDetailList = fillGraphPatientDetailDtoList(patients);
        //Page<GraphPatientDetailDTO> page = doPaginationGraphPatientDetailDTO(graphPatientDetailList, pageable);
        return null;
    }

}