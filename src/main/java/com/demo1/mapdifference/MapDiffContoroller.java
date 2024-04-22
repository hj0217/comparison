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

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mapdiff")
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
//                        .grandParent(Member.builder()
//                                .id(301)
//                                .email("amore@pacific.com")
//                                .role(Role.builder()
//                                        .id(4)
//                                        .role("ROLE_MASTER")
//                                        .build())
//                                .build())
                        .build())
                .build();

        Member change = Member.builder()
                .id(102)
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
//                        .grandParent(Member.builder()
//                                .id(301)
//                                .password("chlwhdqhtlahq")
//                                .role(Role.builder()
//                                        .id(4)
//                                        .role("MASTER_MASTER")
//                                        .build())
//                                .build())
                        .date("2023-01-10")
                        .build())
                .build();


        ObjectMapper mapper = new ObjectMapper();
        Map originalMap = mapper.convertValue(original, Map.class);
        Map changeMap = mapper.convertValue(change, Map.class);

        MapDifference<String, Object> difference = Maps.difference(originalMap, changeMap);
System.out.println("확인용:::"+difference);

        List<MapDiffDto> mapDifferenceDto = new ArrayList<>();


        for (Map.Entry<String, MapDifference.ValueDifference<Object>> entry : difference.entriesDiffering().entrySet()) {

            //1 depth
            if(!entry.getKey().equals("parent")) {
                MapDiffDto dto = new MapDiffDto();
                dto.setPath(entry.getKey());//email, password
                dto.setBefore(entry.getValue().leftValue());
                dto.setAfter(entry.getValue().rightValue());
                if (entry.getValue().leftValue() != null && entry.getValue().rightValue() != null && !entry.getValue().leftValue().equals(entry.getValue().rightValue())) { // 수정
                    dto.setOp("수정");
                } else if (entry.getValue().leftValue() == null && entry.getValue().rightValue() != null) {
                    dto.setOp("추가");
                } else if (entry.getValue().leftValue() != null && entry.getValue().rightValue() == null) {
                    dto.setOp("삭제");
                }
                mapDifferenceDto.add(dto);

            }

            //2 depth
            if(entry.getKey().contains("parent")) {
                List<MapDiffDto> parentDifferences = MapDiffUtil.findDifferences(original.getParent(), change.getParent(), "parent");
                List<MapDiffDto> filteredParentDifferences = parentDifferences.stream()  //parent.grandParent는 빼기
                        .filter(dto -> !dto.getPath().equals("parent.grandParent"))
                        .toList();
                mapDifferenceDto.addAll(filteredParentDifferences);
                //3 depth
                if(entry.getKey().contains("grandParent")) {
                    List<MapDiffDto> grandParentDifferences = MapDiffUtil.findDifferences(original.getParent().getGrandParent(), change.getParent().getGrandParent(), "parent.grandParent");
                    mapDifferenceDto.addAll(grandParentDifferences);
                }
            }
        }

        model.addAttribute("mapdiffDto", mapDifferenceDto);
        return "history/mapdiff";
    }
}


