package com.demo1.email;

import lombok.Data;

/*
*  메일 수신자 정보
*
* */
@Data
public class MailReceiver {

    private String receiverEmail;
    private String receiverName;
}
