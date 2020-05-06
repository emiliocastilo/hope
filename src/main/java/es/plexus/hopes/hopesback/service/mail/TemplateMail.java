package es.plexus.hopes.hopesback.service.mail;

import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TemplateMail extends SimpleMail {
    private final String template;
    @Singular
    private final Map<String, Object> templateParams;
}
