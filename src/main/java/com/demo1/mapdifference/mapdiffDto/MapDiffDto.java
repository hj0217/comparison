package com.demo1.mapdifference.mapdiffDto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MapDiffDto {
    private String op; // left is not nul & right is null (삭제) , left is null & right is not null (추가), both are not null (수정)
                        // 객체 속의 객체 LISt는 직접 하나씩 꺼내보면서 일치여부 확인하기??
    private String path;
    private Object before; // left value
    private Object after;  // right value

}

