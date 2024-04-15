package com.demo1.jsondiff.nc;

public class InvalidJsonPatchException extends JsonPatchApplicationException {
    public InvalidJsonPatchException(String message) {
        super(message, null, null);
    }
}
