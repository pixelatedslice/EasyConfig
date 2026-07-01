package com.pixelatedslice.easyconfig.api.serialization;

import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@FunctionalInterface
@NullMarked
public interface Deserialize<T> {


    Node deserialize(T value, OldNodeBuilder.ContainerSafeStep.Original builder,
            SerializeContext context);

}
