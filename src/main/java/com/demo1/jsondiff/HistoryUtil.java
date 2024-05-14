package com.demo1.jsondiff;

import com.demo1.jsondiff.jsondiffDto.Category;
import com.demo1.jsondiff.jsondiffDto.DiffDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.DiffFlags;
import com.flipkart.zjsonpatch.JsonDiff;
import io.swagger.annotations.ApiModelProperty;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class HistoryUtil {

    public static List<DiffDto> checkDiff (Object original, Object change) throws IOException {

        /* Todo 예외 발생 케이스
        * 1. 2개의 매개변수는 동일 클래스 보장 필요 여부
        * 2. 기본변수 매개변수는 허용 x (original이 기본변수일 경우 apiModelProperty null예외 발생)
        */

        ObjectMapper mapper = new ObjectMapper();
        JsonNode diffNode = JsonDiff.asJson(mapper.valueToTree(original), mapper.valueToTree(change), EnumSet.of(DiffFlags.OMIT_COPY_OPERATION,DiffFlags.OMIT_MOVE_OPERATION, DiffFlags.ADD_ORIGINAL_VALUE_ON_REPLACE));

        if ( diffNode == null || diffNode.isEmpty()) return null;

        Class<?> clazz = original.getClass();
        //reflection field 돌면서 key(field) : value(annotation) 담기
        Map<String, String> fieldMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
            fieldMap.put(field.getName(), apiModelProperty.value());
        }

        List<DiffDto> jsondiffDtoList = new ArrayList<>();

        for (JsonNode node : diffNode) {
            DiffDto dto = new DiffDto();
            dto.setOp(Category.fromValue(node.get("op").asText()));

            //ToDo : path 구분로직 재검토 필요
            String[] processedPaths = node.get("path").asText().split("/");
            String processedPath = processedPaths[1];
            dto.setPath(fieldMap.get(processedPath));


            JsonNode beforeNode = node.get("fromValue");
            if (beforeNode != null) {
                if (beforeNode.isObject()) { //Array case
                    StringBuilder beforeSb = new StringBuilder();
                    for(int i = 0; i< beforeNode.size(); i++) {
                        beforeSb.append(beforeNode.path("role").asText()); //Todo 배열 필드명 추출할 수 있는 방법 있을지?
                        if(beforeNode.size() > 1) beforeSb.append(",");
                    }
                      dto.setBefore(beforeSb);
                } else { // String case
                    dto.setBefore(beforeNode.asText());
                }
            }

            JsonNode afterNode = node.get("value");
            if (afterNode != null) {
                if (afterNode.isObject()) {
                    StringBuilder afterSb = new StringBuilder();
                    for(int i = 0; i< afterNode.size(); i++) {
                        afterSb.append(afterNode.path("role").asText());
                        if(afterNode.size() > 1) afterSb.append(",");
                    }
                    dto.setAfter(afterSb);

                } else {
                    dto.setAfter(afterNode.asText());
                }
            }
            jsondiffDtoList.add(dto);
        }
        return jsondiffDtoList;

        }
}


