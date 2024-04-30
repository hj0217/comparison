package com.demo1.mail;

import lombok.*;

/*
*  메일 수신자 정보
*
* */
@Data
@Builder
@AllArgsConstructor
public class MailReceiver {

    private String receiverEmail;
    private String receiverName;
}
