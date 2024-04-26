package com.demo1.email;


import lombok.Data;
import java.util.List;

@Data
public abstract class Mail {

    private String subject;
    private String content;
    private List<? extends MailReceiver> receiverList;

}
