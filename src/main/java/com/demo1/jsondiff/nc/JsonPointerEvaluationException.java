package com.demo1.jsondiff.nc;

import com.demo1.jsondiff.JsonPointer;
import com.fasterxml.jackson.databind.JsonNode;

public class JsonPointerEvaluationException extends Exception {
    private final JsonPointer path;
    private final JsonNode target;


    public JsonPointerEvaluationException(String message, JsonPointer path, JsonNode target) {
        super(message);
        this.path = path;
        this.target = target;
    }

    public JsonPointer getPath() {
        return path;
    }

    public JsonNode getTarget() {
        return target;
    }

}
