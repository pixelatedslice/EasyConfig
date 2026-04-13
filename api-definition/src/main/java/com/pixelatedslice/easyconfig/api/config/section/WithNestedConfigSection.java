package com.pixelatedslice.easyconfig.api.config.section;

import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.ServiceLoader;

@FunctionalInterface
public interface WithNestedConfigSection {
    default @NonNull Optional<@NonNull ConfigSection> section(@NonNull String @NonNull ... providedKeys) {
        Objects.requireNonNull(providedKeys);
        return (providedKeys.length == 0)
                ? Optional.empty()
                : ConfigSectionIterator.findSection(this.sections(), providedKeys);
    }

    @NonNull Collection<@NonNull ConfigSection> sections();

    @NonNull
    default ConfigSectionIterator sectionIterator() {
        return ServiceLoader.load(ConfigSectionIterator.class).findFirst().orElseThrow();
    }

}