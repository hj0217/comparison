package com.demo1.mail;


import lombok.Data;
import java.util.List;

@Data
public abstract class Mail {

    private String title;
    private String content;
    private List<? extends MailReceiver> receiverList;

}
