package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    @Query("select s from Section s join s.roles r WHERE s.menu=true and r.name IN (:roles)")
    List<Section> findByMenuTrueAndRoles(@Param("roles")List<String> roles);
}
