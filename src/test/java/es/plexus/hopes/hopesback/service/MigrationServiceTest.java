package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.controller.model.InputDTO;
import es.plexus.hopes.hopesback.repository.IndicationRepository;
import es.plexus.hopes.hopesback.repository.MedicineRepository;
import es.plexus.hopes.hopesback.repository.PatientDiagnosisRepository;
import es.plexus.hopes.hopesback.repository.PatientRepository;
import es.plexus.hopes.hopesback.repository.PatientTreatmentRepository;
import es.plexus.hopes.hopesback.repository.model.Indication;
import es.plexus.hopes.hopesback.repository.model.Medicine;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.repository.model.PatientDiagnose;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import es.plexus.hopes.hopesback.service.migrate.MigrationService;
import es.plexus.hopes.hopesback.utils.MockUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MigrationServiceTest {

    @InjectMocks
    private MigrationService migrationService;

    @Mock
    private PatientTreatmentService patientTreatmentService;

    @Mock
    private FormService formService;

    @Mock
    private PatientDiagnosisService patientDiagnosisService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private IndicationRepository indicationRepository;

    @Mock
    private PatientDiagnosisRepository patientDiagnosisRepository;

    @Mock
    private PatientTreatmentRepository patientTreatmentRepository;

    @Mock
    private MedicineRepository medicineRepository;

    @Test
    public void callMigrationDataDiagnosisFromNoRelationalToRelationalShouldBeStatusOk() {

        List<FormDTO> formDTOList = new ArrayList<>();
        formDTOList.add(mockPrincipalDiagnosis());
        //given
        given(formService.findByTemplate(anyString())).willReturn(formDTOList);
        given(patientRepository.findById(anyLong())).willReturn(MockUtils.mockPatient());
        given(patientDiagnosisRepository.findByPatient(any(Patient.class))).willReturn(mockPatientDiagnosis());
        given(indicationRepository.findByDescription(anyString())).willReturn(mockIndication());
        //when
        migrationService.migrationDataDiagnosisFromNoRelationalToRelational();
        //then
        verify(patientDiagnosisService, times(1)).save(any(PatientDiagnose.class));
    }

    @Test
    public void callMigrationDataTreatmentFromNoRelationalToRelationalShouldBeStatusOk() {

        List<FormDTO> formDTOList = new ArrayList<>();
        formDTOList.add(mockPharmacologyTreatment());
        List<FormDTO> formDTOPhototherapyList = new ArrayList<>();
        formDTOPhototherapyList.add(mockPharmacologyTreatment());
        //given
        given(formService.findByTemplateAndJob("farmacology-treatment", true)).willReturn(formDTOList);
        given(patientRepository.findById(anyLong())).willReturn(MockUtils.mockPatient());
        given(indicationRepository.findByDescription(anyString())).willReturn(mockIndication());
        given(patientDiagnosisRepository.findByPatientIdAndIndicationId(anyLong(), anyLong())).willReturn(Optional.of(mockPatientDiagnosis()));
        given(patientTreatmentRepository.findByPatientDiagnoseAndMasterFormulaIgnoreCaseAndMasterFormulaDoseIgnoreCaseAndTypeIgnoreCase(any(PatientDiagnose.class), anyString(), anyString(), anyString())).willReturn(mockPatientTreatment());
        given(medicineRepository.findByNationalCode(anyString())).willReturn(mockMedicine());
        //when
        migrationService.migrationDataTreatmentFromNoRelationalToRelational();
        //then
        verify(patientTreatmentService, times(1)).save(any(PatientTreatment.class));
    }

    private Optional<PatientTreatment> mockPatientTreatment() {
        PatientTreatment patientTreatment = new PatientTreatment();
        patientTreatment.setActive(true);
        patientTreatment.setDose("dosis");
        patientTreatment.setInitDate(LocalDateTime.now());
        patientTreatment.setFinalDate(LocalDateTime.now());

        return Optional.of(patientTreatment);
    }

    private FormDTO mockPrincipalDiagnosis() {

        InputDTO inputDTO = new InputDTO();
        inputDTO.setName("psoriasisType");
        inputDTO.setType("select");
        inputDTO.setValue("OTRAS");

        List<InputDTO> inputDTOList = new ArrayList<>();
        inputDTOList.add(inputDTO);

        FormDTO formDTO = new FormDTO();
        formDTO.setDateTime(LocalDateTime.now());
        formDTO.setPatientId(34);
        formDTO.setTemplate("principal-diagnosis");
        formDTO.setData(inputDTOList);

        return formDTO;
    }

    private PatientDiagnose mockPatientDiagnosis() {

        PatientDiagnose patientDiagnosisDTO = new PatientDiagnose();
        patientDiagnosisDTO.setCieCode("001");
        patientDiagnosisDTO.setCieDescription("COLERA");
        patientDiagnosisDTO.setDerivationDate(LocalDateTime.now());
        patientDiagnosisDTO.setId(1L);
        patientDiagnosisDTO.setIndication(new Indication());
        patientDiagnosisDTO.setInitDate(LocalDateTime.now());
        patientDiagnosisDTO.setPatient(new Patient());

        return patientDiagnosisDTO;
    }

    private Optional<Indication> mockIndication() {

        Indication indication = new Indication();
        indication.setDescription("descripción");
        indication.setId(1L);
        indication.setPathologyId(2L);
        return Optional.of(indication);
    }

    private Optional<Medicine> mockMedicine() {

        Medicine medicine = new Medicine();
        medicine.setDescription("descripción");
        medicine.setId(1L);
        return Optional.of(medicine);
    }

    private FormDTO mockPharmacologyTreatment() {
        ArrayList<LinkedHashMap<String, Object>> array = new ArrayList<>();
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("treatmentType", "BIOLOGICO");
        map.put("indication", "OTRAS");
        map.put("specialIndication", false);
        map.put("bigPsychologicalImpact", false);
        map.put("visibleInjury", false);
        map.put("others", "");
        LinkedHashMap<String, Object> medicineMap = new LinkedHashMap<>();
        medicineMap.put("actIngredients", "ibuprofeno");
        medicineMap.put("id", "1");
        map.put("medicine", medicineMap);
        map.put("family", "QUÍMICO");
        map.put("atc", "M01AE01");
        map.put("cn", "653306");
        map.put("tract", "oral");
        LinkedHashMap<String, Object> doseMap = new LinkedHashMap<>();
        doseMap.put("name", "ibuprofeno");
        map.put("dose", doseMap);
        map.put("otherDosis", "");
        LinkedHashMap<String, Object> regimenTreatmentMap = new LinkedHashMap<>();
        regimenTreatmentMap.put("name", "Estandar");
        map.put("regimenTreatment", regimenTreatmentMap);
        map.put("datePrescription", "2020-11-23T00:00:00.000Z\"");
        map.put("dateStart", "2020-11-30T00:00:00.000Z");
        map.put("expectedEndDate", "");
        map.put("observations", "");
        map.put("otherDosis", "prueba de ibuprofeno");
        map.put("treatmentContinue", false);
        map.put("treatmentPulsatil", false);
        map.put("reasonChangeOrSuspension", null);
        map.put("dateSuspension", null);
        map.put("principle", "ibuprofeno");
        map.put("brand", "Motrin");
        map.put("type", "QUÍMICO");
        array.add(map);
        InputDTO inputDTO = new InputDTO();
        inputDTO.setName("table");
        inputDTO.setType("principal-treatment");
        inputDTO.setValue(array);

        List<InputDTO> inputDTOList = new ArrayList<>();
        inputDTOList.add(inputDTO);

        FormDTO formDTO = new FormDTO();
        formDTO.setDateTime(LocalDateTime.now());
        formDTO.setPatientId(34);
        formDTO.setTemplate("farmacology-treatment");
        formDTO.setData(inputDTOList);

        return formDTO;
    }

}

