package com.demo1.commonDto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private int id;
    private String role;
}