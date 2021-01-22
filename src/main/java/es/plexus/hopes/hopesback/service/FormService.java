package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.DataHistoricDinamycFormDTO;
import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.controller.model.GraphHistorifyDinamycFormDTO;
import es.plexus.hopes.hopesback.controller.model.InputDTO;
import es.plexus.hopes.hopesback.controller.model.TemplateDTO;
import es.plexus.hopes.hopesback.repository.FormMongoRepository;
import es.plexus.hopes.hopesback.repository.model.FormMongo;
import es.plexus.hopes.hopesback.service.events.GraphsEvent;
import es.plexus.hopes.hopesback.service.events.SaveEvent;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.FormMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static es.plexus.hopes.hopesback.service.Constants.DYNAMIC_FORM_HISTORIC_FRONT;
import static es.plexus.hopes.hopesback.service.Constants.DYNAMIC_FORM_HISTORIFY_BACK;
import static es.plexus.hopes.hopesback.service.Constants.DYNAMIC_FORM_SIMPLE;
import static es.plexus.hopes.hopesback.service.Constants.HISTORICAL_FIELD_TYPE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.NOT_DATE_HISTORIFY_FORM_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.NOT_FOUND_FORM_MESSAGE;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.NOT_HISTORIFY_FORM_MESSAGE;

@Log4j2
@Service
public class FormService {

    private final FormMongoRepository formMongoRepository;
    private final FormMapper formMapper;
    private final TemplateService templateService;

    private final ApplicationEventPublisher publisher;

    @Autowired
    public FormService(FormMongoRepository formMongoRepository, FormMapper formMapper, TemplateService templateService, ApplicationEventPublisher publisher) {
        this.formMongoRepository = formMongoRepository;
        this.formMapper = formMapper;
        this.templateService = templateService;
        this.publisher = publisher;
    }

    public void saveData(FormDTO formDto, String user) {
        String keyTemplate = formDto.getTemplate();
        TemplateDTO templateDto = templateService.findByKey(keyTemplate);
        String formType = obtainFormType(templateDto);
        // Validar que el template sea correcto
        validateDynamicFormTemplate(templateDto, formType);

        String fieldNameDate = templateDto.getNameHistoricalDate();

        // Validar datos mínimos del formulario
        validateDynamicFormData(formDto, formType, templateDto);

        // Obtener la fecha del formulario
        String stringDate = obtainStringDateDynamicForm(formDto, formType, fieldNameDate);

        // Se obtiene el formulario a actualizar
        FormMongo formMongo = obtainFormMongoToSave(keyTemplate, formDto.getPatientId(), formType ,stringDate);


        updateDataInDynamicForms(formDto, user, formMongo, stringDate);

        if ( null != formMongo){
            publisher.publishEvent(new SaveEvent(templateDto.getKey(), Long.valueOf(formDto.getPatientId()), formDto));
        }
    }

    public FormDTO findFormByTemplateAndPatientId(String template, Integer patientId) {
        FormMongo formMongo = obtainFormMongoToView(template, patientId);
        return formMapper.entityToDto(formMongo);
    }

    public FormDTO findByTemplateAndPatientIdAndDate(String template, Integer patientId, LocalDateTime date) {
        FormMongo formMongo = obtainHistoryFormMongoByDate(template, patientId, date);
        return formMapper.entityToDto(formMongo);
    }

    public List<FormDTO> findByTemplate(String template) {

        List<FormMongo> formsMongo = formMongoRepository.findByTemplate(template);

        return formMapper.listEntityToListDto(formsMongo);
    }

    public List<FormDTO> findByTemplateAndPatientId(String template, Integer patientId) {

        List<FormMongo> formsMongo = formMongoRepository.findFormsByTemplateAndPatientId(template, patientId);

        return formMapper.listEntityToListDto(formsMongo);
    }

    public void updateData(FormDTO dto, String user){

        FormMongo formMongo = formMongoRepository.findFormByTemplateAndPatientId(dto.getTemplate(), dto.getPatientId());

        if (formMongo != null) {

            formMongoRepository.delete(formMongo);

            this.saveData(dto, user != null ? user : formMongo.getUser());
        }
        else {
            throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(NOT_FOUND_FORM_MESSAGE);
        }
    }

    public void deleteData(String template, Integer patientId){

        FormMongo formMongo = formMongoRepository.findFormByTemplateAndPatientId(template, patientId);

        if (formMongo != null) {
            formMongoRepository.delete(formMongo);
        }
        else {
            throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(NOT_FOUND_FORM_MESSAGE);
        }
    }

    public Object findDataByParamName(String template, Integer patientId, String name){
        List<InputDTO> inputDTOList = new ArrayList<>();
        List<FormDTO> forms = this.findByTemplateAndPatientId(template, patientId);
        if(CollectionUtils.isNotEmpty(forms)){
            inputDTOList = forms.get(0).getData().stream().filter(inputDTO -> name.equals(inputDTO.getName())).collect(Collectors.toList());
        }

        return CollectionUtils.isNotEmpty(inputDTOList)?inputDTOList.get(0).getValue():new InputDTO().getValue();
    }

    public List<GraphHistorifyDinamycFormDTO> graphHistorifyDinamycFormByTemplateAndPatientId(String template, Integer patientId) {
        List<GraphHistorifyDinamycFormDTO> graphs = new ArrayList<>();

        TemplateDTO templateDto = templateService.findByKey(template);

        validateHistorifiedBackDynamicFormTemplate(templateDto);

        List<Object> fieldsToGraph = templateDto.getFieldsToGraph();
        String fieldDate = templateDto.getNameHistoricalDate();
        List<FormDTO> forms = this.findByTemplateAndPatientId(templateDto.getKey(), patientId);

        if(Boolean.TRUE.equals(templateDto.getHistorify())
                && !Boolean.TRUE.equals(templateDto.getIsTable())) {

            graphs = fillGraphDinamicForm(forms, fieldsToGraph, fieldDate);
        }
        publisher.publishEvent(new GraphsEvent(templateDto.getKey(), patientId, fieldsToGraph, fieldDate, forms, graphs));

        return graphs;
    }

    private void validateDynamicFormData(FormDTO formDto, String formType, TemplateDTO templateDto) {

        if(null==formDto.getPatientId()){
            throw ServiceExceptionCatalog.NOT_DATE_HISTORIFY_FORM_EXCEPTION.exception(NOT_DATE_HISTORIFY_FORM_MESSAGE);
        }

        switch (formType) {
            case DYNAMIC_FORM_HISTORIFY_BACK:
                validateHistorifiedBackDynamicFormData(formDto, templateDto);
                break;
            case DYNAMIC_FORM_HISTORIC_FRONT:
                validateHistoricalFrontDynamicFormData(formDto, templateDto);
                break;
            case DYNAMIC_FORM_SIMPLE:
            default:
                break;
        }

    }

    private void validateHistoricalFrontDynamicFormData(FormDTO formDto, TemplateDTO templateDTO) {
        if(!StringUtils.isEmpty(templateDTO.getNameHistoricalDate()) && formDto.getData()
                .stream()
                .noneMatch(inputDTO ->HISTORICAL_FIELD_TYPE.equals(inputDTO.getType())
                        && templateDTO.getNameHistoricalDate().equals(inputDTO.getName()))){
            throw ServiceExceptionCatalog.NOT_DATE_HISTORIFY_FORM_EXCEPTION.exception(NOT_DATE_HISTORIFY_FORM_MESSAGE);
        }
    }

    private void validateHistorifiedBackDynamicFormData(FormDTO formDto, TemplateDTO templateDTO) {
        if(!StringUtils.isEmpty(templateDTO.getNameHistoricalDate()) && formDto.getData()
                .stream()
                .noneMatch(inputDTO -> templateDTO.getNameHistoricalDate().equals(inputDTO.getName()))){
            throw ServiceExceptionCatalog.NOT_DATE_HISTORIFY_FORM_EXCEPTION.exception(NOT_DATE_HISTORIFY_FORM_MESSAGE);
        }

    }

    private String obtainFormType(TemplateDTO templateDto) {
        return Boolean.TRUE.equals(templateDto.getHistorify())
                && !Boolean.TRUE.equals(templateDto.getIsTable())? DYNAMIC_FORM_HISTORIFY_BACK : DYNAMIC_FORM_SIMPLE;
    }

    private void validateDynamicFormTemplate(TemplateDTO templateDto, String formType) {

        if(Objects.isNull(templateDto) ){
            throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(NOT_FOUND_FORM_MESSAGE);
        }

        switch (formType) {
            case DYNAMIC_FORM_HISTORIFY_BACK:
                validateHistorifiedBackDynamicFormTemplate(templateDto);
                break;
            case DYNAMIC_FORM_HISTORIC_FRONT:
                validateHistoricalFrontDynamicFormTemplate(templateDto);
                break;
            default:
                break;
        }

    }

    private LocalDateTime convertStringToMongoLocalDateTime(String stringDate, boolean isFinalDay) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy[-MM][-dd['T'HH[:mm[:ss]]]][.SSSXXX]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .parseDefaulting(ChronoField.NANO_OF_SECOND, 0)
                .toFormatter();

        try{
            return ZonedDateTime
                    .ofInstant( LocalDateTime
                            .parse(stringDate,formatter)
                            .with(isFinalDay?LocalTime.MAX:LocalTime.MIN)
                            .toInstant(ZoneOffset.UTC), ZoneId.systemDefault())
                    .toLocalDateTime();
        } catch (DateTimeParseException ex){
            log.error("Problema al obtener la fecha del formulario");
            throw ServiceExceptionCatalog.NOT_DATE_HISTORIFY_FORM_EXCEPTION.exception(NOT_DATE_HISTORIFY_FORM_MESSAGE);
        }
    }


    /**
     * Method it get the form date
     * @param formDto
     * @param formType
     * @param nameDate
     * @return
     */
    private String obtainStringDateDynamicForm(FormDTO formDto, String formType, String nameDate) {
        String date;
        try {
            switch (formType) {
                case DYNAMIC_FORM_HISTORIFY_BACK:
                    date = formDto.getData()
                            .stream()
                            .filter(inputDTO -> nameDate.equals(inputDTO.getName()))
                            .findFirst()
                            .orElseGet(null)
                            .getValue()
                            .toString();
                    break;

                case DYNAMIC_FORM_HISTORIC_FRONT:
                    date = formDto.getData()
                            .stream()
                            .filter(inputDTO -> HISTORICAL_FIELD_TYPE.equals(inputDTO.getType())
                                    && nameDate.equals(inputDTO.getName()))
                            .findFirst()
                            .orElseGet(null)
                            .getValue()
                            .toString();
                    break;

                case DYNAMIC_FORM_SIMPLE:
                default:
                    date = LocalDate.now().toString();
                    break;
            }
        }catch (Exception e){
            log.error("Problemas al obtener la fecha del formulario");
            throw ServiceExceptionCatalog.NOT_DATE_HISTORIFY_FORM_EXCEPTION.exception(NOT_DATE_HISTORIFY_FORM_MESSAGE);
        }
        return date;
    }

    /**
     * Method that it return the form by date, it depends the field historify
     * @param template
     * @param patientId
     * @return
     */
    private FormMongo obtainHistoryFormMongoByDate(String template, Integer patientId, LocalDateTime date) {

        TemplateDTO templateDto = templateService.findByKey(template);

        validateHistorifiedBackDynamicFormTemplate(templateDto);

        // Se ha de recuperar el formulario de la fecha indicada
        // formularios dinámicos historificables

        return formMongoRepository
                .findByTemplateAndPatientIdAndDateBetween(templateDto.getKey(),
                        patientId,
                        date,
                        ZonedDateTime.ofInstant(date.with(LocalTime.MAX).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()).toLocalDateTime());
    }

    private void validateHistoricalFrontDynamicFormTemplate(TemplateDTO templateDto) {
        if(StringUtils.isEmpty(templateDto.getNameHistoricalDate())) {
            throw ServiceExceptionCatalog.NOT_DATE_HISTORIFY_FORM_EXCEPTION.exception(NOT_DATE_HISTORIFY_FORM_MESSAGE);
        }
    }

    private void validateHistorifiedBackDynamicFormTemplate(TemplateDTO templateDto) {

        if(!Boolean.TRUE.equals(templateDto.getHistorify())) {
            throw ServiceExceptionCatalog.NOT_HISTORIFY_FORM_EXCEPTION.exception(NOT_HISTORIFY_FORM_MESSAGE);
        }
        boolean hasGraphs = !StringUtils.isEmpty(templateDto.getButtons())
                && templateDto.getButtons().contains("graphs");
        if(hasGraphs && CollectionUtils.isEmpty(templateDto.getFieldsToGraph())){
            throw ServiceExceptionCatalog.NOT_HISTORIFY_FORM_EXCEPTION.exception(NOT_HISTORIFY_FORM_MESSAGE);
        }

        if(hasGraphs && StringUtils.isEmpty(templateDto.getNameHistoricalDate())) {
            throw ServiceExceptionCatalog.NOT_DATE_HISTORIFY_FORM_EXCEPTION.exception(NOT_DATE_HISTORIFY_FORM_MESSAGE);
        }
    }

    /**
     * Method that it return the last form or a new form, it depends the field historify
     * @param template
     * @param patientId
     * @return
     */
    private FormMongo obtainFormMongoToView(String template, Integer patientId) {
        FormMongo formMongo;

        TemplateDTO templateDto = templateService.findByKey(template);
        validateDynamicFormTemplate(templateDto, obtainFormType(templateDto));

        // Para los formularios dinámicos no historificables solo hay un formulario
        if(!Boolean.TRUE.equals(templateDto.getHistorify())) {
            formMongo = formMongoRepository.findFormByTemplateAndPatientId(templateDto.getKey(), patientId);
            // Se ha de recuperar el formulario de la fecha del día ya que está opción es para
            // formularios dinámicos historificables
        } else {
            List<FormMongo> forms = formMongoRepository
                    .findFormsByTemplateAndPatientId(templateDto.getKey(), patientId);
            formMongo = CollectionUtils.isNotEmpty(forms)?forms.get(0):null;
        }
        return formMongo;
    }

    /**
     * Method that it return the last form or a new form, it depends the field historify
     * @param template
     * @param patientId
     * @return
     */
    private FormMongo obtainFormMongoToSave(String template, Integer patientId, String formType, String dateString) {
        FormMongo formMongo;

        // Se ha de recuperar el formulario de la fecha del día ya que está opción es para
        // formularios dinámicos historificables
        if (DYNAMIC_FORM_HISTORIFY_BACK.equals(formType)) {
            LocalDateTime startDate = convertStringToMongoLocalDateTime(dateString, false);
            LocalDateTime endDate = convertStringToMongoLocalDateTime(dateString, true);
            formMongo = formMongoRepository
                    .findByTemplateAndPatientIdAndDateBetween(template,
                            patientId,
                            startDate,
                            endDate);
            // Para los formularios dinámicos no historificables solo hay un formulario
        }else{
            formMongo = formMongoRepository.findFormByTemplateAndPatientId(template, patientId);
        }

        return formMongo;
    }

    private void updateDataInDynamicForms(FormDTO dto, String user, FormMongo formMongo, String dateString) {
        if (formMongo != null) {
            formMongoRepository.delete(formMongo);
        }
        saveDynamicForm(dto, user, dateString);
    }

    private void saveDynamicForm(FormDTO dto, String user, String date) {
        FormMongo form = formMapper.dtoToEntity(dto);
        form.setUser(user);
        form.setDateTime(convertStringToMongoLocalDateTime(date,false));
        formMongoRepository.save(form);
    }

    private List<GraphHistorifyDinamycFormDTO> fillGraphDinamicForm(List<FormDTO> forms, List<Object> fieldsToGraph, String fieldDate) {
        List<GraphHistorifyDinamycFormDTO> graphList =  new ArrayList<>();

        fieldsToGraph.forEach( field -> {
            GraphHistorifyDinamycFormDTO graph =  new GraphHistorifyDinamycFormDTO();
            graph.setName((String)field);
            graph.setValues(new ArrayList<DataHistoricDinamycFormDTO>());

            if(CollectionUtils.isNotEmpty(forms)){

                forms.forEach(formDTO -> {
                    DataHistoricDinamycFormDTO dataHistoric =  new DataHistoricDinamycFormDTO();

                    Object dateHistoric = formDTO.getData().stream()
                            .filter(inputDTO -> fieldDate.equals(inputDTO.getName()))
                            .map(InputDTO::getValue)
                            .findFirst()
                            .orElseGet(null);
                    dataHistoric.setDate(null!=dateHistoric?dateHistoric.toString().concat("T00:00:00.000Z"):null);

                    Object valueHistoric = formDTO.getData().stream()
                            .filter(inputDTO -> field.equals(inputDTO.getName()))
                            .map(InputDTO::getValue)
                            .findFirst()
                            .orElseGet(null);
                    dataHistoric.setValue(null!=valueHistoric?valueHistoric.toString():null);

                    if(dataHistoric!= null && valueHistoric != null) {
                        graph.getValues().add(dataHistoric);
                    }
                });
                graphList.add(graph);
            }

        });
        return  graphList;
    }

    public List<FormDTO> findByTemplateAndJob(String template, boolean toProcess) {
        List<FormMongo> formsMongo = formMongoRepository.findByTemplateAndJob(template, toProcess);
        return formMapper.listEntityToListDto(formsMongo);
    }
}
