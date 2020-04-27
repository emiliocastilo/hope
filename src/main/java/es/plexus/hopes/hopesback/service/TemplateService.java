package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.TemplateDTO;
import es.plexus.hopes.hopesback.repository.TemplateMongoRepository;
import es.plexus.hopes.hopesback.repository.model.TemplateMongo;
import es.plexus.hopes.hopesback.service.mapper.TemplateMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {

    private final TemplateMongoRepository templateMongoRepository;

    public TemplateService(TemplateMongoRepository templateMongoRepository) {
        this.templateMongoRepository = templateMongoRepository;
    }

    public void prueba() {

        List<TemplateMongo> all = templateMongoRepository.findAll();

        TemplateMongo templateMongo = new TemplateMongo();
        templateMongo.setKey("PRUEBA_PLANTILLA_3");
        templateMongo.setValue("{'type': 'input','label': 'Nombre','name': 'name','placeholder': 'nombre','validation': 'Validators.required, Validators.minLength(4)'}");
        templateMongoRepository.save(templateMongo);

        System.out.println("prueba acabada");
    }

    public TemplateDTO findByKey(String key) {

        TemplateMongo template = templateMongoRepository.findByKey(key);

        return TemplateMapper.INSTANCE.entityToDto(template);
    }
}
