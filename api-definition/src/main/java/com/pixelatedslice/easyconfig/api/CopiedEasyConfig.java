package com.pixelatedslice.easyconfig.api;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.fileformat.FileFormatProvider;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import org.jspecify.annotations.NonNull;

public interface CopiedEasyConfig extends EasyConfig {
    @Override
    @NonNull CopiedEasyConfig registerSerializers(@NonNull Serializer<?> @NonNull ... serializers);

    @Override
    @NonNull CopiedEasyConfig unregisterSerializers(@NonNull TypeToken<?> @NonNull ... classes);

    @Override
    @NonNull CopiedEasyConfig registerProviders(@NonNull FileFormatProvider<?> @NonNull ... providers);

    @Override
    @NonNull CopiedEasyConfig unregisterProviders(@NonNull FileFormatProvider<?> @NonNull ... providers);
}
