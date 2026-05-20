package com.pixelatedslice.easyconfig.api.config;

import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import org.jspecify.annotations.NonNull;

public interface TrueConfig extends Config {
    @NonNull SerializerRegistry serializers();
}
