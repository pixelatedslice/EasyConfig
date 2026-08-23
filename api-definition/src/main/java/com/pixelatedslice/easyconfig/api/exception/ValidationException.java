package com.pixelatedslice.easyconfig.api.exception;

import com.pixelatedslice.easyconfig.api.validator.ValidatorContext;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class ValidationException extends IllegalArgumentException {

    private final ValidatorContext context;

    public ValidationException(ValidatorContext context) {
        super("Validation failed\n" + String.join("\n\t- ", context.errors()));
        this.context = context;
    }

    public ValidatorContext context() {
        return this.context;
    }
}
