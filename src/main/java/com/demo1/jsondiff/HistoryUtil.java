package com.demo1.jsondiff;

import com.demo1.commonDto.Member;
import com.demo1.commonDto.Role;
import com.demo1.jsondiff.jsondiffDto.Category;
import com.demo1.jsondiff.jsondiffDto.DiffDto;
import com.demo1.jsondiff.lib.JsonDiff;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModelProperty;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class HistoryUtil {

    public static List<DiffDto> checkDiff (Member change) throws IOException {

        Member original = Member.builder()
                .id(101).email("inn@innisfree.com")
                .password("innpassword")
                .role(Role.builder()
                        .role("GUEST")
                        .build())
                .role(Role.builder()
                        .role("ROLE_USER")
                        .build())
                .parent(Member.builder()
                        .id(201)
                        .email("innep@innisfeeep.com")
                        .password("epPASSWORD")
                        .role(Role.builder()
                                .role("ROLE_ADMIN")
                                .build())
                        .grandParent(Member.builder()
                                .id(301)
                                .email("amore@pacific.com")
                                .role(Role.builder()
                                        .role("ROLE_MASTER")
                                        .build())
                                .build())
                        .build())
                .build();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode diffNode = JsonDiff.asJson(mapper.valueToTree(original), mapper.valueToTree(change));

        if (diffNode.isEmpty()) {
            // ToDo 널 return 시, 받는 쪽에서 분기 필요한지?
            return null;
        } else {

            JsonNode diffArray = mapper.readTree(diffNode.toPrettyString());
            Class<?> clazz = original.getClass();

            //reflection field 돌면서 key(field) : value(annotation) 담기
            Map<String, String> fieldMap = new HashMap<>();
            for (Field field : clazz.getDeclaredFields()) {
                ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                fieldMap.put(field.getName(), apiModelProperty.value());
            }


            List<DiffDto> jsondiffDtoList = new ArrayList<>();

            for (JsonNode node : diffArray) {
                DiffDto dto = new DiffDto();
                dto.setOp(Category.fromValue(node.get("op").asText()));

                //ToDo : path 구분로직 재검토 필요
                String[] processedPaths = node.get("path").asText().split("/");
                String processedPath = processedPaths[1];
                dto.setPath(fieldMap.get(processedPath));


                JsonNode beforeNode = node.get("before");
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

                JsonNode afterNode = node.get("after");
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
            return jsondiffDtoList; // DB에 넣기

            /* predict
             * 분류 |  필드명  |    변경 전  |   변경 후
             * ========================================
             * 수정 | 이메일   |  apple@com | banana@com
             * -----------------------------------------
             */
        }
     }
}

