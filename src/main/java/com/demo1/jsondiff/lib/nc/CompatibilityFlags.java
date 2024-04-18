package com.demo1.jsondiff.lib.nc;

import java.util.EnumSet;

public enum CompatibilityFlags {
    MISSING_VALUES_AS_NULLS,
    REMOVE_NONE_EXISTING_ARRAY_ELEMENT,
    ALLOW_MISSING_TARGET_OBJECT_ON_REPLACE,
    FORBID_REMOVE_MISSING_OBJECT;

    public static EnumSet<CompatibilityFlags> defaults() {
        return EnumSet.noneOf(CompatibilityFlags.class);
    }
}
