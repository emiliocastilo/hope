package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.repository.FormMongoRepository;
import es.plexus.hopes.hopesback.service.FormService;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/data")
public class DataController {

    private final FormService formService;
    private final FormMongoRepository formMongoRepository;

    public DataController(FormService formService, FormMongoRepository formMongoRepository) {
        this.formService = formService;
        this.formMongoRepository = formMongoRepository;
    }

    @PostMapping
    public void saveData(@RequestBody @Valid FormDTO dto, Authentication authentication) {
        formService.saveData(dto, authentication.getName());
    }

    @GetMapping
    public FormDTO getData(@RequestParam String template, @RequestParam Integer patientId) {
        return formService.findByTemplateAndPatientId(template, patientId);
    }

    @PutMapping
    public void updateData(@RequestBody @Valid FormDTO dto, Authentication authentication) throws ServiceException {
        formService.updateData(dto, authentication.getName());
    }

    @DeleteMapping
    public void deleteData(@RequestParam String template, @RequestParam Integer patientId) throws ServiceException {
        formService.deleteData(template, patientId);
    }


}
