package es.plexus.hopes.hopesback.service.events;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.plexus.hopes.hopesback.controller.model.DataHistoricDinamycFormDTO;
import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.controller.model.GraphHistorifyDinamycFormDTO;
import es.plexus.hopes.hopesback.controller.model.InputDTO;
import es.plexus.hopes.hopesback.controller.model.MonitoringDrugRowDTO;
import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.service.DispensationDetailService;
import es.plexus.hopes.hopesback.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
@RequiredArgsConstructor
public class SaveConsumer {

    private final PatientService patientService;

    @EventListener(condition = "#saveEvent.name eq 'blood-count-vih'")
    public void handleSaveBloodCountVih(SaveEvent saveEvent) throws ParseException {

        FormDTO form = saveEvent.getForm();
        Integer patient = saveEvent.getPatientId();
        String name = saveEvent.getName();

        ArrayList<LinkedHashMap> cpvAllValues = ((ArrayList) form.getData().stream().filter(inputDTO -> inputDTO.getName().equals("vihCvp")).findFirst().get().getValue());
        ArrayList<LinkedHashMap> cd4AllValues = ((ArrayList) form.getData().stream().filter(inputDTO -> inputDTO.getName().equals("nadirCD4")).findFirst().get().getValue());

        LinkedHashMap<String, String> cvp = getDataFromArrayByDate(cpvAllValues);
        LinkedHashMap<String, String> cd4 = getDataFromArrayByDate(cd4AllValues);

    }

    @EventListener(condition = "#saveEvent.name eq 'sociodemographic-data-vih'")
    public void handleSaveSocialDemographicVih (SaveEvent saveEvent) throws ParseException {

        FormDTO form = saveEvent.getForm();
        Integer patient = saveEvent.getPatientId();
        String name = saveEvent.getName();

        String infectionVia = form.getData().stream().filter(inputDTO -> inputDTO.getName().equals("infectionVia")).findFirst().get().getValue().toString();
        System.out.println("El grupo de riesgo del paciente es: " + infectionVia);
    }


    private LinkedHashMap getDataFromArrayByDate(ArrayList<LinkedHashMap> array) throws ParseException {
        LinkedHashMap<String, String> map = array.get(0);

        if (array.size() > 1) {
            for (LinkedHashMap<String, String> lhm : array) {
                if (map.get("date") != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date firstDate = sdf.parse(map.get("date").substring(0, 10));
                    Date newDate = sdf.parse(lhm.get("date").substring(0, 10));
                    if (firstDate.compareTo(newDate) < 0 || firstDate.compareTo(newDate) == 0) {
                        map = lhm;
                    }
                }
            }
        }
        return map;
    }

    private void saveEvent(String type, String valueToSave){

    }
}


