package com.demo1.customer.tracker;

import lombok.*;

import java.beans.ConstructorProperties;

@Data
public class NumberCaptured implements Captured{
    private final String name;
    protected final int oldValue;
    protected final int newValue;

    @Builder
    @ConstructorProperties({"name", "oldValue", "newValue"})
    public NumberCaptured (String name, int oldValue, int newValue) {
        this.name = name;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public boolean changed() {
        return oldValue != newValue;
    }

//    @Override
//    public Object getOldValue() {
//        return this.oldValue;
//    }

//    @Override
//    public Object getNewValue() {
//        return this.newValue;
//    }
}
