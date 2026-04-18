package com.pixelatedslice.easyconfig.api.exception;

import org.jspecify.annotations.NonNull;

import java.util.Objects;

public class BrokenNodeKeyException extends RuntimeException {
    public BrokenNodeKeyException(@NonNull String providedKey, @NonNull String regex) {
        Objects.requireNonNull(providedKey);
        Objects.requireNonNull(regex);
        super(String.format("The provided key \"%s\" does not match the Regex \"%s\"", providedKey, regex));
    }
}