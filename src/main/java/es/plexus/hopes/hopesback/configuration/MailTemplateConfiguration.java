package es.plexus.hopes.hopesback.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("plexus.hopes.email.template")
public class MailTemplateConfiguration {
    private String defaultSender;
    private String resetPasswordUrl;
    private String supportSender;

}
