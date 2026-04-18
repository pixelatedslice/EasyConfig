package com.pixelatedslice.easyconfig.api;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.exception.ModificationOfNonCopiedEasyConfigInstanceException;
import com.pixelatedslice.easyconfig.api.fileformat.FileFormat;
import com.pixelatedslice.easyconfig.api.fileformat.FileFormatProvider;
import com.pixelatedslice.easyconfig.api.fileformat.builtin.JsonFileFormat;
import com.pixelatedslice.easyconfig.api.fileformat.builtin.TomlFileFormat;
import com.pixelatedslice.easyconfig.api.fileformat.builtin.YamlFileFormat;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import org.jspecify.annotations.NonNull;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ServiceLoader;

@SuppressWarnings("unused")
public interface EasyConfig {
    static EasyConfig instance() {
        return ServiceLoader.load(EasyConfig.class)
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("No implementation for EasyConfig found on the classpath.")
                );
    }

    @NonNull CopiedEasyConfig copy();

    @NonNull Map<@NonNull TypeToken<?>, @NonNull Serializer<?>> serializers();

    <T> @NonNull Optional<@NonNull Serializer<T>> serializer(@NonNull TypeToken<T> clazz);

    void registerSerializers(@NonNull Serializer<?> @NonNull ... serializers)
            throws ModificationOfNonCopiedEasyConfigInstanceException;

    void unregisterSerializers(@NonNull TypeToken<?> @NonNull ... classes)
            throws ModificationOfNonCopiedEasyConfigInstanceException;

    @NonNull Map<@NonNull Class<? extends FileFormat>, @NonNull FileFormatProvider<?>> providers();

    <T extends FileFormat> @NonNull Optional<@NonNull FileFormatProvider<T>> provider(
            @NonNull Class<T> fileFormatClass
    );

    void registerProviders(@NonNull FileFormatProvider<?> @NonNull ... providers)
            throws ModificationOfNonCopiedEasyConfigInstanceException;

    void unregisterProviders(@NonNull FileFormatProvider<?> @NonNull ... providers)
            throws ModificationOfNonCopiedEasyConfigInstanceException;

    @NonNull CommonFormatProviders commonFormatProviders();

    interface CommonFormatProviders {
        @NonNull Optional<@NonNull FileFormatProvider<JsonFileFormat>> json();

        @NonNull Optional<@NonNull FileFormatProvider<TomlFileFormat>> toml();

        @NonNull Optional<@NonNull FileFormatProvider<YamlFileFormat>> yaml();
    }
}