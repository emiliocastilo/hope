package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.repository.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
	List<Token> findByTokenExpirationDateAfter(LocalDateTime localDateTime);
	Long deleteByTokenExpirationDateBefore(LocalDateTime localDateTime);
}
