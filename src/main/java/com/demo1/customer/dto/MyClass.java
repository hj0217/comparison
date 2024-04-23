package com.demo1.customer.dto;

public class MyClass {

    private Object oldData;

    // oldData 필드에 접근하는 public 메서드
    public Object getOldData() {
        return oldData;
    }

    // oldData 필드를 설정하는 public 메서드
    public void setOldData(Object oldData) {
        this.oldData = oldData;
    }

    // JdbcUtils.convertUnderscoreNameToPropertyName(columnName)와 유사한 기능의 메서드
//    private String convertColumnNameToPropertyName(String columnName) {
//        // 여기에 필드 이름을 변환하는 로직을 구현
//        // 예를 들어, 단순히 카멜 케이스로 변환한다면 아래와 같이 구현 가능
//        return StringUtils.convertToCamelCase(columnName);
//    }
}
