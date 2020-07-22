package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.controller.model.IndicationDTO;
import es.plexus.hopes.hopesback.controller.model.InputDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDiagnosisDTO;
import es.plexus.hopes.hopesback.controller.model.PatientTreatmentDTO;
import es.plexus.hopes.hopesback.repository.model.Indication;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
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
    private PatientService patientService;

    @Mock
    private PatientDiagnosisService patientDiagnosisService;

    @Mock
    private IndicationService indicationService;

    @Test
    public void callMigrationDataDiagnosisFromNoRelationalToRelationalShouldBeStatusOk() {

        List<FormDTO> formDTOList = new ArrayList<>();
        formDTOList.add(mockPrincipalDiagnosis());
        //given
        given(formService.findByTemplate(anyString())).willReturn(formDTOList);
        given(patientService.findById(anyLong())).willReturn(MockUtils.mockPatientDTO());
        given(patientDiagnosisService.findByPatient(any(Patient.class))).willReturn(mockPatientDiagnosisDTO());
        given(indicationService.getIndicationByDescription(anyString())).willReturn(mockIndicationDTO());
        //when
        migrationService.migrationDataDiagnosisFromNoRelationalToRelational();
        //then
        verify(patientDiagnosisService, times(1)).save(any(PatientDiagnose.class));
    }

    @Test
    public void callMigrationDataPharmacologyTreatmentFromNoRelationalToRelationalShouldBeStatusOk() {

        List<FormDTO> formDTOList = new ArrayList<>();
        formDTOList.add(mockPharmacologyTreatment());
        //given
        given(formService.findByTemplate(anyString())).willReturn(formDTOList);
        given(patientService.findById(anyLong())).willReturn(MockUtils.mockPatientDTO());
        given(indicationService.getIndicationByDescription(anyString())).willReturn(mockIndicationDTO());
        given(patientDiagnosisService.findByPatientAndIndication(any(Patient.class), any(Indication.class))).willReturn(mockPatientDiagnosisDTO());
        given(patientTreatmentService.findByMasterFormulaAndMasterFormulaDose(anyString(), anyString())).willReturn(mockPatientTreatmentDTO());
        //when
        migrationService.migrationDataPharmacologyTreatmentFromNoRelationalToRelational();
        //then
        verify(patientTreatmentService, times(1)).save(any(PatientTreatment.class));
    }

    private PatientTreatmentDTO mockPatientTreatmentDTO() {
        PatientTreatmentDTO patientTreatmentDTO = new PatientTreatmentDTO();
        patientTreatmentDTO.setActive(true);
        patientTreatmentDTO.setDose("dosis");
        patientTreatmentDTO.setInitDate(LocalDateTime.now());
        patientTreatmentDTO.setFinalDate(LocalDateTime.now());

        return patientTreatmentDTO;
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

    private PatientDiagnosisDTO mockPatientDiagnosisDTO() {

        PatientDiagnosisDTO patientDiagnosisDTO = new PatientDiagnosisDTO();
        patientDiagnosisDTO.setCieCode("001");
        patientDiagnosisDTO.setCieDescription("COLERA");
        patientDiagnosisDTO.setDerivationDate(LocalDateTime.now());
        patientDiagnosisDTO.setId(1L);
        patientDiagnosisDTO.setIndication(mockIndicationDTO());
        patientDiagnosisDTO.setInitDate(LocalDateTime.now());
        patientDiagnosisDTO.setPatient(MockUtils.mockPatientDTO());

        return patientDiagnosisDTO;
    }

    private IndicationDTO mockIndicationDTO() {

        IndicationDTO indicationDTO = new IndicationDTO();
        indicationDTO.setDescription("descripción");
        indicationDTO.setId(1L);
        indicationDTO.setPathologyId(2L);
        return indicationDTO;
    }

    private FormDTO mockPharmacologyTreatment() {
        InputDTO inputDTO = new InputDTO();
        inputDTO.setName("masterFormula");
        inputDTO.setType("checkbox");
        inputDTO.setValue(true);

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

