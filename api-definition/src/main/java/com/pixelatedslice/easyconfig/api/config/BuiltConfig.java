package com.pixelatedslice.easyconfig.api.config;

import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.NonNull;

@NullMarked
public interface BuiltConfig extends Config {
    @NonNull SerializerRegistry serializers();
}
