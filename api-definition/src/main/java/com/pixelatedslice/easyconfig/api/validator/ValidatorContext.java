package com.pixelatedslice.easyconfig.api.validator;

import org.jspecify.annotations.NonNull;

import java.util.Collection;

public interface ValidatorContext {
    void error(@NonNull String message, Object @NonNull ... variables);

    boolean hasError();

    Collection<String> errors();
}
