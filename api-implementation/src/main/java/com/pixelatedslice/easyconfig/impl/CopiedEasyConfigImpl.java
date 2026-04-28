package com.pixelatedslice.easyconfig.impl;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.CopiedEasyConfig;
import com.pixelatedslice.easyconfig.api.format.FileFormatProvider;
import com.pixelatedslice.easyconfig.api.format.Format;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import org.jspecify.annotations.NonNull;

import java.util.Map;

public final class CopiedEasyConfigImpl extends EasyConfigImpl implements CopiedEasyConfig {
    CopiedEasyConfigImpl(
            @NonNull Map<@NonNull Class<? extends Format>, @NonNull FileFormatProvider<?>> providers,
            @NonNull Map<@NonNull TypeToken<?>, @NonNull Serializer<?>> serializers) {
        super(providers, serializers);
    }

    @Override
    public void registerSerializers(@NonNull Serializer<?> @NonNull ... serializers) {
        super.registerSerializers(serializers);
    }

    @Override
    public void unregisterSerializers(@NonNull TypeToken<?> @NonNull ... classes) {
        super.unregisterSerializers(classes);
    }

    @Override
    public void registerProviders(@NonNull FileFormatProvider<?> @NonNull ... providers) {
        super.registerProviders(providers);
    }

    @Override
    public void unregisterProviders(@NonNull FileFormatProvider<?> @NonNull ... providers) {
        super.unregisterProviders(providers);
    }
}
