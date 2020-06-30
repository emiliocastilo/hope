package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.controller.model.InputDTO;
import es.plexus.hopes.hopesback.controller.model.TemplateDTO;
import es.plexus.hopes.hopesback.repository.FormMongoRepository;
import es.plexus.hopes.hopesback.repository.model.FormMongo;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.FormMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.NOT_FOUND_FORM;

@Service
public class FormService {

    private final FormMongoRepository formMongoRepository;
    private final FormMapper formMapper;
    private final TemplateService templateService;

    @Autowired
    public FormService(FormMongoRepository formMongoRepository, FormMapper formMapper, MongoTemplate mongoTemplate, TemplateService templateService) {
        this.formMongoRepository = formMongoRepository;
        this.formMapper = formMapper;
        this.templateService = templateService;
    }

    public void saveData(FormDTO dto, String user) {
        FormMongo formMongo = obtainFormMongo(dto.getTemplate(), dto.getPatientId());
        updateDataInDynamicForms(dto, user, formMongo);
    }

    public FormDTO findByTemplateAndPatientId(String template, Integer patientId) {
        FormMongo formMongo = obtainFormMongo(template, patientId);
        return formMapper.entityToDto(formMongo);
    }

    public FormDTO findByTemplateAndPatientIdAndDate(String template, Integer patientId, Date date) {
        FormMongo formMongo = obtainFormMongo(template, patientId, date);
        return formMapper.entityToDto(formMongo);
    }

    public List<FormDTO> findByTemplate(String template) {

        List<FormMongo> formsMongo = formMongoRepository.findByTemplate(template);

        return formMapper.listEntityToListDto(formsMongo);
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

    public List<InputDTO> findDataByParamName(String template, Integer patientId, String name){
        FormDTO form = this.findByTemplateAndPatientId(template, patientId);
        return form.getData().stream().filter(inputDTO -> name.equals(inputDTO.getName())).collect(Collectors.toList());
    }

    private FormMongo obtainFormMongo(String template, Integer patientId, Date date) {
        TemplateDTO templateDto = templateService.findByKey(template);
        FormMongo formMongo;
        if(Objects.isNull(templateDto)){
            throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(NOT_FOUND_FORM);
        }else if(!Boolean.TRUE.equals(templateDto.getHistorify())) {
            formMongo = formMongoRepository.findByTemplateAndPatientId(templateDto.getKey(), patientId);
        } else {
            formMongo = formMongoRepository
                    .findByTemplateAndPatientIdAndDateBetween(templateDto.getKey(),
                            patientId,
                            Date.from(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay().toInstant(ZoneOffset.MIN)),
                            Date.from(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atTime(LocalTime.MAX).toInstant(ZoneOffset.MIN)));
        }
        return formMongo;
    }

    private FormMongo obtainFormMongo(String template, Integer patientId) {
        TemplateDTO templateDto = templateService.findByKey(template);
        FormMongo formMongo;
        if(Objects.isNull(templateDto)){
            throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(NOT_FOUND_FORM);
        }else if(!Boolean.TRUE.equals(templateDto.getHistorify())) {
            formMongo = formMongoRepository.findByTemplateAndPatientId(templateDto.getKey(), patientId);
        } else {
            formMongo = formMongoRepository
                    .findByTemplateAndPatientIdAndDateBetween(templateDto.getKey(),
                            patientId,
                            Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                            Date.from(LocalDate.now().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant()));
        }
        return formMongo;
    }

    private void updateDataInDynamicForms(FormDTO dto, String user, FormMongo formMongo) {
        if (formMongo != null) {
            formMongoRepository.delete(formMongo);
        }
        saveDynamicForm(dto, user);
    }

    private void saveDynamicForm(FormDTO dto, String user) {
        FormMongo form = formMapper.dtoToEntity(dto);

        form.setDateTime(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        form.setUser(user);

        formMongoRepository.save(form);
    }
}
