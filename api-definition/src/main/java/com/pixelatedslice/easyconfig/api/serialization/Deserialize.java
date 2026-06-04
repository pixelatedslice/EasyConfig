package com.pixelatedslice.easyconfig.api.serialization;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.NodeBuilderGroupStep;
import org.jspecify.annotations.NullMarked;

@FunctionalInterface
@NullMarked
public interface Deserialize<T> {
    Node deserialize(T value, NodeBuilderGroupStep.Container builder, SerializeContext context);
}
