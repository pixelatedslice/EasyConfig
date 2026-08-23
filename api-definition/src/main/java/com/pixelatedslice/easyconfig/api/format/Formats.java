package com.pixelatedslice.easyconfig.api.format;

import org.jspecify.annotations.NullMarked;


import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

@NullMarked
public final class Formats {
    public static final Collection<Format> FORMATS = ServiceLoader
            .load(Format.class)
            .stream()
            .map(ServiceLoader.Provider::get)
            .collect(Collectors.toUnmodifiableSet());

    private Formats() {
    }

    public static Optional<Format> fromExtension(String extension) {
        return FORMATS
                .stream()
                .filter((Format format) -> Arrays.asList(format.fileExtensions()).contains(extension))
                .findFirst();
    }

    @SuppressWarnings("ObjectEquality")
    public static <F extends Format> Optional<Format> get(Class<F> formatClass) {
        return FORMATS.stream().filter((Format format) -> format.getClass() == formatClass).findFirst();
    }
}
