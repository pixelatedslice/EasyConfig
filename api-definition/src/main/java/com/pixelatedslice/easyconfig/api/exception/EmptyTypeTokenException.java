package com.pixelatedslice.easyconfig.api.exception;

import org.jspecify.annotations.NonNull;

import java.util.Objects;

@SuppressWarnings("unused")
public final class EmptyTypeTokenException extends RuntimeException {
    private EmptyTypeTokenException(@NonNull String message) {
        Objects.requireNonNull(message);
        super(message);
    }

    public static EmptyTypeTokenException emptyNodeTypeToken() {
        return new EmptyTypeTokenException("A ConfigNode cannot have an empty TypeToken!");
    }

    public static EmptyTypeTokenException emptySectionTypeToken() {
        return new EmptyTypeTokenException("A ConfigSection cannot have an empty TypeToken!");
    }
}
