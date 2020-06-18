package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.repository.FormMongoRepository;
import es.plexus.hopes.hopesback.repository.model.FormMongo;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.FormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.NOT_FOUND_FORM;

@Service
public class FormService {

    private final FormMongoRepository formMongoRepository;
    private final FormMapper formMapper;
    private final MongoTemplate mongoTemplate;
    private final TemplateService templateService;

    @Autowired
    public FormService(FormMongoRepository formMongoRepository, FormMapper formMapper, MongoTemplate mongoTemplate, TemplateService templateService) {
        this.formMongoRepository = formMongoRepository;
        this.formMapper = formMapper;
        this.mongoTemplate = mongoTemplate;
        this.templateService = templateService;
    }

    public void saveData(FormDTO dto, String user) {
        if(templateService.existsTemplateByKey(dto.getTemplate())) {
            FormMongo formMongo = formMongoRepository.findByTemplateAndPatientId(dto.getTemplate(), dto.getPatientId());

            if (formMongo != null) {

                formMongoRepository.delete(formMongo);

                this.saveData(dto, user);
            }
            else {
                saveDynamicForm(dto, user);
            }
        } else {
            throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(NOT_FOUND_FORM);
        }
    }

    private void saveDynamicForm(FormDTO dto, String user) {
        FormMongo form = formMapper.dtoToEntity(dto);

        form.setDateTime(new Date());
        form.setUser(user);

        formMongoRepository.save(form);
    }

    public FormDTO findByTemplateAndPatientId(String template, Integer patientId) {

        FormMongo formMongo = formMongoRepository.findByTemplateAndPatientId(template, patientId);

        return formMapper.entityToDto(formMongo);

    }

    public void updateData(FormDTO dto, String user){

        FormMongo formMongo = formMongoRepository.findByTemplateAndPatientId(dto.getTemplate(), dto.getPatientId());

        if (formMongo != null) {

            formMongoRepository.delete(formMongo);

            this.saveData(dto, user);
        }
        else {
            throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(NOT_FOUND_FORM);
        }
    }

    public void deleteData(String template, Integer patientId){

        FormMongo formMongo = formMongoRepository.findByTemplateAndPatientId(template, patientId);

        if (formMongo != null) {
            formMongoRepository.delete(formMongo);
        }
        else {
            throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(NOT_FOUND_FORM);
        }
    }
}
