package com.demo1.jsondiff.lib;

import com.demo1.jsondiff.lib.nc.JsonPatchProcessor;
import com.fasterxml.jackson.databind.JsonNode;

public class NoopProcessor implements JsonPatchProcessor {
    static final NoopProcessor INSTANCE;
    static {
        INSTANCE = new NoopProcessor();
    }

    @Override public void remove(JsonPointer path) {}
    @Override public void replace(JsonPointer path, JsonNode value) {}
    @Override public void add(JsonPointer path, JsonNode value) {}
    @Override public void move(JsonPointer fromPath, JsonPointer toPath) {}
    @Override public void copy(JsonPointer fromPath, JsonPointer toPath) {}
    @Override public void test(JsonPointer path, JsonNode value) {}


}