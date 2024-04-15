package com.demo1.jsondiff.member;


import lombok.*;

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
}