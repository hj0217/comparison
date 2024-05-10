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

       model.addAttribute("jsondiffDto", HistoryUtil.checkDiff(change));

        return "/history/jsonDiff";
    }

}
