package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Cie9;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Cie9Repository extends JpaRepository<Cie9, Long> {

    @Query("select cie from Cie9 cie WHERE LOWER(cie.code) like CONCAT('%',LOWER(:search),'%') "
            + " OR LOWER(cie.description) like CONCAT('%',LOWER(:search),'%') ")
    Page<Cie9> findCieByCodeOrSearch(@Param("search") String search, Pageable pageable);
}

