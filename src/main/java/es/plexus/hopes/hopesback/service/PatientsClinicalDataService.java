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

    private final String PATIENS_BY_VIH_MONINFECTED = "VIH monoinfectado";
    private final String PATIENS_BY_VIH_VHC = "VIH / VHC (antiVHC+)";
    private final String PATIENS_BY_VIH_VHB = "VIH / VHB (HBsAg+)";
    private final String PATIENS_BY_VIH_VHC_VHB = "VIH / VHC (antiVHC+) / VHB (HBsAg+)";
    private final String PATIENS_BY_VIH_NO_TEST = "Sin pruebas";

    private final String PATIENS_BY_RNA_VHC_POSITIVO = "RNA-VHC positivo";
    private final String PATIENS_BY_RNA_VHC_NEGATIVO = "RNA-VHC negativo";
    private final String PATIENS_BY_RNA_VHC_SIN_DATOS = "Sin datos para RNA-VHC";

    private final PatientClinicalDataRepository patientClinicalDataRepository;

    public PatientsClinicalDataService(PatientClinicalDataRepository patientClinicalDataRepository) {
        this.patientClinicalDataRepository = patientClinicalDataRepository;
    }

    public Map<String,String> getPatientClinicalDataWithCVP() {
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
        return getPatientsGroupByViralInfection(patients);
    }
    public Map<String,String> getPatientClinicalDataWithVhc() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName("VHC");
        return getPatientsGroupByVhc(patients);
    }


    public List<GraphPatientDetailDTO> getPatientClinicalDataWithCVP(Collection<String> values) {
        List<PatientClinicalData> patientClinicalData = patientClinicalDataRepository.findByPCDNameAndValues("CPV", values);

        List<Patient> patientList = new ArrayList<>();
        patientClinicalData.stream().forEach(pcd -> {
            patientList.add(pcd.getPatient());
        });
       List<GraphPatientDetailDTO> graphPatientDetailList = fillGraphPatientDetailDtoList(patientList);
      return graphPatientDetailList;

    }

    public Page<GraphPatientDetailDTO> getPatientClinicalDataWithCVP(Collection<String> values, final Pageable pageable) {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDNameAndValues("CVP", values);

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
                patientsClinicalData =  getPatientClinicalDataWithCVP();
                break;
            case "CD4":
                patientsClinicalData =  getPatientClinicalDataWithCD4();
                break;
            case "RISK":
                patientsClinicalData =  getPatientClinicalDataWithRiskGroup();
                break;
            case "VIRAL":
                patientsClinicalData = getPatientClinicalDataWithViralInfection();
                break;
            case "VHC":
                patientsClinicalData = getPatientClinicalDataWithVhc();
                break;

        }
        return patientsClinicalData;
    }

    public Page<GraphPatientDetailDTO> getPatientClinicalDataByTypeAndIndication(String type, String indication,Pageable pageable) {
        Page<GraphPatientDetailDTO> patientsClinicalData = null;
        Collection<String> values = new ArrayList<>();
        values.add(indication);
        switch (type){
            case "CVP":
                return getPatientClinicalDataWithCVP(values, pageable);
          /*  case "CD4":
                patientsClinicalData =  getPatientClinicalDataWithCD4();
                break;
            case "RISK":
                patientsClinicalData =  getPatientClinicalDataWithRiskGroup();
                break;
            case "VIRAL":
                patientsClinicalData = getPatientClinicalDataWithViralInfection();
                break;
            case "VHC":
                patientsClinicalData = getPatientClinicalDataWithVhc();
                break*/

        }
        return patientsClinicalData;
    }

    private Map<String, String> getPatientsGroupByCPV(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        long patientsWihtMinusThan20 = 0;
        long patientsBetween20And50 = 0;
        long patientsBetween50And200 = 0;
        long patientsBetween200And500 = 0;
        long patientsBetween500And1000 = 0;
        long patientsWhitMoreThan1000 = 0;

        for ( PatientClinicalData pdc : patients){
            int value = Integer.parseInt(pdc.getValue());
            if (value < 20){
                patientsWihtMinusThan20++;
            } else if (value < 50) {
                patientsBetween20And50++;
            } else if (value < 200){
                patientsBetween50And200++;
            } else if (value < 500) {
                patientsBetween200And500++;
            } else if (value < 1000 ){
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
                case "vihmonoinfectado":
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

        patientsMapped.put(PATIENS_BY_VIH_MONINFECTED, String.valueOf(vihMonoInfected));
        patientsMapped.put(PATIENS_BY_VIH_VHC, String.valueOf(VIHAndVHC));
        patientsMapped.put(PATIENS_BY_VIH_VHB, String.valueOf(VIHAndVHB));
        patientsMapped.put( PATIENS_BY_VIH_VHC_VHB, String.valueOf(VIHAndVHCAndVHB));
        patientsMapped.put( PATIENS_BY_VIH_NO_TEST, String.valueOf(withoutTest));
        return patientsMapped;
    }

    private Map<String, String> getPatientsGroupByVhc(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        int vhcPositivo = 0;
        int vhcNegativo = 0;
        int vhcSinDatos = 0;

        for ( PatientClinicalData pdc : patients){
            switch ( pdc.getValue().toLowerCase().trim() ){
                case "vhcpositivo":
                    vhcPositivo++;
                    break;
                case "vhcnegativo":
                    vhcNegativo++;
                    break;
                case "vhcsindatos":
                    vhcSinDatos++;
                    break;
            }
        }

        patientsMapped.put(PATIENS_BY_RNA_VHC_POSITIVO, String.valueOf(vhcPositivo));
        patientsMapped.put(PATIENS_BY_RNA_VHC_NEGATIVO, String.valueOf(vhcNegativo));
        patientsMapped.put(PATIENS_BY_RNA_VHC_SIN_DATOS, String.valueOf(vhcSinDatos));

        return patientsMapped;
    }

}