package com.pixelatedslice.easyconfig.impl.fileformat.common;

import com.pixelatedslice.easyconfig.api.config.node.ConfigNode;
import org.jspecify.annotations.NonNull;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;

public final class JacksonConfigNode {

    private JacksonConfigNode() {
    }

    public static <T> void write(
            @NonNull JsonGenerator generator,
            @NonNull ConfigNode<T> node
    ) {
        generator.writeName(node.key());
        JacksonWriteUtils.write(generator, node.valueOrDefault().orElse(null));
    }

    public static <T> ConfigNode<T> read(
            @NonNull JsonParser parser,
            @NonNull ConfigNode<T> node
    ) {
        T value = JacksonReadUtils.read(parser, node.typeToken());

        try (var mut = node.mutable()) {
            mut.setValue(value);
            return node;
        }
    }
}
