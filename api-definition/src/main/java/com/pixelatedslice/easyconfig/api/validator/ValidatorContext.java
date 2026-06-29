package com.pixelatedslice.easyconfig.api.validator;

import org.jspecify.annotations.NullMarked;

import java.util.Collection;

@NullMarked
public interface ValidatorContext {
    void error(String message, Object... variables);

    boolean hasError();

    Collection<String> errors();
}
