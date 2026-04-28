package com.pixelatedslice.easyconfig.api.exception;

import org.jspecify.annotations.NonNull;

public class ValidationException extends RuntimeException {
    public ValidationException(@NonNull Iterable<@NonNull String> messages) {
        super(String.join("\n", messages));
    }
}
