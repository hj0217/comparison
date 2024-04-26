package com.demo1.email;

import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface Sendable {

    <T extends Mail> void  sendEmail(T mail) throws UnsupportedEncodingException, MessagingException;

    <T extends Mail> void sendHtmlMessage(T mail) throws MessagingException, UnsupportedEncodingException;
}
