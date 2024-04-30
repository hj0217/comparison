package com.demo1.mail;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface Sendable {

    <T extends Mail> boolean sendEmail(T mail) throws IOException, MessagingException, TemplateException;

}
