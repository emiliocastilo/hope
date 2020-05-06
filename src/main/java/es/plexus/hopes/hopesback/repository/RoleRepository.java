package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Page<Role> findByNameContainingIgnoreCase(final String name, final Pageable pageable);
}
