package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByCode(String code);

	Page<Role> findByNameContainingIgnoreCase(final String name, final Pageable pageable);

	boolean existsByName(String name);
}
