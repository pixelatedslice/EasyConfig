package com.pixelatedslice.easyconfig.api.config.node.serializer;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.exception.SerializeException;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public interface SerializerNode {

    @NonNull
    SerializerNode node(@NonNull String... node);

    @NonNull
    Optional<SerializerNode> parent();

    void set(@NonNull Object value);

    <T> T read(@NonNull TypeToken<T> token) throws SerializeException;

    default <T> T read(@NonNull Class<T> clazz) throws SerializeException {
        return read(TypeToken.of(clazz));
    }

    @NonNull
    String[] path();
}
