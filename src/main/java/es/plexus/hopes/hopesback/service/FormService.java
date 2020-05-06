package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.repository.FormMongoRepository;
import es.plexus.hopes.hopesback.repository.model.FormMongo;
import es.plexus.hopes.hopesback.service.mapper.FormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FormService {

    private final FormMongoRepository formMongoRepository;
    private final FormMapper formMapper;

    @Autowired
    public FormService(FormMongoRepository formMongoRepository, FormMapper formMapper) {
        this.formMongoRepository = formMongoRepository;
        this.formMapper = formMapper;
    }

    public void saveData(FormDTO dto) {

        FormMongo form = formMapper.dtoToEntity(dto);

        form.setDateTime(new Date());
        form.setUser("TEST"); //TODO poner el del token

        formMongoRepository.save(form);
    }

    public FormDTO findByTemplateAndPatientId(String template, Integer patientId) {

        FormMongo formMongo = formMongoRepository.findByTemplateAndPatientId(template, patientId);

        FormDTO formDTO = formMapper.entityToDto(formMongo);

        return formDTO;
    }
}
