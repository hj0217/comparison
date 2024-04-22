package com.demo1.jsondiff;

import com.demo1.commonDto.Member;
import com.demo1.commonDto.Role;
import com.demo1.jsondiff.jsondiffDto.Category;
import com.demo1.jsondiff.jsondiffDto.DiffDto;
import com.demo1.jsondiff.lib.JsonDiff;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/jsonDiff")
public class JsonDiffController {

    @GetMapping("update")
    public String updateMemberInformation(//@RequestParam Member updateMemberDto,
                                           Model model) throws IOException {
        Member original = Member.builder()
                .id(101)
                .email("inn@innisfree.com")
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
//System.out.println(diffArray);

        List<DiffDto> jsondiffDtoList = new ArrayList<>(); // Json배열 담을 리스트

        for (JsonNode node : diffArray) {
            DiffDto dto = new DiffDto();

            dto.setOp(Category.fromValue(node.get("op").asText()));
            dto.setPath(node.get("path").asText());
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
