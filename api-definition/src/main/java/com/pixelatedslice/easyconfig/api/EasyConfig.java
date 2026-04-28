package com.pixelatedslice.easyconfig.api;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.exception.ModificationOfNonCopiedEasyConfigInstanceException;
import com.pixelatedslice.easyconfig.api.format.FileFormatProvider;
import com.pixelatedslice.easyconfig.api.format.Format;
import com.pixelatedslice.easyconfig.api.format.builtin.JsonFormat;
import com.pixelatedslice.easyconfig.api.format.builtin.TomlFormat;
import com.pixelatedslice.easyconfig.api.format.builtin.YamlFormat;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unused")
public interface EasyConfig {
    static EasyConfig instance() {
        return EasyConfigProvider.INSTANCE;
    }

    @NonNull CopiedEasyConfig copy();

    @NonNull Map<@NonNull TypeToken<?>, @NonNull Serializer<?>> serializers();

    <T> @NonNull Optional<@NonNull Serializer<T>> serializer(@NonNull TypeToken<T> clazz);

    void registerSerializers(@NonNull Serializer<?> @NonNull ... serializers)
            throws ModificationOfNonCopiedEasyConfigInstanceException;

    void unregisterSerializers(@NonNull TypeToken<?> @NonNull ... classes)
            throws ModificationOfNonCopiedEasyConfigInstanceException;

    @NonNull Map<@NonNull Class<? extends Format>, @NonNull FileFormatProvider<?>> providers();

    <T extends Format> @NonNull Optional<@NonNull FileFormatProvider<T>> provider(
            @NonNull Class<T> formatClass
    );

    void registerProviders(@NonNull FileFormatProvider<?> @NonNull ... providers)
            throws ModificationOfNonCopiedEasyConfigInstanceException;

    void unregisterProviders(@NonNull FileFormatProvider<?> @NonNull ... providers)
            throws ModificationOfNonCopiedEasyConfigInstanceException;

    @NonNull CommonFormatProviders commonFormatProviders();

    interface CommonFormatProviders {
        @NonNull Optional<@NonNull FileFormatProvider<JsonFormat>> json();

        @NonNull Optional<@NonNull FileFormatProvider<TomlFormat>> toml();

        @NonNull Optional<@NonNull FileFormatProvider<YamlFormat>> yaml();
    }
}