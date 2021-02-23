package es.plexus.hopes.hopesback.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDTO {

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 200)
    private String password;

    private String role;
}
