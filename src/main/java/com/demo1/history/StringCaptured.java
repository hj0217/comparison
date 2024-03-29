package com.demo1.history;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;


import java.beans.ConstructorProperties;

@Getter
@EqualsAndHashCode
public class StringCaptured implements Captured{

    private final String name;
    private final String oldValue;
    private final String newValue;

    @Builder
    @ConstructorProperties({"name", "oldValue", "newValue"})
    public StringCaptured(String name, String oldValue, String newValue) {
        this.name = name;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }


    @Override
    public boolean changed() {
        return !StringUtils.equalsIgnoreCase(oldValue, newValue);
    }


}
