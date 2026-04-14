package com.pixelatedslice.easyconfig.impl.fileformat.common;

import com.pixelatedslice.easyconfig.api.config.node.ConfigNode;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import org.jspecify.annotations.NonNull;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;

import java.util.ArrayList;

public final class JacksonConfigSection {

    private JacksonConfigSection() {
    }

    public static void write(
            @NonNull JsonGenerator generator,
            @NonNull ConfigSection section
    ) {
        generator.writeName(section.key());

        generator.writeStartObject();

        for (ConfigNode<?> node : section.nodes()) {
            JacksonConfigNode.write(generator, node);
        }

        for (ConfigSection nested : section.sections()) {
            write(generator, nested);
        }

        generator.writeEndObject();
    }

    public static ConfigSection read(@NonNull JsonParser parser, @NonNull ConfigSection section) {
        var nodes = new ArrayList<ConfigNode<?>>();
        var sections = new ArrayList<ConfigSection>();

        while (parser.nextToken() != JsonToken.END_OBJECT) {
            var key = parser.currentName();

            if (parser.nextToken() == JsonToken.START_OBJECT) {
                var nested = section.section(key)
                        .orElseThrow(() -> new IllegalStateException(String.format(
                                "The section with the key %s doesn't exist in the provided parent section",
                                key
                        )));
                sections.add(read(parser, nested));
            } else {
                var token = section.nodeTypeToken(key)
                        .orElseThrow(() -> new IllegalStateException(String.format(
                                "The TypeToken for node %s can't be found",
                                key
                        )));

                var node = section.node(token, key)
                        .orElseThrow(() -> new IllegalStateException(String.format(
                                "The node with the key %s doesn't exist in the provided parent section",
                                key
                        )));

                nodes.add(JacksonConfigNode.read(parser, node));
            }
        }

        try (var mut = section.mutable()) {
            mut.setSections(sections);
            mut.setNodes(nodes);
            return section;
        }
    }
}
