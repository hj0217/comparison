package com.demo1.customer.dto;

import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Data
@ToString
public class BaseChangeHistory {

    /** 변경 이력 순번 */
    private Integer chngHistSn;

    /** 변경 필드 */
    private String chngField;

    /** 변경 필드 명 */
    private String chngFieldName;

    /** 변경전 필드 값 */
    private String chngFieldBeforeVal;

    /** 변경후 필드 값 */
    private String chngFieldAfterVal;

    /** 설명 */
    private String note;



    public String getChngFieldVal() {
        return this.getChngFieldBeforeVal()+ " ▶ "+ this.getChngFieldAfterVal();
    }



    public static <T extends BaseChangeHistory> T createInstance(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException |
                 InvocationTargetException e) {
            return null;
        }
    }

}
