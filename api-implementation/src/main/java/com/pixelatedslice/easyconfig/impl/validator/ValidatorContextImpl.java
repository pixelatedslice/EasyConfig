package com.pixelatedslice.easyconfig.impl.validator;

import com.pixelatedslice.easyconfig.api.validator.ValidatorContext;
import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ValidatorContextImpl implements ValidatorContext {

    private final Map<@NonNull String, @NonNull Object @NonNull []> errors = new ConcurrentHashMap<>();

    @Override
    public boolean hasError() {
        return !this.errors.isEmpty();
    }

    public Collection<String> errors() {
        return errors.entrySet().stream().map(entry -> entry.getKey().formatted(entry.getValue())).toList();
    }

    @Override
    public void error(@NonNull String message, @NonNull Object @NonNull ... variables) {
        this.errors.put(message, variables);
    }

}
