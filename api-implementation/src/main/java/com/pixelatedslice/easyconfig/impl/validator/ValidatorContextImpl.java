package com.pixelatedslice.easyconfig.impl.validator;

import com.pixelatedslice.easyconfig.api.exception.ValidationException;
import com.pixelatedslice.easyconfig.api.validator.ValidatorContext;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class ValidatorContextImpl implements ValidatorContext {
    private final Set<String> errors = new HashSet<>();

    @Override
    public void error(@NonNull String message, @Nullable Object @NonNull ... variables) {
        this.errors.add(String.format(message, variables));
    }

    /**
     * Throws a {@link ValidationException} containing all collected errors if any exist.
     * Does nothing if no errors have been added.
     */
    @Override
    public void throwIfErrors() {
        if (this.errors.isEmpty()) {
            return;
        }
        throw new ValidationException(this.errors);
    }
}
