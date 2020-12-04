package es.plexus.hopes.hopesback.service;


import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.PatientClinicalDataRepository;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientClinicalData;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import static es.plexus.hopes.hopesback.service.utils.GraphPatientDetailUtils.doPaginationGraphPatientDetailDTO;
import static es.plexus.hopes.hopesback.service.utils.GraphPatientDetailUtils.fillGraphPatientDetailDtoList;

@Log4j2
@Service
public class PatientsClinicalDataService {


    private final String PATIENS_BY_CVP_LESSER_THAN_20 = "<20";
    private final String PATIENS_BY_CVP_BETWEEN_20_AND_50 = "<Entre 20 y <50";
    private final String PATIENS_BY_CVP_BETWEEN_50_AND_200 = "Entre 50 y <200";
    private final String PATIENS_BY_CVP_BETWEEN_200_AND_500 = "Entre 200 y <500";
    private final String PATIENS_BY_CVP_BETWEEN_500_AND_1000 = "Entre 500 y <1000";
    private final String PATIENS_BY_CVP_GREATER_THAN_1000 = ">1000";

    private final String PATIENTS_BY_CVP_UNIT = " copias/uL";
    private final String PATIENTS_BY_CD4_UNIT = "";
    private final String PATIENS_BY_CD4_LESSER_THAN_100 = "<100";
    private final String PATIENS_BY_CD4_BETWEEN_100_AND_200 = "Entre 100 y <200";
    private final String PATIENS_BY_CD4_BETWEEN_200_AND_350 = "Entre 200 y <350";
    private final String PATIENS_BY_CD4_BETWEEN_350_AND_500 = "Entre 350 y <500";
    private final String PATIENS_BY_CD4_GREATER_THAN_500 = ">=500";

    private final String PATIENS_BY_RISK_GROUP_HETEROSEXUAL = "Heterosexual";
    private final String PATIENS_BY_RISK_GROUP_HOMOSEXUAL = "Homosexual";
    private final String PATIENS_BY_RISK_GROUP_ADVP = "ADVP";
    private final String PATIENS_BY_RISK_GROUP_VERTICAL_TRANSMISSION = "Transmisión vertical";

    private final PatientClinicalDataRepository patientClinicalDataRepository;

    public PatientsClinicalDataService(PatientClinicalDataRepository patientClinicalDataRepository) {
        this.patientClinicalDataRepository = patientClinicalDataRepository;
    }

    public Map<String,String> getPatientClinicalDataWithCPV() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName("CPV");
        return getPatientsGroupByCPV(patients);
    }

    public Map<String,String> getPatientClinicalDataWithCD4() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName("CD4");
        return getPatientsGroupByCD4(patients);
    }
    public Map<String,String> getPatientClinicalDataWithRiskGroup() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName("RISKGROUP");
        return getPatientsGroupByRiskGroup(patients);
    }
    public Map<String,String> getPatientClinicalDataWithViralInfection() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName("VIRALINFECTION");
        return getPatientsGroupByRiskGroup(patients);
    }

    public List<GraphPatientDetailDTO> getPatientClinicalDataWithCPV(Collection<String> values) {
        List<PatientClinicalData> patientClinicalData = patientClinicalDataRepository.findByPCDNameAndValues("CPV", values);

        List<Patient> patientList = new ArrayList<>();
        patientClinicalData.stream().forEach(pcd -> {
            patientList.add(pcd.getPatient());
        });
       List<GraphPatientDetailDTO> graphPatientDetailList = fillGraphPatientDetailDtoList(patientList);
      return graphPatientDetailList;

    }

    public Page<GraphPatientDetailDTO> getPatientClinicalDataWithCPV(Collection<String> values, final Pageable pageable) {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDNameAndValues("CPV", values);

        List<Patient> patientList = new ArrayList<>();
        patients.stream().forEach(pcd -> {
            patientList.add(pcd.getPatient());
        });

        List<GraphPatientDetailDTO> graphPatientDetailList = fillGraphPatientDetailDtoList(patientList);
        Page<GraphPatientDetailDTO> page = doPaginationGraphPatientDetailDTO(graphPatientDetailList, pageable);
        return page;
    }

    public Map<String, String> getPatientClinicalDataByType(String type) {
        Map<String, String> patientsClinicalData = new HashMap<>();
        switch (type){
            case "CVP":
                patientsClinicalData =  getPatientClinicalDataWithCPV();
                break;
            case "CD4":
                patientsClinicalData =  getPatientClinicalDataWithCD4();
                break;
            case "RISK":
                patientsClinicalData =  getPatientClinicalDataWithRiskGroup();
                break;
        }
        return patientsClinicalData;
    }

    private Map<String, String> getPatientsGroupByCPV(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        long patientsWihtMinusThan20 = 0;
        long patientsBetween20And50 = 0;
        long patientsBetween50And200 = 0;
        long patientsBetween200And500 =  0;
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

        patientsMapped.put(PATIENS_BY_CVP_LESSER_THAN_20 + PATIENTS_BY_CVP_UNIT, String.valueOf(patientsWihtMinusThan20));
        patientsMapped.put(PATIENS_BY_CVP_BETWEEN_20_AND_50 + PATIENTS_BY_CVP_UNIT, String.valueOf(patientsBetween20And50));
        patientsMapped.put(PATIENS_BY_CVP_BETWEEN_50_AND_200 + PATIENTS_BY_CVP_UNIT, String.valueOf(patientsBetween50And200));
        patientsMapped.put(PATIENS_BY_CVP_BETWEEN_200_AND_500 + PATIENTS_BY_CVP_UNIT , String.valueOf(patientsBetween200And500));
        patientsMapped.put(PATIENS_BY_CVP_BETWEEN_500_AND_1000 + PATIENTS_BY_CVP_UNIT , String.valueOf(patientsBetween500And1000));
        patientsMapped.put(PATIENS_BY_CVP_GREATER_THAN_1000 + PATIENTS_BY_CVP_UNIT , String.valueOf(patientsWhitMoreThan1000));

        return patientsMapped;
    }

    private Map<String, String> getPatientsGroupByCD4(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        long patientsWithLesserThan100 = 0;
        long patientsBetween100And200 = 0;
        long patientsBetween200And350 = 0;
        long patientsBetween350And500 =  0;
        long patientsWhitMoreThan500 = 0;

        for ( PatientClinicalData pdc : patients){
            int value = Integer.parseInt(pdc.getValue());
            if ( value < 100){
                patientsWithLesserThan100++;
            } else if (value < 200) {
                patientsBetween100And200++;
            } else if (value < 350){
                patientsBetween200And350++;
            } else if (value < 500) {
                patientsBetween350And500++;
            } else {
                patientsWhitMoreThan500++;
            }
        }

        patientsMapped.put(PATIENS_BY_CD4_LESSER_THAN_100 + PATIENTS_BY_CD4_UNIT, String.valueOf(patientsWithLesserThan100));
        patientsMapped.put(PATIENS_BY_CD4_BETWEEN_100_AND_200 + PATIENTS_BY_CD4_UNIT, String.valueOf(patientsBetween100And200));
        patientsMapped.put(PATIENS_BY_CD4_BETWEEN_200_AND_350 + PATIENTS_BY_CD4_UNIT, String.valueOf(patientsBetween200And350));
        patientsMapped.put(PATIENS_BY_CD4_BETWEEN_350_AND_500 + PATIENTS_BY_CD4_UNIT , String.valueOf(patientsBetween350And500));
        patientsMapped.put(PATIENS_BY_CD4_GREATER_THAN_500 + PATIENTS_BY_CD4_UNIT , String.valueOf(patientsWhitMoreThan500));
        return patientsMapped;
    }

    private Map<String, String> getPatientsGroupByRiskGroup(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        int patientsHeterosexual = 0;
        int patiensHomosexual = 0;
        int patientsADVP = 0;
        int patientsVerticalTransmission = 0;

        for ( PatientClinicalData pdc : patients){
            switch ( pdc.getValue().toLowerCase().trim() ){
                case "heterosexual":
                    patientsHeterosexual++;
                    break;
                case "homosexual":
                    patiensHomosexual++;
                    break;
                case "advp":
                    patientsADVP++;
                    break;
                case "TransmisionVertical":
                    patientsVerticalTransmission++;
                    break;
            }
        }

        patientsMapped.put(PATIENS_BY_RISK_GROUP_HETEROSEXUAL, String.valueOf(patientsHeterosexual));
        patientsMapped.put(PATIENS_BY_RISK_GROUP_HOMOSEXUAL, String.valueOf(patiensHomosexual));
        patientsMapped.put(PATIENS_BY_RISK_GROUP_ADVP, String.valueOf(patientsADVP));
        patientsMapped.put( PATIENS_BY_RISK_GROUP_VERTICAL_TRANSMISSION, String.valueOf(patientsVerticalTransmission));
        return patientsMapped;
    }

    private Map<String, String> getPatientsGroupByViralInfection(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        int vihMonoInfected = 0;
        int VIHAndVHC = 0;
        int VIHAndVHB = 0;
        int VIHAndVHCAndVHB = 0;
        int withoutTest = 0;

        for ( PatientClinicalData pdc : patients){
            switch ( pdc.getValue().toLowerCase().trim() ){
                case "vih monoinfectado":
                    vihMonoInfected++;
                    break;
                case "vih/vhc":
                    VIHAndVHC++;
                    break;
                case "vih/vhb":
                    VIHAndVHB++;
                    break;
                case "vih/vhc/vhb":
                    VIHAndVHCAndVHB++;
                    break;
            }
        }

        patientsMapped.put(PATIENS_BY_RISK_GROUP_HETEROSEXUAL, String.valueOf(vihMonoInfected));
        patientsMapped.put(PATIENS_BY_RISK_GROUP_HOMOSEXUAL, String.valueOf(VIHAndVHC));
        patientsMapped.put(PATIENS_BY_RISK_GROUP_ADVP, String.valueOf(VIHAndVHB));
        patientsMapped.put( PATIENS_BY_RISK_GROUP_VERTICAL_TRANSMISSION, String.valueOf(VIHAndVHCAndVHB));
        return patientsMapped;
    }
}