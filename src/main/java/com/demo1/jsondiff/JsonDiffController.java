package com.demo1.jsondiff;

import com.demo1.commonDto.Member;
import com.demo1.commonDto.Role;
import com.demo1.jsondiff.jsondiffDto.Category;
import com.demo1.jsondiff.jsondiffDto.DiffDto;
import com.demo1.jsondiff.lib.JsonDiff;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/jsonDiff")
public class JsonDiffController {

    @GetMapping("update")
    public String updateInformation(//@RequestParam Member updateMemberDto,
                                           Model model) throws IOException, ClassNotFoundException {
        Member original = Member.builder()
                .id(101).email("inn@innisfree.com")
                .password("innpassword")
                .role(Role.builder()
                        .id(1)
                        .role("GUEST")
                        .build())
                .role(Role.builder()
                        .id(2)
                        .role("ROLE_USER")
                        .build())
                .parent(Member.builder()
                        .id(201)
                        .email("innep@innisfeeep.com")
                        .password("epPASSWORD")
                        .role(Role.builder()
                                .id(3)
                                .role("ROLE_ADMIN")
                                .build())
                        .grandParent(Member.builder()
                                .id(301)
                                .email("amore@pacific.com")
                                .role(Role.builder()
                                        .id(4)
                                        .role("ROLE_MASTER")
                                        .build())
                                .build())
                        .build())
                .build();

        Member change = Member.builder()
                .id(101)
                .password("changedPassword")
                .role(Role.builder()
                        .id(2)
                        .role("ROLE_USER")
                        .build())
                .role(Role.builder()
                        .id(4)
                        .role("ROLE_MASTER")
                        .build())
                .parent(Member.builder()
                        .id(201)
                        .email("changedInnep@innisfeeep.com")
                        .password("epPASSWORD")
                        .grandParent(Member.builder()
                                .id(301)
                                .password("chlwhdqhtlahq")
                                .build())
                        .build())
                .build();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode diffNode = JsonDiff.asJson(mapper.valueToTree(original), mapper.valueToTree(change));
//        System.out.println(diffNode.toPrettyString());

        //DB 저장
        JsonNode diffArray = mapper.readTree(diffNode.toPrettyString());
        //DB 클래스 타입 저장 i.g) com.demo1.commonDto.Member
        Class<?> clazz = original.getClass();



        List<DiffDto> jsondiffDtoList = new ArrayList<>(); // Json배열 담을 리스트





        for (JsonNode node : diffArray) {
            DiffDto dto = new DiffDto();
            dto.setOp(Category.fromValue(node.get("op").asText()));
            //dto.setPath(node.get("path").asText());

            /*
             * 테스트중
             */
            String path = node.get("path").asText(); //path 벨류로 클래스 필드 검사진행
System.out.println(path);
            for (Field field : clazz.getDeclaredFields()) { // 클래스 정의의 모든 필드를 반복하여 검사합니다.

                if (path.toLowerCase().contains(field.getName())) {
                    ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);  // 필드에서 Swagger 어노테션 찾기 (예: @ApiModelProperty)
                    dto.setPath(apiModelProperty.value());
                }
            }

            /*
             * end of 테스트
             */

            JsonNode beforeNode = node.get("before");
            if (beforeNode != null) {
                if (beforeNode.isObject()) {
                    dto.setBefore(beforeNode.toString());
                } else {
                    dto.setBefore(beforeNode.asText());
                }
            }
            JsonNode afterNode = node.get("after");
            if (afterNode != null) {
                if (afterNode.isObject()) {
                    dto.setAfter(afterNode.toString());
                } else {
                    dto.setAfter(afterNode.asText());
                }
            }

            jsondiffDtoList.add(dto);
        }
        model.addAttribute("jsondiffDto", jsondiffDtoList);


        return "history/jsondiff";
    }
}
