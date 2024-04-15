package com.demo1.jsondiff;

import com.fasterxml.jackson.databind.JsonNode;

public class Diff {
    private final Operation operation;
    private final JsonPointer path;
    private final JsonNode value;
    private JsonPointer toPath; //only to be used in move operation
    private final JsonNode srcValue; // only used in replace operation


    Diff(Operation operation, JsonPointer path, JsonNode value) {
        this.operation = operation;
        this.path = path;
        this.value = value;
        this.srcValue = null;
    }


    Diff(Operation operation, JsonPointer fromPath, JsonPointer toPath) {
        this.operation = operation;
        this.path = fromPath;
        this.toPath = toPath;
        this.value = null;
        this.srcValue = null;
    }


    Diff(Operation operation, JsonPointer path, JsonNode srcValue, JsonNode value) {
        this.operation = operation;
        this.path = path;
        this.value = value;
        this.srcValue = srcValue;
    }

    public Operation getOperation() {
        return operation;
    }

    public JsonPointer getPath() {
        return path;
    }

    public JsonNode getValue() {
        return value;
    }

    public static Diff generateDiff(Operation replace, JsonPointer path, JsonNode target) {
        return new Diff(replace, path, target);
    }

    public static Diff generateDiff(Operation replace, JsonPointer path, JsonNode source, JsonNode target) {
        return new Diff(replace, path, source, target);
    }



    JsonPointer getToPath() {
        return toPath;
    }

    public JsonNode getSrcValue(){
        return srcValue;
    }
}
