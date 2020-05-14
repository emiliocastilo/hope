package es.plexus.hopes.hopesback.controller.model;

import es.plexus.hopes.hopesback.repository.model.Hospital;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class PatientDTO {
    private Long id;
    private String name;
    private String firstSurname;
    private String lastSurname;
    private String nhc;
    private String healthCard;
    private String dni;
    private String address;
    private String phone;
    private String email;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private Hospital hospital;
    private String genderCode;
    private Set<PathologyDTO> pathologies = new HashSet<>();

}
