package com.demo1.mapdifference;

import com.demo1.mapdifference.mapdiffDto.MapDiffDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapDiffUtil {
    public static List<MapDiffDto> findDifferences(Object original, Object changed, String path) {
        ObjectMapper mapper = new ObjectMapper();
        Map originalMap = mapper.convertValue(original, Map.class);
        Map changedMap = mapper.convertValue(changed, Map.class);

        MapDifference<String, Object> difference = Maps.difference(originalMap, changedMap);

        List<MapDiffDto> mapDifferenceDto = new ArrayList<>();

        for (Map.Entry<String, MapDifference.ValueDifference<Object>> entry : difference.entriesDiffering().entrySet()) {
            String key = entry.getKey();
            MapDifference.ValueDifference<Object> value = entry.getValue();
                MapDiffDto dto = new MapDiffDto();
                dto.setPath(path + "." + key);
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

        // Recursively find differences in nested objects
        for (Map.Entry<String, Object> entry : difference.entriesOnlyOnLeft().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String newPath = path + "." + key;
            mapDifferenceDto.addAll(findDifferences(value, null, newPath));
        }

        for (Map.Entry<String, Object> entry : difference.entriesOnlyOnRight().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String newPath = path + "." + key;
            mapDifferenceDto.addAll(findDifferences(null, value, newPath));
        }

        return mapDifferenceDto;
    }
}
