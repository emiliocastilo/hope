package es.plexus.hopes.hopesback.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Configuration
@ConfigurationProperties("plexus.hopes.email.template")
public class MailTemplateConfiguration {
    private String defaultSender;
    private String defaultSubject;
    private String defaultText;
    private String resetPasswordUrl;
}