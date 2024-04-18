package com.demo1.mapdifference;


import com.demo1.commonDto.Member;
import com.demo1.commonDto.Role;
import com.demo1.mapdifference.mapdiffDto.MapDiffDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/member2")
public class MapDiffContoroller {

    @GetMapping("/update")
    public String update(Model model) {

        Member original = Member.builder()
                .id(101)
                .email("inn@innisfree.com")
                .password("innpassword")
//                .role(Role.builder()
//                        .id(1)
//                        .role("GUEST")
//                        .build())
//                .role(Role.builder()
//                        .id(2)
//                        .role("ROLE_USER")
//                        .build())
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
//                .role(Role.builder()
//                        .id(2)
//                        .role("ROLE_USER")
//                        .build())
//                .role(Role.builder()
//                        .id(4)
//                        .role("ROLE_MASTER")
//                        .build())
                .parent(Member.builder()
                        .id(201)
                        .email("changedInnep@innisfeeep.com")
                        .password("epPASSWORD")
                        .role(Role.builder()
                                .id(4)
                                .role("ROLE_MASTER")
                                .build())
                        .grandParent(Member.builder()
                                .id(301)
                                .password("chlwhdqhtlahq")
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

        MapDifference<String, Object> difference = Maps.difference(originalMap, changeMap);
System.out.println("확인용1"+difference);

        Map<String, MapDifference.ValueDifference<Object>> differenceMap = difference.entriesDiffering();
//System.out.println("확인용2"+differenceMap);

        List<MapDiffDto> mapDifferenceDto = new ArrayList<>();


       // difference.entriesDiffering().forEach((key, value) -> System.out.println(key + ": " + value));
        for (Map.Entry<String, MapDifference.ValueDifference<Object>> entry : difference.entriesDiffering().entrySet()) {

            String key = entry.getKey();
            MapDifference.ValueDifference<Object> value = entry.getValue();
            MapDiffDto dto = new MapDiffDto();

                dto.setPath(key); //id, email, address, parent
                dto.setBefore(value.leftValue());
                dto.setAfter(value.rightValue());
                if (value.leftValue() != null && value.rightValue() != null && !value.leftValue().equals(value.rightValue())) { // 수정
                    dto.setOp("수정");
                } else if (value.leftValue() == null && value.rightValue() != null) {
                    dto.setOp("추가");
                } else if (value.leftValue() != null && value.rightValue() == null) {
                    dto.setOp("삭제");
                }
            mapDifferenceDto.add(dto);
        }


        model.addAttribute("mapdiffDto", mapDifferenceDto);
        return "history/mapdiff";
    }

}
