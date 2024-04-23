package com.demo1.jsondiff.jsondiffDto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DiffDto {
    private Category op;
    private String path;
    private Object before; // String or {id: xx, role: "xxxx_xxx"}
    private Object after;

}

