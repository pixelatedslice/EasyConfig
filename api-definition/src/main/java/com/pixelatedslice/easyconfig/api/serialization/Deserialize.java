package com.pixelatedslice.easyconfig.api.serialization;

import com.pixelatedslice.easyconfig.api.config.node.serializer.SerializerNode;
import com.pixelatedslice.easyconfig.api.serialization.context.SerializeContext;
import org.jspecify.annotations.NullMarked;

@FunctionalInterface
@NullMarked
public interface Deserialize<T> {
    void deserialize(T value, SerializerNode node, SerializeContext context);
}
