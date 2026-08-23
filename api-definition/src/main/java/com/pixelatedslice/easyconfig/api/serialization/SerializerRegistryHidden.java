package com.pixelatedslice.easyconfig.api.serialization;

import org.jspecify.annotations.NonNull;

import java.util.ServiceLoader;

final class SerializerRegistryHidden {

    private static SerializerRegistry GLOBAL = null;

    private SerializerRegistryHidden(){

    }

    static SerializerRegistry global() {
        if (GLOBAL != null) {
            return GLOBAL;
        }
        setGlobal(serviceLoader());
        return GLOBAL;
    }

    @Deprecated
    static void setGlobalForUnitTesting(SerializerRegistry mocked) {
        GLOBAL = mocked;
    }

    private static synchronized SerializerRegistry serviceLoader() {
        return ServiceLoader.load(SerializerRegistry.class).findFirst().orElseThrow(() -> new RuntimeException("Cannot find SerializerRegistry as a service"));
    }

    private static synchronized void setGlobal(@NonNull SerializerRegistry registry) {
        if (GLOBAL != null) {
            return;
        }
        GLOBAL = registry;
    }
}
