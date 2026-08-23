package com.pixelatedslice.easyconfig.api.serialization;

import com.pixelatedslice.easyconfig.api.exception.DuplicateException;
import org.jspecify.annotations.NullMarked;

import java.util.function.BinaryOperator;

@SuppressWarnings("unused")
@NullMarked
public interface SerializerRegistryOptions {


    @SuppressWarnings("UnusedReturnValue")
    SerializerRegistryOptions duplicateRegisterStyle(DuplicateRegisterStyle style);

    SerializerRegistryOptions.DuplicateRegisterStyle duplicateRegisterStyle();

    enum DuplicateRegisterStyle {
        THROW((first, replacement) -> {
            throw new DuplicateException(first, replacement);
        }),
        KEEP_ORIGINAL((first, _) -> first),
        REPLACE((_, replacement) -> replacement);

        private final BinaryOperator<Serializer<?>> action;

        DuplicateRegisterStyle(BinaryOperator<Serializer<?>> action) {
            this.action = action;
        }

        public BinaryOperator<Serializer<?>> action() {
            return this.action;
        }

        public <T> Serializer<T> apply(Serializer<T> original, Serializer<T> replacement) {
            //noinspection unchecked
            return (Serializer<T>) this.action.apply(original, replacement);
        }
    }
}
