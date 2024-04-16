package com.demo1.jsondiff.nc;

import com.demo1.jsondiff.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;

public interface JsonPatchProcessor {
    void remove(JsonPointer path) throws JsonPointerEvaluationException;
    void replace(JsonPointer path, JsonNode value) throws JsonPointerEvaluationException;
    void add(JsonPointer path, JsonNode value) throws JsonPointerEvaluationException;
    void move(JsonPointer fromPath, JsonPointer toPath) throws JsonPointerEvaluationException;
    void copy(JsonPointer fromPath, JsonPointer toPath) throws JsonPointerEvaluationException;
    void test(JsonPointer path, JsonNode value) throws JsonPointerEvaluationException;

}
