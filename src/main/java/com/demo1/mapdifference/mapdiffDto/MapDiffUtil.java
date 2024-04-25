package com.demo1.mapdifference.mapdiffDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapDiffUtil {
    public static List<MapDiffDto> findDifferences(Object original, Object changed, String path) {
        ObjectMapper mapper = new ObjectMapper();

        // 각 객체를 맵으로 변환
        Map<String, Object> originalMap = convertToMap(original, mapper);
        Map<String, Object> changedMap = convertToMap(changed, mapper);

        MapDifference<String, Object> difference = Maps.difference(originalMap, changedMap);

        List<MapDiffDto> mapDifferenceDto = new ArrayList<>();

        for (Map.Entry<String, MapDifference.ValueDifference<Object>> entry : difference.entriesDiffering().entrySet()) {
            String key = entry.getKey();
            MapDifference.ValueDifference<Object> value = entry.getValue();
            String currentPath = path + "." + key;

            // 값이 객체인 경우 재귀적으로 탐색
            if (value.leftValue() instanceof Map && value.rightValue() instanceof Map) {
                mapDifferenceDto.addAll(findDifferences(value.leftValue(), value.rightValue(), currentPath));
            } else {
                MapDiffDto dto = new MapDiffDto();
                dto.setPath(currentPath);
                dto.setBefore(value.leftValue());
                dto.setAfter(value.rightValue());
                if (value.leftValue() != null && value.rightValue() != null && !value.leftValue().equals(value.rightValue())) {
                    dto.setOp("수정");
                } else if (value.leftValue() == null && value.rightValue() != null) {
                    dto.setOp("추가");
                } else if (value.leftValue() != null && value.rightValue() == null) {
                    dto.setOp("삭제");
                }
                mapDifferenceDto.add(dto);
            }
        }
        return mapDifferenceDto;
    }

    // 객체를 맵으로 변환하는 유틸리티 메서드
    private static Map<String, Object> convertToMap(Object object, ObjectMapper mapper) {
        return mapper.convertValue(object, Map.class);
    }
}
