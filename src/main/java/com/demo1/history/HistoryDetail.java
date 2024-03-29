package com.demo1.history;

import lombok.Data;

import java.util.List;

@Data
public class HistoryDetail {

    List<Captured> changes;

    void addIfChanged(Captured change) {
        changes.add(change);
    }
}
