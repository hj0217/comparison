package com.demo1.sendEmail.emailDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Template {
    private String subject;
    private String text;
    private String senderName;

}
