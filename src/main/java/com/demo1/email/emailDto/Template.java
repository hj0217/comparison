package com.demo1.email.emailDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Template {
    private String subject;
    private String contents;
    private String senderName;

}
