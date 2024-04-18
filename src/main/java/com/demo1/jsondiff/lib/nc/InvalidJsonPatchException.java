package com.demo1.jsondiff.lib.nc;

public class InvalidJsonPatchException extends JsonPatchApplicationException {
    public InvalidJsonPatchException(String message) {
        super(message, null, null);
    }
}
