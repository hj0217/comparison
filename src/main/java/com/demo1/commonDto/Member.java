package com.demo1.commonDto;


import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private int id; // 객체 비교 시 기준값

    private String email;

    private String password;

    private String address;

    private String phone;

    @Singular
    private List<Role> roles;

    private Member parent;

    private Member grandParent;

}