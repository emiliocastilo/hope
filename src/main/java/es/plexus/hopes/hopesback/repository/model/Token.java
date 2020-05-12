package es.plexus.hopes.hopesback.repository.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @Column(name = "tok_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "tok_value", nullable = false, length = 200)
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name = "tok_type", nullable = false, length = 20)
    private TokenType type;

    
    @Basic
    @Column(name = "tok_token_creation_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime tokenCreationDate;

    @Basic
    @Column(name = "tok_token_expiration_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime tokenExpirationDate;

    @ManyToOne
    @JoinColumn(name = "tok_usr_id")
    private User user;

}
