package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.FormMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface FormMongoRepository extends MongoRepository<FormMongo, String> {

    @Query("{'template':'?0', 'patientId':'?1'}")
    FormMongo findByTemplateAndPatientId(String template, Integer patientId);

    @Query("{'template':'?0', 'patientId':'?1', 'dateTime':{'$gte':?2, '$lt': ?3}}")
    FormMongo findByTemplateAndPatientIdAndDateBetween(String template, Integer patientId, Date initDate, Date endDate);

    List<FormMongo> findByTemplate(String template);

    FormMongo findById(String template, Integer patientId);

}
