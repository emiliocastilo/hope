package es.plexus.hopes.hopesback.controller.model;

import es.plexus.hopes.hopesback.repository.model.ERole;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginDTO {

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 200)
    private String password;

    private String role;
}
