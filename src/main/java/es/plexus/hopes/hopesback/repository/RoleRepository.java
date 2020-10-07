package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByCode(String code);

    Page<Role> findByNameContainingIgnoreCase(final String name, final Pageable pageable);

    boolean existsByName(String name);

    @Query(value = "select distinct rol from Role rol" +
            " WHERE rol.hospital.id = (select rol1.hospital.id from Role rol1 where rol1.id = :idRole)" +
            " AND rol.service.id = (SELECT rol1.service.id FROM Role rol1 where rol1.id = :idRole)" +
            " AND rol.pathology.id = (SELECT rol1.pathology.id FROM Role rol1 where rol1.id = :idRole)")
    Page<Role> findRolesByRoleSelected(@Param("idRole") Long idRole, Pageable pageable);

    @Query(value = "select distinct rol from Role rol" +
            " WHERE rol.hospital.id = (select rol1.hospital.id from Role rol1 where rol1.id = :idRole)" +
            " AND rol.service.id = (SELECT rol1.service.id FROM Role rol1 where rol1.id = :idRole)" +
            " AND rol.pathology.id = (SELECT rol1.pathology.id FROM Role rol1 where rol1.id = :idRole)")
    List<Role> findRolesByRoleSelected(@Param("idRole") Long idRole);
}
