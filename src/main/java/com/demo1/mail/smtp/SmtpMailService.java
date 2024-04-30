package com.demo1.mail.smtp;

import com.demo1.mail.*;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Service
public class SmtpMailService implements Sendable {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private SmtpConfig config;

    @Qualifier("freeMarkerTemplateEngine")
    @Autowired
    private TemplateEngine templateEngine;


    @Override
    public <T extends Mail> boolean sendEmail(T mail) throws IOException, MessagingException, TemplateException {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);


        helper.setSubject(mail.getTitle());
        helper.setFrom(config.getSenderEmail(), config.getSenderName());
        for (final MailReceiver receiver : mail.getReceiverList()) {
            helper.addTo(receiver.getReceiverEmail(), receiver.getReceiverName());

        }
        String htmlBody = templateEngine.processTemplate("mail/email", mail);

        helper.setText(htmlBody, true);
        sender.send(message);

        return false;
    }

}
