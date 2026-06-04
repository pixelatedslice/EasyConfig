package com.pixelatedslice.easyconfig.api.config.node;

import com.google.errorprone.annotations.CheckReturnValue;
import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.NodeBuilder;
import org.jspecify.annotations.NullMarked;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;


@NullMarked
public interface Node {
    default @NonNull NodeType nodeType() {
        return NodeType.PLAIN_NODE;
    }

    @NonNull String key();

    @NonNull ReturnedNode parent();

    @NonNull
    @CheckReturnValue
    NodeBuilder toBuilder();

    @NonNull
    @CheckReturnValue
    ConfigStructure toStructure();

    default @NonNull String[] fullPath() {
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
