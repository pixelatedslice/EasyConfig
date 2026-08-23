package com.pixelatedslice.easyconfig.api.exception;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.serialization.SerializerType;
import org.jspecify.annotations.NullMarked;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.serializer.SerializerNode;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

@NullMarked
public class SerializerException extends RuntimeException {
    public SerializerException(@NonNull String message) {
        Objects.requireNonNull(message);
        super(message);
    }

    public static class MissingSerializerException extends SerializerException {

        public MissingSerializerException(@NonNull Node node) {
            Objects.requireNonNull(node);
            super(String.join(" -> ", node.fullPath()) + " is missing a serializer");
        }

        public MissingSerializerException(@NonNull SerializerNode node) {
            Objects.requireNonNull(node);
            super(String.join(" -> ", node.path()) + " is missing a serializer");
        }
    }
}
