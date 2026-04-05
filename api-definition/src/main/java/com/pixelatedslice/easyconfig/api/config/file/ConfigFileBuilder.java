package com.pixelatedslice.easyconfig.api.config.file;

import com.pixelatedslice.easyconfig.api.config.node.BuilderWithConfigNodes;
import com.pixelatedslice.easyconfig.api.config.section.BuilderWithConfigSections;
import org.jspecify.annotations.NonNull;

import java.nio.file.Path;

public interface ConfigFileBuilder extends BuilderWithConfigNodes, BuilderWithConfigSections {
    @NonNull ConfigFileBuilder filePath(@NonNull Path filePathWithoutExtension);

    @NonNull ConfigFile build();
}