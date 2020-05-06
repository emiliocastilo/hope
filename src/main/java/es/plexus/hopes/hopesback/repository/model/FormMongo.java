package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "form")
@CompoundIndexes({
        @CompoundIndex(name = "form_idx", def = "{'template': 1, 'patientId': 1}", unique = true)
})
public class FormMongo {

    @Indexed
    private String template;

    @Indexed
    private String patientId;

    private String user;
    private String data;
    private Date dateTime;

}
