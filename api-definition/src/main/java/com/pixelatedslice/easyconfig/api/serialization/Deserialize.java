package com.pixelatedslice.easyconfig.api.serialization;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.NodeBuilder;
import org.jspecify.annotations.NonNull;

@FunctionalInterface
public interface Deserialize<T> {

    @NonNull
    Node deserialize(@NonNull T value, NodeBuilder.ContainerSafeStep.@NonNull Original builder, @NonNull SerializeContext context);

}
