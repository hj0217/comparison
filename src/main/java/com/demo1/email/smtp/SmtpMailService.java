package com.demo1.email.smtp;

import com.demo1.email.Mail;
import com.demo1.email.MailReceiver;
import com.demo1.email.Sendable;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class SmtpMailService implements Sendable {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private SmtpConfig config;

    MimeMessage message = sender.createMimeMessage();


    @Override
    public <T extends Mail> void sendEmail(T mail) throws UnsupportedEncodingException, MessagingException {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(mail.getSubject());
        helper.setFrom(config.getSenderEmail(), config.getSenderName());
        for (final MailReceiver receiver : mail.getReceiverList()) {
            helper.addTo(receiver.getReceiverEmail(), receiver.getReceiverName());
        }
        helper.setText(mail.getContent(), true);
        sender.send(message);
    }

    @Override
    public <T extends Mail> void sendHtmlMessage(T mail) throws MessagingException, UnsupportedEncodingException {

    }
}
