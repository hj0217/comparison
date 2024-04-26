package com.demo1.email;

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


    public MailBuilder(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
