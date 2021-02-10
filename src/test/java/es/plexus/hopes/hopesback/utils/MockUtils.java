package es.plexus.hopes.hopesback.utils;

import es.plexus.hopes.hopesback.configuration.security.TokenProvider;
import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.controller.model.GraphPatientDetailDTO;
import es.plexus.hopes.hopesback.controller.model.PathologyDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.repository.model.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static es.plexus.hopes.hopesback.configuration.security.Constants.SECOND_TOKEN_EXPIRATION_TIME;

public class MockUtils {

    public static PatientDTO mockPatientDTO() {
        PatientDTO patientDto = new PatientDTO();

        patientDto.setAddress("Calle Falsa, 1");
        patientDto.setBirthDate(LocalDateTime.now());
        patientDto.setDni("012345678W");
        patientDto.setEmail("email@hopes.com");
        patientDto.setId(1L);
        patientDto.setName("Peter");
        patientDto.setFirstSurname("Perez");
        patientDto.setLastSurname("Perez");
        patientDto.setGenderCode("M");
        patientDto.setNhc("NHC0001");
        patientDto.setHealthCard("HC0001");
        patientDto.setPhone("+356663");

        Hospital hospital = new Hospital();
        hospital.setId(1L);
        patientDto.setHospital(hospital);

        Set<PathologyDTO> pathologies = new HashSet<>();
        PathologyDTO pathology = new PathologyDTO();
        pathologies.add(pathology);
        patientDto.setPathologies(pathologies);
        return patientDto;
    }

    public static Optional<Patient> mockOptionalPatient() {
        Patient patient = new Patient();

        patient.setAddress("Calle Falsa, 1");
        patient.setBirthDate(LocalDateTime.now());
        patient.setDni("012345678W");
        patient.setEmail("email@hopes.com");
        patient.setId(1L);
        patient.setName("Peter");
        patient.setFirstSurname("Perez");
        patient.setLastSurname("Perez");
        patient.setGenderCode("M");
        patient.setNhc("NHC0001");
        patient.setHealthCard("HC0001");
        patient.setPhone("+356663");

        Hospital hospital = new Hospital();
        hospital.setId(1L);
        patient.setHospital(hospital);

        Set<Pathology> pathologies = new HashSet<>();
        Pathology pathology = new Pathology();
        pathologies.add(pathology);
        patient.setPathologies(pathologies);
        return Optional.of(patient);
    }

    public static Patient mockPatient() {
        Patient patient = new Patient();
        patient.setHealthOutcomes(Collections.singletonList(new HealthOutcome()));
        patient.setName("name");
        patient.setNhc("nhc");
        patient.setDiagnoses(Collections.singletonList(new PatientDiagnose()));

        Set<Pathology> pathologies = new HashSet<>();
        pathologies.add(mockPathology());
        patient.setPathologies(pathologies);
        return patient;
    }

    public static Pathology mockPathology(){
        Pathology pathology = new Pathology();
        pathology.setId(1L);
        pathology.setName("Dermatología");
        pathology.setDescription("Patologia Dermatología");
        return pathology;
    }

    public static String mockToken(){
        return TokenProvider.generateToken("admin","ROLE_ADMIN" , SECOND_TOKEN_EXPIRATION_TIME);
    }


    public static HealthOutcome mockHealthOutcome(){
        HealthOutcome healthOutcome = new HealthOutcome();
        healthOutcome.setPatient(new Patient());
        healthOutcome.setDate(LocalDateTime.now());
        healthOutcome.setId(1L);
        healthOutcome.setIndexType("PASI");
        healthOutcome.setResult("Result");
        healthOutcome.setValue(new BigDecimal(0.0));
        return healthOutcome;
    }

    public static Map<String, Map<String, BigDecimal>> mockMapMapStringString() {
        Map<String, Map<String, BigDecimal>> map = new HashMap<>();
        Map<String, BigDecimal> subMap = new HashMap<>();
        subMap.put("Type", BigDecimal.ONE);
        map.put("key", subMap);
        return map;
    }

    public static DispensationDetailDTO mockDispensationDetailDTO() {
        DispensationDetailDTO dispensationDTO = new DispensationDetailDTO();
        dispensationDTO.setId(1L);
        dispensationDTO.setDispensation(null);
        dispensationDTO.setAmount(new BigDecimal(2));
        dispensationDTO.setQuantity("quantity");
        dispensationDTO.setDescription("description");
        dispensationDTO.setNhc("nhc");
        dispensationDTO.setDaysDispensation(1);
        dispensationDTO.setCode("code");
        dispensationDTO.setNationalCode(1);
        return dispensationDTO;
    }

    public static PageImpl<DispensationDetailDTO> mockPageDispensation(PageRequest pageRequest) {
        return new PageImpl<>(Collections.singletonList(mockDispensationDetailDTO()), pageRequest, 1);
    }

    public static String mockJsonDispensationDetail() {
        String json = "{\"code\":\"" + mockDispensationDetailDTO().getCode() + "\"}";
        return json;
    }

    public static PageRequest mockPageRequest() {
        return PageRequest.of(0, 5, Sort.by("test"));
    }
    //private final PageRequest mockPageRequest() {
    //	return PageRequest.of(1, 5, Sort.by("id"));
    //}

    public static Pageable mockPageable(){
        return PageRequest.of(1, 5);
    }

    public static  GraphPatientDetailDTO mockGraphPatientDetailsDTO() {
        GraphPatientDetailDTO graphPatientDetailDTO =
                new GraphPatientDetailDTO();

        graphPatientDetailDTO.setId(1L);
        graphPatientDetailDTO.setNhc("NOHC0001");
        graphPatientDetailDTO.setHealthCard("HC0001");
        graphPatientDetailDTO.setFullName("Nombre completo");
        graphPatientDetailDTO.setPrincipalIndication("Indication");
        graphPatientDetailDTO.setPrincipalDiagnose("Diagnose CIE");
        graphPatientDetailDTO.setTreatment("Treatment");
        graphPatientDetailDTO.setPasi("PASI Result");
        graphPatientDetailDTO.setPasiDate(LocalDateTime.now());
        graphPatientDetailDTO.setDlqi("DLQI Result");
        graphPatientDetailDTO.setDlqiDate(LocalDateTime.now());

        return graphPatientDetailDTO;
    }

    public static PageImpl<GraphPatientDetailDTO> getPageableGraphPatientDetail(PageRequest pageRequest) {
        return new PageImpl<>(Collections.singletonList(mockGraphPatientDetailsDTO()), pageRequest, 1);
    }

    public static Map<String, Long> mockMapStringLong() {
        Map<String, Long> map = new HashMap();
        map.put("Type", 3L);
        return map;
    }
}
