package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.TemplateDTO;
import es.plexus.hopes.hopesback.service.TemplateService;
import es.plexus.hopes.hopesback.service.mapper.TemplateMapper;
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

    @GetMapping
    public TemplateDTO getByKey(@RequestParam String key) {
        return templateService.findByKey(key);
    }

    @PostMapping
    public void uploadTemplate(@RequestBody @Valid TemplateDTO dto) {
        templateService.uploadTemplate(TemplateMapper.INSTANCE.dtoToEntity(dto));
    }

}
