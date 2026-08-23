package com.pixelatedslice.easyconfig.api.validator.null_policy;

import org.jspecify.annotations.Nullable;

@FunctionalInterface
public interface NullPolicy {
    void handle(@Nullable Object any);
}