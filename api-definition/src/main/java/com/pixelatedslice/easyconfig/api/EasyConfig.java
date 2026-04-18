package com.pixelatedslice.easyconfig.api;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.fileformat.FileFormat;
import com.pixelatedslice.easyconfig.api.fileformat.FileFormatProvider;
import com.pixelatedslice.easyconfig.api.fileformat.builtin.JsonFileFormat;
import com.pixelatedslice.easyconfig.api.fileformat.builtin.TomlFileFormat;
import com.pixelatedslice.easyconfig.api.fileformat.builtin.YamlFileFormat;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unused")
public interface EasyConfig {
    @NonNull CopiedEasyConfig copy();

    @NonNull Map<@NonNull TypeToken<?>, @NonNull Serializer<?>> serializers();

    <T> @NonNull Optional<@NonNull Serializer<T>> serializer(@NonNull TypeToken<T> clazz);

    @NonNull EasyConfig registerSerializers(@NonNull Serializer<?> @NonNull ... serializers);

    @NonNull EasyConfig unregisterSerializers(@NonNull TypeToken<?> @NonNull ... classes);

    @NonNull Map<@NonNull Class<? extends FileFormat>, @NonNull FileFormatProvider<?>> providers();

    <T extends FileFormat> @NonNull Optional<@NonNull FileFormatProvider<T>> provider(
            @NonNull Class<T> fileFormatClass
    );

    EasyConfig registerProviders(@NonNull FileFormatProvider<?> @NonNull ... providers);

    EasyConfig unregisterProviders(@NonNull FileFormatProvider<?> @NonNull ... providers);

    @NonNull CommonFormatProviders commonFormatProviders();

    interface CommonFormatProviders {
        @NonNull Optional<@NonNull FileFormatProvider<JsonFileFormat>> json();

        @NonNull Optional<@NonNull FileFormatProvider<TomlFileFormat>> toml();

        @NonNull Optional<@NonNull FileFormatProvider<YamlFileFormat>> yaml();
    }
}