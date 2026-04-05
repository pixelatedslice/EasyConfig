package com.pixelatedslice.easyconfig.api.config.section;

import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.ServiceLoader;

public interface WithNestedConfigSection {
    default @NonNull Optional<@NonNull ConfigSection> section(@NonNull String @NonNull ... providedKeys) {
        return ConfigSectionIterator.findSection(this.sections(), providedKeys);
    }

    @NonNull Collection<@NonNull ConfigSection> sections();

    default @NonNull Optional<@NonNull ConfigSection> nestedSectionButInTheBukkitAPIStyle(@NonNull String key) {
        return ConfigSectionIterator.findSectionButInTheBukkitAPIStyle(this.sections(), key);
    }

    @NonNull WithNestedConfigSection addSection(@NonNull ConfigSection section);

    @NonNull WithNestedConfigSection removeSection(@NonNull String key);

    default @NonNull ConfigSectionIterator sectionIterator() {
        return ServiceLoader.load(ConfigSectionIterator.class).findFirst().orElseThrow();
    }
}