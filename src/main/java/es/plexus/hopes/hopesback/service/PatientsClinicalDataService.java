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
import static java.util.stream.Collectors.*;

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


    private final String CVP = "CVP";
    private final String CD4 = "CD4";


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

    public Map<String, Long> getPatientClinicalDataByType(String type) {
        Map<String, Long> patientsClinicalData;

        List<PatientClinicalData> patients = patientClinicalDataRepository.findByName(type.toUpperCase());

        if (type.equals(CD4) || type.equals(CVP)) {
            patients.forEach(patientClinicalData -> {
                patientClinicalData.setValue(getTextByNameAndValue(type, Long.valueOf(patientClinicalData.getValue())));
            });
        }

        patientsClinicalData = patients.stream().collect(groupingBy(PatientClinicalData::getValue, counting()));

        return patientsClinicalData;
    }

    private String getTextByNameAndValue(String name, Long value) {

        String textEdited = name;

        if (name.equals(CVP)) {

            if (value < 20) {
                textEdited = PATIENS_BY_CVP_LESSER_THAN_20;
            } else if (value < 50) {
                textEdited = PATIENS_BY_CVP_BETWEEN_20_AND_50;
            } else if (value < 200) {
                textEdited = PATIENS_BY_CVP_BETWEEN_20_AND_50;
            } else if (value < 500) {
                textEdited = PATIENS_BY_CVP_BETWEEN_200_AND_500;
            } else if (value >= 1000) {
                textEdited = PATIENS_BY_CVP_GREATER_THAN_1000;
            } else {
                textEdited = PATIENS_BY_CVP_BETWEEN_500_AND_1000;
            }

        } else if (name.equals(CD4)) {

            if (value < 100) {
                textEdited = PATIENS_BY_CD4_LESSER_THAN_100;
            } else if (value < 200) {
                textEdited = PATIENS_BY_CD4_BETWEEN_100_AND_200;
            } else if (value < 350) {
                textEdited = PATIENS_BY_CD4_BETWEEN_200_AND_350;
            } else if (value < 500) {
                textEdited = PATIENS_BY_CD4_BETWEEN_350_AND_500;
            } else {
                textEdited = PATIENS_BY_CD4_GREATER_THAN_500;
            }

        }
        return textEdited;
    }

    public Page<GraphPatientDetailDTO> getPatientClinicalDataByTypeAndIndication(String type, String indication, Pageable pageable) {

        List<PatientClinicalData> patientClinicalData;

        indication = transformateIndication(indication);

        if (type.equals(CD4) || type.equals(CVP)) {
            patientClinicalData = patientClinicalDataRepository.findByPCDNameAndIndicationBetween(type, "0", indication);
        } else {
            patientClinicalData = patientClinicalDataRepository.findByNameAndValueLike(type, indication);
        }

        List<Long> patientsId = new ArrayList<>();
        patientClinicalData.forEach(pcd -> {
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
            PatientClinicalData cd4 = patientClinicalDataRepository.findByPCDNameAndPatientId(CD4, patient.getId());
            PatientClinicalData cvp = patientClinicalDataRepository.findByPCDNameAndPatientId(CVP, patient.getId());
            List<PatientClinicalData> clinicalDatas = new ArrayList<>();

            if (cd4 != null) {
                cd4.setPatient(new Patient());
            }
            if (cvp != null) {
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
        if (indication.contains(" )")) {
            return indication.replace(" )", "+)");
        }
        return indication;
    }
}