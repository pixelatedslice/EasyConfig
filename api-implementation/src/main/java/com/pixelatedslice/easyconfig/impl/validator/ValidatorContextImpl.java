package com.pixelatedslice.easyconfig.impl.validator;

import com.pixelatedslice.easyconfig.api.validator.ValidatorContext;
import org.jspecify.annotations.NullMarked;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@NullMarked
public class ValidatorContextImpl implements ValidatorContext {

    private final Map<String, Object[]> errors = new ConcurrentHashMap<>();

    @Override
    public boolean hasError() {
        return !this.errors.isEmpty();
    }

    public Collection<String> errors() {
        return this.errors.entrySet().stream().map(entry -> entry.getKey().formatted(entry.getValue())).toList();
    }

    @Override
    public void error(String message, Object... variables) {
        this.errors.put(message, variables);
    }

}
