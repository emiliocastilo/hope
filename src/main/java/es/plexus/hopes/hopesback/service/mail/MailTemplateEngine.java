package es.plexus.hopes.hopesback.service.mail;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MailTemplateEngine {
    private final TemplateEngine templateEngine;

    public MailTemplateEngine(final TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String process(final TemplateMail templateMail) {
        final Context context = new Context();
        context.setVariables(templateMail.getTemplateParams());
        return this.templateEngine.process(templateMail.getTemplate(), context);
    }

}