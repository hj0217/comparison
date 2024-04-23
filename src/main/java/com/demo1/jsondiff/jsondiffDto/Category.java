package com.demo1.jsondiff.jsondiffDto;

public enum Category {
    삭제("remove"),
    수정("replace"),
    추가("add");


    private final String value;

    Category(String value) {
        this.value = value;
    }

    public static Category fromValue(String value) {
        for (Category category : Category.values()) {
            if (value.equals(category.value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid Category value: " + value);
    }
}
