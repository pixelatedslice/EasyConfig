package com.pixelatedslice.easyconfig.impl.fileformat.common;

import com.pixelatedslice.easyconfig.api.config.file.ConfigFile;
import com.pixelatedslice.easyconfig.api.config.node.ConfigNode;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import org.jspecify.annotations.NonNull;
import tools.jackson.core.JsonGenerator;
import tools.jackson.core.JsonParser;

import java.util.ArrayList;

public final class JacksonConfigFile {

    private JacksonConfigFile() {
    }

    public static void write(@NonNull JsonGenerator generator, @NonNull ConfigFile file) {
        for (ConfigNode<?> node : file.rootSection().nodes()) {
            JacksonConfigNode.write(generator, node);
        }
        for (ConfigSection section : file.rootSection().sections()) {
            JacksonConfigSection.write(generator, section);
        }
    }

    public static void read(@NonNull JsonParser parser, @NonNull ConfigFile file) {
        var root = file.rootSection();
        var nodes = new ArrayList<ConfigNode<?>>();
        var sections = new ArrayList<ConfigSection>();

        for (ConfigNode<?> node : root.nodes()) {
            nodes.add(JacksonConfigNode.read(parser, node));
        }

        for (ConfigSection section : root.sections()) {
            sections.add(JacksonConfigSection.read(parser, section));
        }

        try (var mut = root.mutable()) {
            mut.setNodes(nodes);
            mut.setSections(sections);
        }
    }
}
