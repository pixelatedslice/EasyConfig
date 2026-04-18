package com.pixelatedslice.easyconfig.impl;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.CopiedEasyConfig;
import com.pixelatedslice.easyconfig.api.fileformat.FileFormat;
import com.pixelatedslice.easyconfig.api.fileformat.FileFormatProvider;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import org.jspecify.annotations.NonNull;

import java.util.Map;

public final class CopiedEasyConfigImpl extends EasyConfigImpl implements CopiedEasyConfig {

    CopiedEasyConfigImpl(
            @NonNull Map<@NonNull Class<? extends FileFormat>, @NonNull FileFormatProvider<?>> providers,
            @NonNull Map<@NonNull TypeToken<?>, @NonNull Serializer<?>> serializers) {
        super(providers, serializers);
    }

    @Override
    public @NonNull CopiedEasyConfig registerSerializers(@NonNull Serializer<?> @NonNull ... serializers) {
        return (CopiedEasyConfig) super.registerSerializers(serializers);
    }

    @Override
    public @NonNull CopiedEasyConfig unregisterSerializers(@NonNull TypeToken<?> @NonNull ... typeTokens) {
        return (CopiedEasyConfig) super.unregisterSerializers(typeTokens);
    }

    @Override
    public @NonNull CopiedEasyConfig registerProviders(@NonNull FileFormatProvider<?> @NonNull ... providers) {
        return (CopiedEasyConfig) super.registerProviders(providers);
    }

    @Override
    public @NonNull CopiedEasyConfig unregisterProviders(@NonNull FileFormatProvider<?> @NonNull ... providers) {
        return (CopiedEasyConfig) super.unregisterProviders(providers);
    }
}
