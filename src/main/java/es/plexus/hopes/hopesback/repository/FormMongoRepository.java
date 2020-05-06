package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.FormMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormMongoRepository extends MongoRepository<FormMongo, Long> {


    FormMongo findOneByTemplateAndPatientId(String template, Integer patientId);
}
