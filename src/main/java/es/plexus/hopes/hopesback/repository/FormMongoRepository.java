package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.FormMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FormMongoRepository extends MongoRepository<FormMongo, String> {

    @Query("{'template':'?0', 'patientId':'?1'}")
    FormMongo findFormByTemplateAndPatientId(String template, Integer patientId);

    @Query("{'template':'?0', 'patientId':'?1', 'dateTime':{'$gte':?2, '$lt': ?3}}")
    FormMongo findByTemplateAndPatientIdAndDateBetween(String template, Integer patientId, LocalDateTime initDate, LocalDateTime endDate);

    List<FormMongo> findByTemplate(String template);

    FormMongo findById(String template, Integer patientId);

    @Query(value= "{'template':'?0', 'patientId':'?1'}", sort = "{'dateTime': -1}")
    List<FormMongo> findFormsByTemplateAndPatientId(String template, Integer patientId);

    List<FormMongo>  findByTemplateAndJob(String template, boolean toProcess);
}
