package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "template")
public class TemplateMongo {
    @Id
    private String key;

    private String form;

    private String buttons;

    private Boolean historify;

    private String nameHistoricalDate;

    private List<Object> fieldsToGraph;

}
