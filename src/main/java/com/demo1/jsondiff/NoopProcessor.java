package com.demo1.jsondiff;

import com.demo1.jsondiff.nc.JsonPatchProcessor;
import com.fasterxml.jackson.databind.JsonNode;

public class NoopProcessor implements JsonPatchProcessor {
    static final NoopProcessor INSTANCE;
    static {
        INSTANCE = new NoopProcessor();
    }

    @Override public void remove(JsonPointer path, JsonNode before) {}
    @Override public void replace(JsonPointer path, JsonNode value, JsonNode before) {}
    @Override public void add(JsonPointer path, JsonNode value) {}
    @Override public void move(JsonPointer fromPath, JsonPointer toPath) {}
    @Override public void copy(JsonPointer fromPath, JsonPointer toPath) {}
    @Override public void test(JsonPointer path, JsonNode value) {}


}
