package es.plexus.hopes.hopesback.service.events;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.controller.model.GraphHistorifyDinamycFormDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class GraphsEvent {

	private final String name;
	private final Integer patientId;
	private final List<Object> fieldsToGraph;
	private final String fieldDate;
	private final List<FormDTO> forms;
	private final List<GraphHistorifyDinamycFormDTO> graphsList;

}

