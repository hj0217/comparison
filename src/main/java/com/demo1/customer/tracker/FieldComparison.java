package com.demo1.customer.tracker;

import com.demo1.customer.dto.BaseChangeHistory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FieldComparison {

    public <T extends BaseChangeHistory> List<T> createHistoryData(Class<T> clazz, Object oldData, Object newData,  List<CompareColumn> compareColumnList)throws ClassNotFoundException {
        List<T> changeColumnList = new ArrayList<>();

        String columnName;
        String columnComment;
        try {
            for(CompareColumn compareColumn : compareColumnList) {
                columnName = compareColumn.toString();
                columnComment = compareColumn.getValue();

                //String propertyName = JdbcUtils.convertUnderscoreNameToPropertyName(columnName);

//              Field oldKey = ReflectionUtils.findField(oldData.getClass(), JdbcUtils.convertUnderscoreNameToPropertyName(columnName));
//              Field newKey = ReflectionUtils.findField(newData.getClass(), JdbcUtils.convertUnderscoreNameToPropertyName(columnName));
                Field oldKey = oldData.getClass().getDeclaredField(columnName); // name, age의 메타 데이터를 가지고 있음.
                Field newKey = newData.getClass().getDeclaredField(columnName);

                //Method oldGetter = oldData.getClass().get // reflection 객체의 필드에 getter를 통해 접근 하는 코드는...? (필드는 private으로 외부에서 바로 접근이 금지됨)

                String oldValue = null;
                String newValue = null;

                //oldKey.setAccessible(true); // NOPMD by Kiowa on 22. 12. 16. 오후 12:24 Class Private 필드 접근 하기 위함

                oldValue = String.valueOf(oldKey.get(oldData));
 System.out.println("확인용2: "+oldValue);

                //newKey.setAccessible(true); // NOPMD by Kiowa on 22. 12. 16. 오후 12:24 Class Private 필드 접근 하기 위함
                newValue = String.valueOf(newKey.get(newData));


                if (StringUtils.isNotBlank(newValue) && !StringUtils.equals(oldValue, newValue)) {
                    T history = createInstance(clazz);
                    history.setChngField(columnName);
                    history.setChngFieldName(columnComment);

                    history.setChngFieldBeforeVal(oldValue);
                    history.setChngFieldAfterVal(newValue);
                    changeColumnList.add(history);
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return changeColumnList;
    }


    private static <T extends BaseChangeHistory> T createInstance(Class<T> clazz) {
        try {
            Constructor<T> constructor;
            constructor = clazz.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            return null;
        }

    }
}




