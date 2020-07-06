package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.configuration.MailTemplateConfiguration;
import es.plexus.hopes.hopesback.controller.model.SupportDTO;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.mail.MailService;
import es.plexus.hopes.hopesback.service.mail.TemplateMail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Controlador soporte de incidencias", tags = "support")
@Log4j2
@RestController
@RequestMapping(SupportController.SUPPORT_MAPPING)
public class SupportController {

    static final String SUPPORT_MAPPING = "/support";

    private final MailService mailService;
    private final MailTemplateConfiguration mailTemplateConfiguration;

    public SupportController(MailService mailService, MailTemplateConfiguration mailTemplateConfiguration) {
        this.mailService = mailService;
        this.mailTemplateConfiguration = mailTemplateConfiguration;
    }

    @ApiOperation("Nueva petición a soporte")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void sendRequestToSupport(@RequestBody @Valid final SupportDTO supportDTO) throws ServiceException {

        final TemplateMail email = TemplateMail.builder()
                .from(this.mailTemplateConfiguration.getDefaultSender())
                .to(this.mailTemplateConfiguration.getSupportSender())
                .subject(supportDTO.getSubject())
                .copy(supportDTO.getEmail())
                .templateParam("body", supportDTO.getBody())
                .template("emails/supportMessage")
                .html(true)
                .build();

        this.mailService.sendMail(email);
    }
}
