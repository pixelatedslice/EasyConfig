package com.pixelatedslice.easyconfig.api.exception;

import com.google.common.reflect.TypeToken;
import org.jspecify.annotations.NonNull;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Objects;

public class BuiltInSerializerUnregisterException extends RuntimeException {
    public BuiltInSerializerUnregisterException(@NonNull Collection<? extends @NonNull TypeToken<?>> classes) {
        Objects.requireNonNull(classes);
        super(message(classes));
    }

    private static String message(@NonNull Collection<? extends @NonNull TypeToken<?>> classes) {
        return "The built-in serializers cannot be unregistered!\n" + "Tried unregistering the serializers for the " +
                "classes: " + String.join(
                "\", \"",
                classes.stream().map(TypeToken::getType).map(Type::toString).toList());
    }
}