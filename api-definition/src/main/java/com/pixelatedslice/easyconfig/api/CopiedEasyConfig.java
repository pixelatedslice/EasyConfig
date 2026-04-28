package com.pixelatedslice.easyconfig.api;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.format.FileFormatProvider;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import org.jspecify.annotations.NonNull;

public interface CopiedEasyConfig extends EasyConfig {
    @Override
    void registerSerializers(@NonNull Serializer<?> @NonNull ... serializers);

    @Override
    void unregisterSerializers(@NonNull TypeToken<?> @NonNull ... classes);

    @Override
    void registerProviders(@NonNull FileFormatProvider<?> @NonNull ... providers);

    @Override
    void unregisterProviders(@NonNull FileFormatProvider<?> @NonNull ... providers);
}
