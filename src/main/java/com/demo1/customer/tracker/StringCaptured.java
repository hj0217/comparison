package com.demo1.customer.tracker;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.beans.ConstructorProperties;


@Data
@EqualsAndHashCode
public class StringCaptured implements Captured{
    private final String name;
    protected final String oldValue;
    protected final String newValue;

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

//
//    @Override
//    public Object getOldValue() {
//        return this.oldValue;
//    }
//
//    @Override
//    public Object getNewValue() {
//        return this.newValue;
//    }

}
