package es.plexus.hopes.hopesback.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.plexus.hopes.hopesback.repository.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
	List<Token> findByTokenExpirationDateAfter(LocalDateTime localDateTime);
	Long deleteByTokenExpirationDateBefore(LocalDateTime localDateTime);
}
