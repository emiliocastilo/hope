package es.plexus.hopes.hopesback.service;


import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.repository.PatientClinicalDataRepository;
import es.plexus.hopes.hopesback.repository.PatientDiagnosisRepository;
import es.plexus.hopes.hopesback.repository.PatientRepository;
import es.plexus.hopes.hopesback.repository.PatientTreatmentRepository;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientClinicalData;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import static es.plexus.hopes.hopesback.service.utils.GraphPatientDetailUtils.*;

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

    private final String PATIENS_BY_RAMS_SNC = "Toxicidad S.N.C.";
    private final String PATIENS_BY_RAMS_GASTRO_INSTESTINAL = "Toxicidad gastro-intestinal";
    private final String PATIENS_BY_RAMS_RENAL = "Toxicidad renal";
    private final String PATIENS_BY_RAMS_CUTANEA = "Toxicidad cutánea";
    private final String PATIENS_BY_RAMS_OSEA = "Toxicidad ósea";

    private final String PATIENTS_BY_TREATMENT_NAIVE_CHANGE = "Naive";
    private final String PATIENTS_BY_TREATMENT_FIRST_CHANGE = "1er cambio de tratamiento";
    private final String PATIENTS_BY_TREATMENT_SECOND_CHANGE = "2º cambio de tratamiento";
    private final String PATIENTS_BY_TREATMENT_THIRD_CHANGE = "3er cambio de tratamiento";
    private final String PATIENTS_BY_TREATMENT_OTHER_CHANGE = "Más de 3 cambios";

    private final String PATIENTS_BY_CAUSE_VIRAL_FAIL = "Fallo viral";
    private final String PATIENTS_BY_CAUSE_RAMS = "RAMs";
    private final String PATIENTS_BY_CAUSE_SIMPLIFICATION = "Simplificaciones";
    private final String PATIENTS_BY_CAUSE_VIRAL_INTERACTIONS = "Interacciones";
    private final String PATIENTS_BY_CAUSE_VIRAL_PREGNANCY = "Embarazo";
    private final String PATIENTS_BY_CAUSE_PATIENT_PREFERENCES = "Preferencias del paciente";
    private final String PATIENTS_BY_CAUSE_OTHER = "Otros";


    private final String PATIENTS_BY_FAILURE_PRIMARY = "Fallo Primario";
    private final String PATIENTS_BY_FAILURE_SECONDARY = "Fallo Secundario";


    private final String CVP = "CVP";
    private final String CD4 = "CD4";
    private final String RISK = "RISK";
    private final String VHC = "VHC";
    private final String VIRAL = "VIRAL";
    private final String RAMS = "RAMS";
    private final String FAILURE = "FAILURE";
    private final String TREATMENT_CHANGE = "CHANGEQUANTITY";
    private final String CAUSE = "CAUSE";


    private final PatientClinicalDataRepository patientClinicalDataRepository;
    private final PatientDiagnosisRepository patientDiagnosisRepository;
    private final PatientRepository patientRepository;
    private final PatientTreatmentRepository patientTreatmentRepository;

    public PatientsClinicalDataService(PatientClinicalDataRepository patientClinicalDataRepository, PatientRepository patientRepository, PatientDiagnosisRepository patientDiagnosisRepository, PatientTreatmentRepository patientTreatmentRepository) {
        this.patientClinicalDataRepository = patientClinicalDataRepository;
        this.patientRepository = patientRepository;
        this.patientDiagnosisRepository = patientDiagnosisRepository;
        this.patientTreatmentRepository = patientTreatmentRepository;
    }


    public Map<String, String> getPatientClinicalDataWithCVP() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName(CVP);
        return getPatientsGroupByCPV(patients);
    }

    public Map<String, String> getPatientClinicalDataWithCD4() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName(CD4);
        return getPatientsGroupByCD4(patients);
    }

    public Map<String, String> getPatientClinicalDataWithRiskGroup() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName(RISK);
        return getPatientsGroupByRiskGroup(patients);
    }

    public Map<String, String> getPatientClinicalDataWithViralInfection() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName(VIRAL);
        return getPatientsGroupByViralInfection(patients);
    }

    public Map<String, String> getPatientClinicalDataWithVhc() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName(VHC);
        return getPatientsGroupByVhc(patients);
    }

    public Map<String, String> getPatientClinicalDataWithRams() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName(RAMS);
        return getPatientsGroupByRams(patients);
    }

    public Map<String, String> getPatientClinicalDataWithViralFailure() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName(FAILURE);
        return getPatientsGroupByViralFailure(patients);
    }

    public Map<String, String> getPatientClinicalDataWithTreatmentChange() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName(TREATMENT_CHANGE);
        return getPatientsGroupByNumberTreatmentChanges(patients);
    }

    public Map<String, String> getPatientClinicalDataWithCause() {
        List<PatientClinicalData> patients = patientClinicalDataRepository.findByPCDName(CAUSE);
        return getPatientsGroupByCause(patients);
    }

    public Map<String, String> getPatientClinicalDataByType(String type) {
        Map<String, String> patientsClinicalData = new HashMap<>();
        switch (type.toUpperCase()) {
            case (CVP):
                patientsClinicalData = getPatientClinicalDataWithCVP();
                break;
            case CD4:
                patientsClinicalData = getPatientClinicalDataWithCD4();
                break;
            case RISK:
                patientsClinicalData = getPatientClinicalDataWithRiskGroup();
                break;
            case VIRAL:
                patientsClinicalData = getPatientClinicalDataWithViralInfection();
                break;
            case VHC:
                patientsClinicalData = getPatientClinicalDataWithVhc();
                break;
            case RAMS:
                patientsClinicalData = getPatientClinicalDataWithRams();
                break;
            case FAILURE:
                patientsClinicalData = getPatientClinicalDataWithViralFailure();
                break;
            case TREATMENT_CHANGE:
                patientsClinicalData = getPatientClinicalDataWithTreatmentChange();
                break;
            case CAUSE:
                patientsClinicalData = getPatientClinicalDataWithCause();
                break;
        }
        return patientsClinicalData;
    }

    public Page<GraphPatientDetailDTO> getPatientClinicalDataByTypeAndIndication(String type, String indication, Pageable pageable) {

        List<PatientClinicalData> patientClinicalData;

        indication = transformateIndication(indication);

        if (type.equals(CD4) || type.equals(CVP)) {
            patientClinicalData = patientClinicalDataRepository.findByPCDNameAndIndicationBetween(type, "0", indication);
        } else {
            patientClinicalData = patientClinicalDataRepository.findByPCDNameAndIndicationLike(type, indication);
        }

        List<Long> patientsId = new ArrayList<>();
        patientClinicalData.stream().forEach(pcd -> {
            patientsId.add(pcd.getPatient().getId());
        });

        List<Patient> patientList = patientRepository.findPatientsByPatientsId(patientsId);

        // por cada paciente, obtengo sus diagnósticos y los añado.
        patientList.forEach(patient -> {
            // Obtengo los diagnósticos del paciente
            List<PatientDiagnose> patientDiagnoses = patientDiagnosisRepository.findPatientsDiagnosibyPatientId(patient.getId());
            patientDiagnoses.stream().forEach(patientDiagnose -> {
                // Obtengo los tratamientos del diagnóstico
                List<PatientTreatment> patientTreatment = patientTreatmentRepository.findByPatientDiagnose(patientDiagnose);
                patientDiagnose.setPatient(new Patient());
                patientDiagnose.setTreatments(patientTreatment);
            });
            // Obtengo el CD4 y CVP del paciente
            PatientClinicalData cd4= patientClinicalDataRepository.findByPCDNameAndPatientId(CD4,patient.getId());
            PatientClinicalData cvp = patientClinicalDataRepository.findByPCDNameAndPatientId(CVP,patient.getId());
            List<PatientClinicalData> clinicalDatas = new ArrayList<>();

            if ( cd4 != null ){
                cd4.setPatient(new Patient());
            }
            if ( cvp != null ){
                cvp.setPatient(new Patient());
            }
            clinicalDatas.add(cd4);
            clinicalDatas.add(cvp);

            patient.setDiagnoses(patientDiagnoses);
            patient.setPatientClinicalDatas(clinicalDatas);
        });


        List<GraphPatientDetailDTO> graphPatientDetailList = fillGraphPatientDetailDtoListVIH(patientList);
         Page<GraphPatientDetailDTO> page = doPaginationGraphPatientDetailDTO(graphPatientDetailList, pageable);
        return page;
    }

    private String transformateIndication(String indication) {
        if ( indication.contains(" )")){
            return indication.replace(" )", "+)");
        }
        return indication;
    }

    private Map<String, String> getPatientsGroupByCPV(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        long patientsWihtMinusThan20 = 0;
        long patientsBetween20And50 = 0;
        long patientsBetween50And200 = 0;
        long patientsBetween200And500 = 0;
        long patientsBetween500And1000 = 0;
        long patientsWhitMoreThan1000 = 0;

        for (PatientClinicalData pdc : patients) {
            int value = Integer.parseInt(pdc.getValue());
            if (value < 20) {
                patientsWihtMinusThan20++;
            } else if (value < 50) {
                patientsBetween20And50++;
            } else if (value < 200) {
                patientsBetween50And200++;
            } else if (value < 500) {
                patientsBetween200And500++;
            } else if (value < 1000) {
                patientsBetween500And1000++;
            } else {
                patientsWhitMoreThan1000++;
            }
        }

        patientsMapped.put(PATIENS_BY_CVP_LESSER_THAN_20 + PATIENTS_BY_CVP_UNIT, String.valueOf(patientsWihtMinusThan20));
        patientsMapped.put(PATIENS_BY_CVP_BETWEEN_20_AND_50 + PATIENTS_BY_CVP_UNIT, String.valueOf(patientsBetween20And50));
        patientsMapped.put(PATIENS_BY_CVP_BETWEEN_50_AND_200 + PATIENTS_BY_CVP_UNIT, String.valueOf(patientsBetween50And200));
        patientsMapped.put(PATIENS_BY_CVP_BETWEEN_200_AND_500 + PATIENTS_BY_CVP_UNIT, String.valueOf(patientsBetween200And500));
        patientsMapped.put(PATIENS_BY_CVP_BETWEEN_500_AND_1000 + PATIENTS_BY_CVP_UNIT, String.valueOf(patientsBetween500And1000));
        patientsMapped.put(PATIENS_BY_CVP_GREATER_THAN_1000 + PATIENTS_BY_CVP_UNIT, String.valueOf(patientsWhitMoreThan1000));

        return patientsMapped;
    }

    private Map<String, String> getPatientsGroupByCD4(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        long patientsWithLesserThan100 = 0;
        long patientsBetween100And200 = 0;
        long patientsBetween200And350 = 0;
        long patientsBetween350And500 = 0;
        long patientsWhitMoreThan500 = 0;

        for (PatientClinicalData pdc : patients) {
            int value = Integer.parseInt(pdc.getValue());
            if (value < 100) {
                patientsWithLesserThan100++;
            } else if (value < 200) {
                patientsBetween100And200++;
            } else if (value < 350) {
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
        patientsMapped.put(PATIENS_BY_CD4_BETWEEN_350_AND_500 + PATIENTS_BY_CD4_UNIT, String.valueOf(patientsBetween350And500));
        patientsMapped.put(PATIENS_BY_CD4_GREATER_THAN_500 + PATIENTS_BY_CD4_UNIT, String.valueOf(patientsWhitMoreThan500));
        return patientsMapped;
    }

    private Map<String, String> getPatientsGroupByRiskGroup(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        int patientsHeterosexual = 0;
        int patiensHomosexual = 0;
        int patientsADVP = 0;
        int patientsVerticalTransmission = 0;

        for (PatientClinicalData pdc : patients) {
            switch (pdc.getValue().trim()) {
                case PATIENS_BY_RISK_GROUP_HETEROSEXUAL:
                    patientsHeterosexual++;
                    break;
                case PATIENS_BY_RISK_GROUP_HOMOSEXUAL:
                    patiensHomosexual++;
                    break;
                case PATIENS_BY_RISK_GROUP_ADVP:
                    patientsADVP++;
                    break;
                case PATIENS_BY_RISK_GROUP_VERTICAL_TRANSMISSION:
                    patientsVerticalTransmission++;
                    break;
            }
        }

        patientsMapped.put(PATIENS_BY_RISK_GROUP_HETEROSEXUAL, String.valueOf(patientsHeterosexual));
        patientsMapped.put(PATIENS_BY_RISK_GROUP_HOMOSEXUAL, String.valueOf(patiensHomosexual));
        patientsMapped.put(PATIENS_BY_RISK_GROUP_ADVP, String.valueOf(patientsADVP));
        patientsMapped.put(PATIENS_BY_RISK_GROUP_VERTICAL_TRANSMISSION, String.valueOf(patientsVerticalTransmission));
        return patientsMapped;
    }

    private Map<String, String> getPatientsGroupByViralInfection(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        int vihMonoInfected = 0;
        int VIHAndVHC = 0;
        int VIHAndVHB = 0;
        int VIHAndVHCAndVHB = 0;
        int withoutTest = 0;

        for (PatientClinicalData pdc : patients) {
            switch (pdc.getValue().trim()) {
                case PATIENS_BY_VIH_MONINFECTED:
                    vihMonoInfected++;
                    break;
                case PATIENS_BY_VIH_VHC:
                    VIHAndVHC++;
                    break;
                case PATIENS_BY_VIH_VHB:
                    VIHAndVHB++;
                    break;
                case PATIENS_BY_VIH_VHC_VHB:
                    VIHAndVHCAndVHB++;
                    break;
            }
        }

        patientsMapped.put(PATIENS_BY_VIH_MONINFECTED, String.valueOf(vihMonoInfected));
        patientsMapped.put(PATIENS_BY_VIH_VHC, String.valueOf(VIHAndVHC));
        patientsMapped.put(PATIENS_BY_VIH_VHB, String.valueOf(VIHAndVHB));
        patientsMapped.put(PATIENS_BY_VIH_VHC_VHB, String.valueOf(VIHAndVHCAndVHB));
        patientsMapped.put(PATIENS_BY_VIH_NO_TEST, String.valueOf(withoutTest));
        return patientsMapped;
    }

    private Map<String, String> getPatientsGroupByVhc(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        int vhcPositive = 0;
        int vhcNegative = 0;
        int vhcNoData = 0;

        for (PatientClinicalData pdc : patients) {
            switch (pdc.getValue().trim()) {
                case PATIENS_BY_RNA_VHC_POSITIVO:
                    vhcPositive++;
                    break;
                case PATIENS_BY_RNA_VHC_NEGATIVO:
                    vhcNegative++;
                    break;
                case PATIENS_BY_RNA_VHC_SIN_DATOS:
                    vhcNoData++;
                    break;
            }
        }

        patientsMapped.put(PATIENS_BY_RNA_VHC_POSITIVO, String.valueOf(vhcPositive));
        patientsMapped.put(PATIENS_BY_RNA_VHC_NEGATIVO, String.valueOf(vhcNegative));
        patientsMapped.put(PATIENS_BY_RNA_VHC_SIN_DATOS, String.valueOf(vhcNoData));

        return patientsMapped;
    }

    private Map<String, String> getPatientsGroupByRams(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        int toxicidadSNC = 0;
        int toxicidadGastrointestinal = 0;
        int toxicidadRenal = 0;
        int toxicidadCutanea = 0;
        int toxicidaOsea = 0;

        for (PatientClinicalData pdc : patients) {
            switch (pdc.getValue().trim()) {
                case PATIENS_BY_RAMS_SNC:
                    toxicidadSNC++;
                    break;
                case PATIENS_BY_RAMS_GASTRO_INSTESTINAL:
                    toxicidadGastrointestinal++;
                    break;
                case PATIENS_BY_RAMS_RENAL:
                    toxicidadRenal++;
                    break;
                case PATIENS_BY_RAMS_CUTANEA:
                    toxicidadCutanea++;
                    break;
                case PATIENS_BY_RAMS_OSEA:
                    toxicidaOsea++;
                    break;
            }
        }

        patientsMapped.put(PATIENS_BY_RAMS_SNC, String.valueOf(toxicidadSNC));
        patientsMapped.put(PATIENS_BY_RAMS_GASTRO_INSTESTINAL, String.valueOf(toxicidadGastrointestinal));
        patientsMapped.put(PATIENS_BY_RAMS_RENAL, String.valueOf(toxicidadRenal));
        patientsMapped.put(PATIENS_BY_RAMS_CUTANEA, String.valueOf(toxicidadCutanea));
        patientsMapped.put(PATIENS_BY_RAMS_OSEA, String.valueOf(toxicidaOsea));

        return patientsMapped;
    }

    private Map<String, String> getPatientsGroupByViralFailure(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        int primaryFailure = 0;
        int secondaryFailure = 0;

        for (PatientClinicalData pdc : patients) {
            switch (pdc.getValue().trim()) {
                case PATIENTS_BY_FAILURE_PRIMARY:
                    primaryFailure++;
                    break;
                case PATIENTS_BY_FAILURE_SECONDARY:
                    secondaryFailure++;
                    break;
            }
        }

        patientsMapped.put(PATIENTS_BY_FAILURE_PRIMARY, String.valueOf(primaryFailure));
        patientsMapped.put(PATIENTS_BY_FAILURE_SECONDARY, String.valueOf(secondaryFailure));

        return patientsMapped;
    }

    private Map<String, String> getPatientsGroupByNumberTreatmentChanges(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        int naive = 0;
        int firstChange = 0;
        int secondChange = 0;
        int thirdChange = 0;
        int otherChange = 0;

        for (PatientClinicalData pdc : patients) {
            switch (pdc.getValue().trim()) {
                case PATIENTS_BY_TREATMENT_NAIVE_CHANGE:
                    naive++;
                    break;
                case PATIENTS_BY_TREATMENT_FIRST_CHANGE:
                    firstChange++;
                    break;
                case PATIENTS_BY_TREATMENT_SECOND_CHANGE:
                    secondChange++;
                    break;
                case PATIENTS_BY_TREATMENT_THIRD_CHANGE:
                    thirdChange++;
                    break;
                case PATIENTS_BY_TREATMENT_OTHER_CHANGE:
                    otherChange++;
                    break;
            }
        }

        patientsMapped.put(PATIENTS_BY_TREATMENT_NAIVE_CHANGE, String.valueOf(naive));
        patientsMapped.put(PATIENTS_BY_TREATMENT_FIRST_CHANGE, String.valueOf(firstChange));
        patientsMapped.put(PATIENTS_BY_TREATMENT_SECOND_CHANGE, String.valueOf(secondChange));
        patientsMapped.put(PATIENTS_BY_TREATMENT_THIRD_CHANGE, String.valueOf(thirdChange));
        patientsMapped.put(PATIENTS_BY_TREATMENT_OTHER_CHANGE, String.valueOf(otherChange));

        return patientsMapped;
    }

    private Map<String, String> getPatientsGroupByCause(List<PatientClinicalData> patients) {
        Map<String, String> patientsMapped = new HashMap<>();
        int viralFail = 0;
        int rams = 0;
        int simplification = 0;
        int interaction = 0;
        int pregnancy = 0;
        int patientPreferences = 0;
        int otherCause = 0;

        for (PatientClinicalData pdc : patients) {
            switch (pdc.getValue().trim()) {
                case PATIENTS_BY_CAUSE_VIRAL_FAIL:
                    viralFail++;
                    break;
                case PATIENTS_BY_CAUSE_RAMS:
                    rams++;
                    break;
                case PATIENTS_BY_CAUSE_SIMPLIFICATION:
                    simplification++;
                    break;
                case PATIENTS_BY_CAUSE_VIRAL_INTERACTIONS:
                    interaction++;
                    break;
                case PATIENTS_BY_CAUSE_VIRAL_PREGNANCY:
                    pregnancy++;
                    break;
                case PATIENTS_BY_CAUSE_PATIENT_PREFERENCES:
                    patientPreferences++;
                    break;
                case PATIENTS_BY_CAUSE_OTHER:
                    otherCause++;
                    break;
            }
        }

        patientsMapped.put(PATIENTS_BY_CAUSE_VIRAL_FAIL, String.valueOf(viralFail));
        patientsMapped.put(PATIENTS_BY_CAUSE_RAMS, String.valueOf(rams));
        patientsMapped.put(PATIENTS_BY_CAUSE_SIMPLIFICATION, String.valueOf(simplification));
        patientsMapped.put(PATIENTS_BY_CAUSE_VIRAL_INTERACTIONS, String.valueOf(interaction));
        patientsMapped.put(PATIENTS_BY_CAUSE_VIRAL_PREGNANCY, String.valueOf(pregnancy));
        patientsMapped.put(PATIENTS_BY_CAUSE_PATIENT_PREFERENCES, String.valueOf(patientPreferences));
        patientsMapped.put(PATIENTS_BY_CAUSE_OTHER, String.valueOf(otherCause));

        return patientsMapped;
    }
}