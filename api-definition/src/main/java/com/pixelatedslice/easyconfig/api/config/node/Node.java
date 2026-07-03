package com.pixelatedslice.easyconfig.api.config.node;

import com.google.errorprone.annotations.CheckReturnValue;
import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.CommonNodes;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.Nodes;
import org.jspecify.annotations.NullMarked;

import java.util.function.BiFunction;
import java.util.stream.Stream;


@SuppressWarnings("unused")
@NullMarked
public interface Node {
    static <N extends Node> N of(
            BiFunction<? super Nodes, ? super CommonNodes, ? extends FactoryNodeBuilder.BuildStep<N>> builder) {
        return builder.apply(Nodes.INSTANCE, CommonNodes.INSTANCE).build();
    }

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
