package com.pixelatedslice.easyconfig.api.format;

import org.jspecify.annotations.NullMarked;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

@SuppressWarnings({"unused", "UtilityClassWithoutPrivateConstructor", "NonFinalUtilityClass"})
@NullMarked
public class Formats {
    public static final Collection<Format> FORMATS = ServiceLoader
            .load(Format.class)
            .stream()
            .map(ServiceLoader.Provider::get)
            .collect(Collectors.toUnmodifiableSet());
}
