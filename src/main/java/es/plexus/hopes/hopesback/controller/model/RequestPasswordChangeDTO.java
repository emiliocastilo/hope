package es.plexus.hopes.hopesback.controller.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RequestPasswordChangeDTO {
    @NotBlank(message = "email cannot be blank")
    @Email(message = "email has to be a valid email")
    private String email;

}
