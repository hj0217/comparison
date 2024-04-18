package com.demo1.jsondiff.lib;

import com.demo1.jsondiff.lib.nc.CompatibilityFlags;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.EnumSet;

public class CopyingApplyProcessor extends InPlaceApplyProcessor {

    CopyingApplyProcessor(JsonNode target) {
        this(target, CompatibilityFlags.defaults());
    }

    CopyingApplyProcessor(JsonNode target, EnumSet<CompatibilityFlags> flags) {
        super(target.deepCopy(), flags);
    }
}
