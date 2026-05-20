package com.pixelatedslice.easyconfig.api.format;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public final class Formats {

    public static final Collection<Format> FORMATS;

    static {
        FORMATS = ServiceLoader.load(Format.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toUnmodifiableSet());
    }


}
