package es.plexus.hopes.hopesback.service.events;

import es.plexus.hopes.hopesback.controller.model.FormDTO;
import es.plexus.hopes.hopesback.repository.model.Pathology;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
public class SaveEvent {

    private final String name;
    private final Long patientId;
    private final FormDTO form;
    private final String indication;
    private final Pathology pathology;
}
