package com.demo1.jsondiff.member;

import com.demo1.jsondiff.JsonDiff;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("update")
    public String updateMemberInformation(@RequestParam int index, Model model) {
        Member original = Member.builder()
                .id(10001)
                .email("Test@email.com")
                .password("password")
                .role(Role.builder()
                        .role("ROLE_ADMIN")
                        .build())
                .role(Role.builder()
                        .role("ROLE_USER")
                        .build())
                .parent(Member.builder()
                        .id(101)
                        .email("test2@email.com")
                        .password("password")
                        .build())
                .build();

        Member change = Member.builder()
                .id(10001)
                .email("changeTest@email.com")
                .password("password")
                .role(Role.builder()
                        .role("ROLE_USER")
                        .build())
                .role(Role.builder()
                        .role("ROLE_MASTER")
                        .build())
                .parent(Member.builder()
                        .id(101)
                        .email("test@email.com")
                        .password("password")
                        .build())
                .build();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode diffNode = JsonDiff.asJson(mapper.valueToTree(original), mapper.valueToTree(change));

        System.out.println(diffNode.toPrettyString());

        return "history/detail";
    }
}
