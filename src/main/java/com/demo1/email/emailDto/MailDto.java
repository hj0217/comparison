package com.demo1.email.emailDto;

import lombok.*;

@Data
@Builder
public class MailDto {
        private String address;
        private String name;
}
