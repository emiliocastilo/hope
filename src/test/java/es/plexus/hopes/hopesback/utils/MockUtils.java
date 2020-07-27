package es.plexus.hopes.hopesback.utils;

import es.plexus.hopes.hopesback.controller.model.PathologyDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.repository.model.Hospital;

import java.time.LocalDateTime;
import java.util.HashSet;
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
}
