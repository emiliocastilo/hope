package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "template")
public class TemplateMongo {

    @Id
    private String key;

    private String form;

}
