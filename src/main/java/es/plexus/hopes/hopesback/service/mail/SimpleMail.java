package es.plexus.hopes.hopesback.service.mail;

import lombok.Data;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.io.File;
import java.util.List;

@Data
@SuperBuilder
public class SimpleMail {
    private final String from;
    private final String to;
    private final String subject;
    private final String text;
    private final boolean html;
    @Singular
    private final List<File> files;

}
