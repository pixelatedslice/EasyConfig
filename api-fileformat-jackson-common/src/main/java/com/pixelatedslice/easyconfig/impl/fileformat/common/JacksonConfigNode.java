package com.pixelatedslice.easyconfig.impl.fileformat.common;

import com.pixelatedslice.easyconfig.api.config.node.ConfigNode;
import org.jspecify.annotations.NonNull;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;

public final class JacksonConfigNode {

    private JacksonConfigNode() {
    }

    public static <T> void write(@NonNull JsonGenerator generator, @NonNull ConfigNode<T> node) {
        generator.writeStartObject();
        generator.writeName(node.key());
        JacksonWriteUtils.write(generator, node.valueOrDefault().orElse(null));
        generator.writeEndObject();
    }

    public static <T> ConfigNode<T> read(
            @NonNull JsonParser parser,
            @NonNull ConfigNode<T> node
    ) {
        if (parser.nextToken() != JsonToken.START_OBJECT) {
            throw new IllegalStateException("There is no object to parse!");
        }

        if (!node.key().equals(parser.currentName())) {
            throw new IllegalStateException("The node's key and the current field key don't match!");
        }

        parser.nextToken();

        T value = JacksonReadUtils.read(parser, node.typeToken());

        try (var mut = node.mutable()) {
            mut.setValue(value);
            return node;
        }
    }
}
