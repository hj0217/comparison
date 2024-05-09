package com.demo1.commonDto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;

@Schema(description = "회원DTO")
@Setter
@Getter
@Builder
@AllArgsConstructor
public class Member {
    
    @ApiModelProperty(value = "아이디")
    private int id;

    @ApiModelProperty(value = "이메일")
    private String email;

    @ApiModelProperty(value = "비밀번호")
    private String password;

    @ApiModelProperty(value = "주소")
    private String address;

    @ApiModelProperty(value = "휴대전화번호")
    private String phone;

    @Singular
    @ApiModelProperty(value = "역할리스트")
    private List<Role> roles;

    @ApiModelProperty(value = "부모회원")
    private Member parent;


    @ApiModelProperty(value = "조상회원")
    private Member grandParent;

}