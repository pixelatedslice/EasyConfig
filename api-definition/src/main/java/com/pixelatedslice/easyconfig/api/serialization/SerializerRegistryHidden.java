package com.pixelatedslice.easyconfig.api.serialization;

import org.jspecify.annotations.NullMarked;


import java.util.ServiceLoader;

@NullMarked
final class SerializerRegistryHidden {

    static final SerializerRegistry GLOBAL = ServiceLoader
            .load(SerializerRegistry.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Cannot find SerializerRegistry as a service"));

    private SerializerRegistryHidden() {
    }
}
