package es.plexus.hopes.hopesback.service.mail;

import es.plexus.hopes.hopesback.configuration.MailTemplateConfiguration;
import io.jsonwebtoken.lang.Collections;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.hibernate.service.spi.ServiceException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
@Log4j2
public class MailService {
    private final MailTemplateConfiguration mailConfiguration;
    private final MailTemplateEngine mailTemplateEngine;
    private final JavaMailSender javaMailSender;
    private final MessageSource messageSource;

    public MailService(final MailTemplateConfiguration mailConfiguration,
                       final MailTemplateEngine mailTemplateEngine,
                       final JavaMailSender javaMailSender,
                       final MessageSource messageSource) {
        this.mailConfiguration = mailConfiguration;
        this.mailTemplateEngine = mailTemplateEngine;
        this.javaMailSender = javaMailSender;
        this.messageSource = messageSource;
    }

    public void sendMail(final SimpleMail simpleMail) {
        this.send(mimeMessage -> prepareMessage(mimeMessage, simpleMail));
    }

    public void sendMail(final TemplateMail templateMail) {
        final SimpleMail simpleMail = SimpleMail.builder()
                .from(templateMail.getFrom())
                .to(templateMail.getTo())
                .subject(resolveSimpleMessageSource(templateMail.getSubject()))
                .copy(templateMail.getCopy())
                .text(mailTemplateEngine.process(templateMail))
                .html(templateMail.isHtml())
                .files(templateMail.getFiles())
                .build();
        sendMail(simpleMail);
    }

    private String resolveSimpleMessageSource(final String from) {

        String message = from;

        try {
            message = messageSource.getMessage(from, null, LocaleContextHolder.getLocale());
        }
        catch (NoSuchMessageException e) {
            log.debug("Not found message into locale");
        }

        return message;
    }

    private void prepareMessage(final MimeMessage mimeMessage, final SimpleMail simpleMail) throws MessagingException {
        final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, isMultipart(simpleMail));
        messageHelper.setFrom(getFrom(simpleMail));
        messageHelper.setTo(simpleMail.getTo());
        messageHelper.setSubject(simpleMail.getSubject());
        messageHelper.setText(simpleMail.getText(), simpleMail.isHtml());

        if (StringUtils.isNotBlank(simpleMail.getCopy())) {
            messageHelper.setCc(simpleMail.getCopy());
        }

        addFiles(messageHelper, simpleMail.getFiles());
    }

    private boolean isMultipart(final SimpleMail simpleMail) {
        return !Collections.isEmpty(simpleMail.getFiles());
    }

    private String getFrom(final SimpleMail simpleMail) {
        final String from = simpleMail.getFrom();
        return StringUtils.isNotBlank(from)
               ? from
               : mailConfiguration.getDefaultSender();
    }

    private void addFiles(final MimeMessageHelper messageHelper, final List<File> attachedFiles) {
        if (!Collections.isEmpty(attachedFiles)) {
            try {
                for (File file : attachedFiles) {
                    messageHelper.addInline(file.getName(), file);
                }
            } catch (final MessagingException ex) {
                log.error("Error mientras se añadian fiechero al email");
            }
        }
    }

    private void send(final MimeMessagePreparator messagePreparator) {
        try {
            javaMailSender.send(messagePreparator);
            log.debug("Email sent");
        } catch (final MailAuthenticationException | MailSendException ex) {
            throw new ServiceException("Error while sending the email", ex);
        } catch (final MailException ex) {
            throw new ServiceException("Error while using the email service", ex);
        }
    }

}
