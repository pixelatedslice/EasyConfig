package com.pixelatedslice.easyconfig.api.format;

import org.jspecify.annotations.NullMarked;


import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

@NullMarked
public class Formats {
    public static final Collection<Format> FORMATS = ServiceLoader
            .load(Format.class)
            .stream()
            .map(ServiceLoader.Provider::get)
            .collect(Collectors.toUnmodifiableSet());

    public static Optional<Format> fromExtension(String extension){
        return FORMATS.stream().filter(t -> Arrays.asList(t.fileExtensions()).contains(extension)).findFirst();
    }
}
