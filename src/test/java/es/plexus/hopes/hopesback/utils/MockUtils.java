package es.plexus.hopes.hopesback.utils;

import es.plexus.hopes.hopesback.controller.model.PathologyDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.repository.model.Pathology;
import es.plexus.hopes.hopesback.repository.model.Patient;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    public static Optional<Patient> mockPatient() {
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

    public static Pathology mockPathology(){
        Pathology pathology = new Pathology();
        pathology.setId(1L);
        pathology.setName("Dermatología");
        pathology.setDescription("Patologia Dermatología");
        return pathology;
    }

    public static String mockToken(){
        return "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsIkNMQUlNX1RPS0VOIjoiUk9MRV9BRE1JTiIsImlhdCI6MTYxMjI1ODQ3NCwiaXNzIjoiaG9wZXMiLCJleHAiOjE2MTIyODcyNzR9.PoQCWVBqNImxlpnNolGrbMM_YgX4hI5cUSX5vvVw8iU";
    }
}
