package com.demo1.email.smtp;

import com.demo1.email.AbstractMailBuilder;
import com.demo1.email.Mail;
import com.demo1.email.MailBuilder;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
@ConfigurationProperties(prefix = "mail.smtp")
@Getter
@Setter
public class SmtpConfig {

    private String senderEmail;
    private String senderName;

    static class SMTPMailBuilder implements AbstractMailBuilder {

        @Override
        public Mail build(MailBuilder builder) {
            SmtpMail mail = new SmtpMail();
            mail.setSubject(builder.getTitle());
            mail.setContent(builder.getTitle());
            mail.setReceiverList(builder.getReceivers());
            return mail;
        }

    }

    @PostConstruct
    public void setBuilder() {
        AbstractMailBuilder builder = new SMTPMailBuilder();
        MailBuilder.setBuilder(builder);
    }



}
