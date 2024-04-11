package com.demo1.customer.tracker;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

import java.util.List;

@Data
@ToString
public class HistoryDto {
    private int index;
    private List<Captured> changes = new ArrayList<>();

    public void addIfChanged(Captured change) {
        if(change.changed()) {
            changes.add(change);
        }
    }

}
