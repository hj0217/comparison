package com.demo1.history;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo( // 인터페이스를 이용하여 다형성을 구현한 경우, 실제 클레스가 무엇인지를 알려주는 설정을 한느 어노테이션
        use = JsonTypeInfo.Id.NAME,
        property = "type" //사용자 정의 프로퍼티 키
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StringCaptured.class, name = "STRING"), // 구현체 지정,( + 네이밍)
})
public interface Captured {
    boolean changed();

}
