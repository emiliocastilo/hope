package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.TemplateMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TemplateMongoRepository extends MongoRepository<TemplateMongo, Long> {


    @Query("{'key':'?0'}")
    TemplateMongo findByKey(String key);

	boolean existsByKey(String key);
}
