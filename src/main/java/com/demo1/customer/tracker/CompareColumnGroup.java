package com.demo1.customer.tracker;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public enum CompareColumnGroup  {


    MEMBER("회원",Arrays.asList(CompareColumn.NAME, CompareColumn.AGE)),
    EMPTY("대상없음",Collections.emptyList());
    /**
     * 컬럼 그룹 명
     */
    private String value;
    List<CompareColumn> compareColumnList;

    CompareColumnGroup(String value, List<CompareColumn> compareColumnList) {
        this.value = value;
        this.compareColumnList = compareColumnList;
    }

    /**
     * 비교 대상 컬럼 그룹  = MEMBER (NAME, AGE)
     *
     */
    public com.demo1.customer.tracker.CompareColumnGroup findByDataCompareColumn(CompareColumn compareColumn) {
        return  Arrays.stream(com.demo1.customer.tracker.CompareColumnGroup.values())
                .filter(compareColumnGroup -> compareColumnGroup.hasCompareColumn(compareColumn))
                .findAny()
                .orElse(com.demo1.customer.tracker.CompareColumnGroup.EMPTY);
    }


    public boolean hasCompareColumn(CompareColumn column) {
        return compareColumnList.stream()
                .anyMatch(compareColumn -> compareColumn == column);
    }

}

