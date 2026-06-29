package com.pixelatedslice.easyconfig.api.exception;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.serialization.SerializerType;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@SuppressWarnings("unused")
@NullMarked
public class SerializerException extends RuntimeException {
    public SerializerException(String message) {
        Objects.requireNonNull(message);

        super(message);
    }

    public SerializerException(String message, Object... args) {
        Objects.requireNonNull(message);
        Objects.requireNonNull(args);

        super(String.format(message, args));
    }

    public static SerializerException DID_NOT_EXPECT_SERIALIZER_TYPE(
            SerializerType expected,
            SerializerType received
    ) {
        Objects.requireNonNull(expected);
        Objects.requireNonNull(received);

        return new SerializerException(
                "Type mismatch for serializer: Expected %s but encountered %s.",
                expected.toString(), received
        );
    }

    public static SerializerException SERIALIZER_HAS_WRONG_SUPPORTED_TYPE(
            TypeToken<?> nodeType,
            TypeToken<?> expectedType
    ) {
        Objects.requireNonNull(nodeType);
        Objects.requireNonNull(expectedType);

        return new SerializerException("Supported type mismatch for serializer: Expected %s but found %s.",
                expectedType, nodeType.toString()
        );
    }
}
