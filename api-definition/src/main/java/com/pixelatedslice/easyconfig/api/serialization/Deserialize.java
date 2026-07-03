package com.pixelatedslice.easyconfig.api.serialization;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderGroupStep;
import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@FunctionalInterface
@NullMarked
public interface Deserialize<T> {
    Node deserialize(T value, FactoryNodeBuilderGroupStep.Container builder, SerializeContext context);
}
