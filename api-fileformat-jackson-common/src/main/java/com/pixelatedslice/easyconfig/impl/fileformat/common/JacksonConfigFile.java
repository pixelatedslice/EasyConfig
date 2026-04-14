package com.pixelatedslice.easyconfig.impl.fileformat.common;

import com.pixelatedslice.easyconfig.api.config.file.ConfigFile;
import com.pixelatedslice.easyconfig.api.config.node.ConfigNode;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import org.jspecify.annotations.NonNull;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;

import java.util.ArrayList;

public final class JacksonConfigFile {
    private JacksonConfigFile() {
    }

    public static void write(
            @NonNull JsonGenerator generator,
            @NonNull ConfigFile file
    ) {
        generator.writeStartObject();


        for (ConfigNode<?> node : file.rootSection().nodes()) {
            JacksonConfigNode.write(generator, node);
        }
        for (ConfigSection section : file.rootSection().sections()) {
            JacksonConfigSection.write(generator, section);
        }

        generator.writeEndObject();
    }

    public static void read(@NonNull JsonParser parser, @NonNull ConfigFile file) {
        if (parser.currentToken() == null) {
            parser.nextToken();
        }

        var root = file.rootSection();
        var nodes = new ArrayList<ConfigNode<?>>();
        var sections = new ArrayList<ConfigSection>();

        if (parser.currentToken() == JsonToken.START_OBJECT) {
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                var key = parser.currentName();

                if (parser.nextToken() == JsonToken.START_OBJECT) {
                    var sectionOptional = root.section(key);
                    if (sectionOptional.isPresent()) {
                        sections.add(JacksonConfigSection.read(parser, sectionOptional.get()));
                    } else {
                        parser.skipChildren();
                    }
                } else {
                    var tokenOptional = root.nodeTypeToken(key);
                    if (tokenOptional.isPresent()) {
                        var nodeOptional = root.node(tokenOptional.get(), key);
                        nodeOptional.ifPresent(configNode -> nodes.add(JacksonConfigNode.read(parser, configNode)));
                    } else {
                        parser.nextToken();
                        parser.skipChildren();
                    }
                }
            }
        }

        try (var mut = root.mutable()) {
            mut.setNodes(nodes);
            mut.setSections(sections);
        }
    }
}
