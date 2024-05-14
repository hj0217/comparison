package com.demo1.jsondiff;

import com.demo1.commonDto.Member;
import com.demo1.commonDto.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/jsonDiff")
public class JsonDiffController {

    @GetMapping("/update")
    public String findDiff(Model model) throws IOException {

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

        Member change = Member.builder()
                .id(101)
                .password("changedPassword")
                .role(Role.builder()
                        .role("ROLE_USER")
                        .build())
                .role(Role.builder()
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

       model.addAttribute("jsondiffDto", HistoryUtil.checkDiff(original, change));

        return "/history/jsonDiff";
    }

}
