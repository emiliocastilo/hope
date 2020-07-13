package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.TemplateDTO;
import es.plexus.hopes.hopesback.repository.TemplateMongoRepository;
import es.plexus.hopes.hopesback.repository.model.TemplateMongo;
import es.plexus.hopes.hopesback.service.mapper.TemplateMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class TemplateService {

    private final TemplateMongoRepository templateMongoRepository;

    public TemplateService(TemplateMongoRepository templateMongoRepository) {
        this.templateMongoRepository = templateMongoRepository;
    }

    public TemplateDTO findByKey(String key) {

        TemplateMongo template = templateMongoRepository.findByKey(key);

        return TemplateMapper.INSTANCE.entityToDto(template);
    }

    public void uploadTemplate(@Valid TemplateDTO dto) {

        TemplateMongo templateMongo = TemplateMapper.INSTANCE.dtoToEntity(dto);

        templateMongoRepository.save(templateMongo);
    }
    public boolean existsTemplateByKey(String key){
        return templateMongoRepository.existsByKey(key);
    }
}
