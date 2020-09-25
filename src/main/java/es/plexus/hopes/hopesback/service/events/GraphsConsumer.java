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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
@RequiredArgsConstructor
public class GraphsConsumer {

	private final PatientService patientService;
	private final DispensationDetailService dispensationDetailService;

	@EventListener(condition = "#graphsEvent.name eq 'adherence-to-treatment-morisky' or #graphsEvent.name eq 'adherence-to-treatment-haynes'")
	public void handleAddAdherenceGraphs(GraphsEvent graphsEvent) {
		PatientDTO patient = patientService.findById(Long.valueOf(graphsEvent.getPatientId()));

		List<DispensationDetailDTO> dispensationDetailsList = dispensationDetailService
																.findDispensationDetailByNhc(patient.getNhc());

		if(CollectionUtils.isNotEmpty(dispensationDetailsList)){
			GraphHistorifyDinamycFormDTO adherenceGraph = new GraphHistorifyDinamycFormDTO();
			GraphHistorifyDinamycFormDTO gapGraph = new GraphHistorifyDinamycFormDTO();
			adherenceGraph.setName("Adherencia");
			gapGraph.setName("Desfase");
			adherenceGraph.setValues(new ArrayList<>());
			gapGraph.setValues(new ArrayList<>());
			DispensationDetailDTO[] arrayDd = dispensationDetailsList.toArray(new DispensationDetailDTO[0]);
			for (int i=0; i<arrayDd.length; i++){
				if(i>0 && null != arrayDd[i] && null != arrayDd[i].getDate()
						&& null != arrayDd[i].getDaysDispensation()) {
					Long differenceDays = DAYS.between(arrayDd[i-1].getDate(), arrayDd[i].getDate());
					if(differenceDays>0) {
						Long adherenceValue = (arrayDd[i].getDaysDispensation() * 100) / differenceDays;
						DataHistoricDinamycFormDTO dh = new DataHistoricDinamycFormDTO();

						dh.setDate(arrayDd[i].getDate().toString());
						dh.setValue(adherenceValue.toString());
						adherenceGraph.getValues().add(dh);

						dh.setDate(arrayDd[i].getDate().toString());
						dh.setValue(String.valueOf(differenceDays.intValue() + arrayDd[i].getDaysDispensation()));
						gapGraph.getValues().add(dh);
					}
				}
			}
			graphsEvent.getGraphsList().add(adherenceGraph);
			graphsEvent.getGraphsList().add(gapGraph);
		}
	}

	// Fixme Se ha creado el MonitoringDrugRowDTO por la dependencia que ha introducido el componente table
	// cuando se actualice el componente table, se ha de eliminar este método y generalizarlo para cualquier tabla
	// sino habra que crear dtos por tabla y no es lo más apropiado.
	@EventListener(condition = "#graphsEvent.name eq 'biological-drug-monitoring'")
	public void handleAddMonitoringDrugsGraphs(GraphsEvent graphsEvent) {
		if(CollectionUtils.isNotEmpty(graphsEvent.getForms())) {
			FormDTO form = graphsEvent.getForms().get(0);
			InputDTO dataForm = form.getData().stream()
					.filter(inputDTO -> "table".equals(inputDTO.getType()))
					.findFirst()
					.orElseGet(null);
			if(dataForm!=null){
				List<MonitoringDrugRowDTO> monitoringDrugList = new ObjectMapper().convertValue(dataForm.getValue(), new TypeReference<List<MonitoringDrugRowDTO>>() {
				});
				if (CollectionUtils.isNotEmpty(monitoringDrugList)) {
					Set<String> biologicalDrugsSet = monitoringDrugList.stream().map(MonitoringDrugRowDTO::getBiologicalDrug).collect(Collectors.toSet());
					biologicalDrugsSet.forEach(bd -> {
						List<MonitoringDrugRowDTO> biologicalDrugs =
								monitoringDrugList.stream()
										.filter(input -> bd.equalsIgnoreCase(input.getBiologicalDrug()))
										.collect(Collectors.toList());
						GraphHistorifyDinamycFormDTO graph = new GraphHistorifyDinamycFormDTO();
						graph.setName(bd);
						graph.setValues(new ArrayList<>());
						biologicalDrugs.forEach(drug -> {
							DataHistoricDinamycFormDTO dh = new DataHistoricDinamycFormDTO();
							dh.setDate(drug.getAnalysisDate());
							dh.setValue(drug.getSericLevels());
							graph.getValues().add(dh);
						});
						graphsEvent.getGraphsList().add(graph);
					});
				}
			}
		}
	}
}

