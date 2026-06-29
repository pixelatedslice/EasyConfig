package com.pixelatedslice.easyconfig.api.config;

import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@NullMarked
public interface BuiltConfig extends Config {
    SerializerRegistry serializers();
}
