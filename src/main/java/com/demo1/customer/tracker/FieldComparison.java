package com.demo1.customer.tracker;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldComparison {

    public HistoryDto comparison(Class<?> asIs, Class<?> tobe)throws ClassNotFoundException {
        List<CompareColumn> compareColumnList = new ArrayList<>();
        HistoryDto hd = new HistoryDto();
        Field[] fields = tobe.getDeclaredFields();


//        public List<CompareColumn> searchDataField() {
//        }
//        for (Field field : fields) {
//            Field asIsField = ReflectionUtils.findField(field.getType(), asIs.getTypeName());
//            System.out.println(field.getName() + ":" + field.getType());
//        }


        return hd;
    }
}





enum CompareColumn {
    NAME("이름"),
    AGE("나이");

    private String value;

    CompareColumn(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

enum CompareColumnGroup {
        MEMBER("회원", Arrays.asList(CompareColumn.NAME, CompareColumn.AGE));

        private String value;
        List<CompareColumn> list;

        CompareColumnGroup(String value, List<CompareColumn> list) {
            this.value = value;
            this.list = list;
        }
    }




