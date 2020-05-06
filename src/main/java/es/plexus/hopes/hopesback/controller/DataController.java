package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.service.FormService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/data")
public class DataController {

    private final FormService formService;

    public DataController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping
    public void saveData(@RequestBody @Valid FormDTO dto) {
        formService.saveData(dto);
    }

    @GetMapping
    public FormDTO getData(@RequestParam String template, @RequestParam Integer patientId) {
        return formService.findByTemplateAndPatientId(template, patientId);
    }

}
