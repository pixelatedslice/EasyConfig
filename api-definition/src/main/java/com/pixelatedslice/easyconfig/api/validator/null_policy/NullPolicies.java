package com.pixelatedslice.easyconfig.api.validator.null_policy;

import org.jspecify.annotations.Nullable;

public interface NullPolicies {
    @SuppressWarnings("unused")
    NullPolicy IGNORE = (@Nullable Object _) -> {
    };
    NullPolicy THROW = (@Nullable Object any) -> {
        if (any != null) {
            return;
        }
        throw new IllegalArgumentException("This method does not accept null args.");
    };
}