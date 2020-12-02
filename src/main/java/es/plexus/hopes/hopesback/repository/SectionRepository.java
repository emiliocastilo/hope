package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    @Query("select s from Section s join s.roles r WHERE s.active= true and r.code IN (:roles) order by s.fatherSection, s.order")
    List<Section> findByMenuTrueAndRoles(@Param("roles")List<String> roles);

    @Query("select s from Section s join s.roles r WHERE r.code = :roleCode order by s.fatherSection, s.order" )
    List<Section> findByRolCode(@Param("roleCode") String roleCode);

    Optional<Section> findByFatherSectionIsNull();

    Optional<Section> findByFatherSection(Section fatherSection);
}
