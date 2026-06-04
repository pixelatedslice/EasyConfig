package com.pixelatedslice.easyconfig.api.config.node.serializer;

import org.jspecify.annotations.NonNull;

import java.util.Optional;

public interface SerializerNode {

    @NonNull
    SerializerNode node(@NonNull String... node);

    @NonNull
    Optional<SerializerNode> parent();

    void set(@NonNull Object value);

    @NonNull
    String[] path();
}
