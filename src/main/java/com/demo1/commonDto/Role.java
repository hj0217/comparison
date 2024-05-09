package com.demo1.commonDto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "역할DTO")
@Setter
@Getter
@Builder
@AllArgsConstructor
@JsonSubTypes.Type(value= Role.class, name = "role")
public class Role {

    @ApiModelProperty(value = "아이디")
    private int id;
    @ApiModelProperty(value = "역할")
    private String role;
}