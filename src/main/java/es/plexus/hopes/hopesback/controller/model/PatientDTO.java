package es.plexus.hopes.hopesback.controller.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import es.plexus.hopes.hopesback.repository.model.Hospital;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    @ApiModelProperty(position = 20, example = "1981-01-01T00:00:00Z", value = "Fecha de carga de las dispensaciones")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime birthDate;
    private Hospital hospital;
    private String genderCode;
    private Set<PathologyDTO> pathologies = new HashSet<>();

}
