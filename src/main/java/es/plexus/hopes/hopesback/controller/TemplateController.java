package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.TemplateDTO;
import es.plexus.hopes.hopesback.service.TemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/template")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }


    @GetMapping("/prueba")
    public void pruebaMongo() {
        templateService.prueba();

        // FIXME he hecho la prueba de que el mongo funciona, configurado los logs y ahora solo falta hacer la api para
        //guardar una plantilla en mongo
    }

    @GetMapping
    public TemplateDTO getByKey(@RequestParam String key) {

        return templateService.findByKey(key);
    }

    @PostMapping
    public void uploadTemplate(@RequestBody @Valid TemplateDTO templateDTO) {

        System.out.println("entra al post");

    }

}
