package com.demo1.jsondiff.nc;

import com.demo1.jsondiff.JsonPointer;
import com.demo1.jsondiff.Operation;

public class JsonPatchApplicationException extends RuntimeException{
    final Operation operation;
    final JsonPointer path;

    public JsonPatchApplicationException(String message, Operation operation, JsonPointer path) {
        super(message);
        this.operation = operation;
        this.path = path;
    }

    public Operation getOperation() {
        return operation;
    }

    public JsonPointer getPath() {
        return path;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (operation != null) sb.append('[').append(operation).append(" Operation] ");
        sb.append(getMessage());
        if (path != null) sb.append(" at ").append(path.isRoot() ? "root" : path);
        return sb.toString();
    }
}
