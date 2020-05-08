package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.FormMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface FormMongoRepository extends MongoRepository<FormMongo, Long> {

    @Query("{'template':'?0', 'patientId':'?1'}")
    FormMongo findByTemplateAndPatientId(String template, Integer patientId);
}
