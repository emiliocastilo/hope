package es.plexus.hopes.hopesback.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("plexus.hopes.ldap")
public class LDAPConfiguration {
    private String dnPatterns;
    private String groupSearchBase;
    private String url;
    private String passwordAttribute;
}
