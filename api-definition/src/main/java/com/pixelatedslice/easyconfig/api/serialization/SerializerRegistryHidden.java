package com.pixelatedslice.easyconfig.api.serialization;

import java.util.ServiceLoader;

class SerializerRegistryHidden {

    static final SerializerRegistry GLOBAL = ServiceLoader.load(SerializerRegistry.class).findFirst().orElseThrow(() -> new RuntimeException("Cannot find SerializerRegistry as a service"));
}
