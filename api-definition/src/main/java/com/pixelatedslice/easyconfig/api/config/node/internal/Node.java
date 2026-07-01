package com.pixelatedslice.easyconfig.api.config.node.internal;

import com.google.errorprone.annotations.CheckReturnValue;
import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import com.pixelatedslice.easyconfig.api.config.node.NodeType;
import com.pixelatedslice.easyconfig.api.config.node.ReturnedNode;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilder;
import org.jspecify.annotations.NullMarked;

import java.util.stream.Stream;

@SuppressWarnings("unused")
@NullMarked
public sealed interface Node permits com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode,
        com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode,
        com.pixelatedslice.easyconfig.api.config.node.env.EnvNode,
        com.pixelatedslice.easyconfig.api.config.node.for_impl.ForImplNode,
        com.pixelatedslice.easyconfig.api.config.node.value.ValueNode {
    default NodeType nodeType() {
        return NodeType.PLAIN_NODE;
    }

    String key();

    ReturnedNode parent();


    @CheckReturnValue
    FactoryNodeBuilder.KeyStep<?> toBuilder();


    @CheckReturnValue
    ConfigStructure toStructure();

    default String[] fullPath() {
        Stream<String> stream = Stream.empty();
        Node current = this;
        while (true) {
            stream = Stream.concat(stream, Stream.of(current.key()));
            if (current.parent().plainNode().isEmpty()) {
                break;
            }
            current = current.parent().plainNode().get();
        }

        final var result = stream.toArray(String[]::new);
        final var reversed = new String[result.length];
        for (int i = 0; i < result.length; i++) {
            reversed[result.length - (1 + i)] = result[i];
        }
        
        return reversed;
    }
}
