package com.demo1.mail;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailBuilder {

    @Getter
    private final String title;

    @Getter
    private final String content;

    private final Map<String, Object> additonalProperties = new HashMap<>();

    @Setter
    private static AbstractMailBuilder builder;

    @Getter
    private List<? extends MailReceiver> receivers;


    /*
    * 수신자 설정
    * */
    public MailBuilder(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /*
     * 수신자 설정
     * */
    public MailBuilder receivers(List<? extends MailReceiver> receivers) {
        this.receivers = receivers;
        return this;
    }

    /*
    * 추가 설정
    * */
    public void addAdditonalProperty(String key, Object value) {
        this.additonalProperties.put(key, value);
    }


    /*
    * 추가 정보 조회
    * */
    public Object getAdditonalProperty(String key) {
        return this.additonalProperties.get(key);
    }

    /*
    * 메일 생성
    * */
    public Mail build() {
        return builder.build(this);
    }

}
