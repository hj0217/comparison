package com.demo1.customer.tracker;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StringCaptured.class, name="STRING"),
        @JsonSubTypes.Type(value = NumberCaptured.class, name="NUMBER")
        })
public interface Captured {

    boolean changed();
//    Object getOldValue();
//    Object getNewValue();


}
