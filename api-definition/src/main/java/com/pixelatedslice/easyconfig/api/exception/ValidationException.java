package com.pixelatedslice.easyconfig.api.exception;

import com.pixelatedslice.easyconfig.api.validator.ValidatorContext;
import org.jspecify.annotations.NonNull;

public class ValidationException extends IllegalArgumentException {

    private final @NonNull ValidatorContext context;

    public ValidationException(@NonNull ValidatorContext context) {
        super("Validation failed\n" + String.join("\n\t- ", context.errors()));
        this.context = context;
    }

    public @NonNull ValidatorContext context() {
        return this.context;
    }
}
