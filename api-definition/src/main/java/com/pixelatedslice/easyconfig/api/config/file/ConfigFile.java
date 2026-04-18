package com.pixelatedslice.easyconfig.api.config.file;

import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import com.pixelatedslice.easyconfig.api.config.section.MutableConfigSection;
import org.jspecify.annotations.NonNull;

import java.nio.file.Path;
import java.util.ServiceLoader;

public interface ConfigFile {
    static @NonNull ConfigFileBuilder builder() {
        return ServiceLoader.load(ConfigFileBuilder.class).findFirst().orElseThrow();
    }

    @NonNull ConfigSection rootSection();

    @NonNull Path filePathWithoutExtension();

    default MutableConfigSection mutable() {
        return this.rootSection().mutable();
    }
}