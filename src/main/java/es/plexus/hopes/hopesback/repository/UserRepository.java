package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	boolean existsByUsername(String username);

    @Query("select usr from User usr "
            + " WHERE LOWER(usr.username) like CONCAT('%',LOWER(:search),'%')"
            + " OR LOWER(usr.name) like CONCAT('%',LOWER(:search),'%') "
            + " OR LOWER(usr.surname) like CONCAT('%',LOWER(:search),'%') "
            + " OR LOWER(usr.phone) like CONCAT('%',LOWER(:search),'%') "
            + " OR LOWER(usr.dni) like CONCAT('%',LOWER(:search),'%') "
            + " OR CAST(usr.collegeNumber as text) like CONCAT('%',LOWER(:search),'%') "
            + " OR LOWER(usr.email) like CONCAT('%',LOWER(:search),'%') ")
    Page<User> findUsersBySearch(@Param("search") String search, Pageable pageable);

    @Query(value = "select distinct usr from User usr" +
            " JOIN usr.roles rol" +
            " WHERE rol.hospital.id = (select rol1.hospital.id from Role rol1 where rol1.id = :idRole)" +
            " AND rol.service.id = (SELECT rol1.service.id FROM Role rol1 where rol1.id = :idRole)" +
            " AND rol.pathology.id = (SELECT rol1.pathology.id FROM Role rol1 where rol1.id = :idRole)")
    Page<User> findUsersByRoleSelected(@Param("idRole") Long idRole, Pageable pageable);
}
