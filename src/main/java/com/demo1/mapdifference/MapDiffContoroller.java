package com.demo1.mapdifference;


import com.demo1.commonDto.Member;
import com.demo1.commonDto.Role;
import com.demo1.mapdifference.mapdiffDto.MapDiffDto;
import com.demo1.mapdifference.mapdiffDto.MapDiffUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/mapdiff")
public class MapDiffContoroller {

    @GetMapping("/update")
    public String update(Model model) {
        //데이터1
        Member original = Member.builder()
                .email("inn@innisfree.com")
                .password("innpassword")
                .role(Role.builder()
                        .id(102)
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
                                .build())
                        .build())
                .build();

        //데이터2
        Member change = Member.builder()
                .password("changedPassword")
                .role(Role.builder()
                        .id(102)
                        .role("GUEST")
                        .build())
                .role(Role.builder()
                        .id(2)
                        .role("ROLE_USER")
                        .build())
                .parent(Member.builder()
                        .id(202)
                        .email("changedInnep@innisfeeep.com")
                        .password("epPASSWORD")
                        .role(Role.builder()
                                .id(4)
                                .role("ROLE_MASTER")
                                .build())
                        .grandParent(Member.builder()
                                .id(302)
                                .email("chlwhdqhtlahq")
                                .role(Role.builder()
                                        .id(4)
                                        .role("MASTER_MASTER")
                                        .build())
                                .build())
                        .build())
                .build();


        ObjectMapper mapper = new ObjectMapper();
        Map originalMap = mapper.convertValue(original, Map.class);
        Map changeMap = mapper.convertValue(change, Map.class);

        List<MapDiffDto>  mapDifferenceDto = MapDiffUtil.findDifferences(originalMap, changeMap, "");

        model.addAttribute("mapdiffDto", mapDifferenceDto);
        return "history/mapdiff";
    }
}

