package com.pixelatedslice.easyconfig.api.serialization;

import com.pixelatedslice.easyconfig.api.exception.DuplicateException;
import org.jspecify.annotations.NonNull;

import java.util.function.BinaryOperator;

public interface SerializerRegistryOptions {

    @NonNull
    SerializerRegistryOptions duplicateRegisterStyle(@NonNull DuplicateRegisterStyle style);

    SerializerRegistryOptions.@NonNull DuplicateRegisterStyle duplicateRegisterStyle();

    enum DuplicateRegisterStyle {
        THROW((first, replacement) -> {
            throw new DuplicateException(first, replacement);
        }),
        KEEP_ORIGINAL((first, _) -> first),
        REPLACE((_, replacement) -> replacement);

        private final BinaryOperator<Serializer<?>> action;

        DuplicateRegisterStyle(@NonNull BinaryOperator<Serializer<?>> action) {
            this.action = action;
        }

        public BinaryOperator<Serializer<?>> action() {
            return this.action;
        }

        public <T> @NonNull Serializer<T> apply(@NonNull Serializer<T> original, Serializer<T> replacement) {
            //noinspection unchecked
            return (Serializer<T>) this.action.apply(original, replacement);
        }
    }
}
