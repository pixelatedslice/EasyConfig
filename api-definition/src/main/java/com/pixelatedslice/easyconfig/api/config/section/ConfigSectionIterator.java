package com.pixelatedslice.easyconfig.api.config.section;

import org.jspecify.annotations.NonNull;

import java.util.*;

public interface ConfigSectionIterator extends Iterator<ConfigSection> {
    static @NonNull Optional<@NonNull ConfigSection> findSection(
            @NonNull Collection<? extends @NonNull ConfigSection> nestedSections,
            @NonNull String... providedKeys
    ) {
        Objects.requireNonNull(nestedSections);
        Objects.requireNonNull(providedKeys);
        if (providedKeys.length == 0) {
            return Optional.empty();
        }
        List<String> keys = List.of(providedKeys);
        var currentNestedSections = nestedSections;
        int last = keys.size() - 1;
        for (int i = 0; i <= last; i++) {
            String wanted = keys.get(i);
            ConfigSection next = null;
            for (ConfigSection section : currentNestedSections) {
                if (section.key().equals(wanted)) {
                    next = section;
                    break;
                }
            }
            if (next == null) {
                return Optional.empty();
            }
            if (i == last) {
                return Optional.of(next);
            }
            currentNestedSections = next.sections();
        }
        return Optional.empty();
    }
}
