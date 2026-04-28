package com.pixelatedslice.easyconfig.api.config.file;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.builder.config.BuilderWithConfigNodes;
import com.pixelatedslice.easyconfig.api.builder.config.BuilderWithConfigSections;
import com.pixelatedslice.easyconfig.api.config.node.ConfigNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSectionBuilder;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.nio.file.Path;
import java.util.function.Consumer;

public interface ConfigFileBuilder extends BuilderWithConfigNodes, BuilderWithConfigSections {
    @Override
    @NonNull <T> ConfigFileBuilder env(@NonNull String key, @NonNull String envKey,
            @NonNull TypeToken<T> typeToken, Validator<T> validator);

    @Override
    @NonNull <T> ConfigFileBuilder env(@NonNull String key, @NonNull TypeToken<T> typeToken,
            Validator<T> validator);

    @Override
    @NonNull <T> ConfigFileBuilder env(@NonNull String key, @NonNull String envKey, @NonNull Class<T> simpleType,
            Validator<T> validator);

    @Override
    @NonNull <T> ConfigFileBuilder env(@NonNull String key, @NonNull Class<T> simpleType, Validator<T> validator);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull String key, @NonNull T valueWithSimpleType,
            Validator<T> validator);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull String key, @NonNull TypeToken<T> typeToken,
            Validator<T> validator);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull String key, @NonNull Class<T> simpleType, Validator<T> validator);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull String key, @Nullable T value, @NonNull TypeToken<T> typeToken,
            Validator<T> validator);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull String key, @Nullable T value, @NonNull Class<T> simpleType,
            Validator<T> validator);

    @Override
    @NonNull <T> ConfigFileBuilder env(@NonNull String key, @NonNull String envKey,
            @NonNull TypeToken<T> typeToken);

    @Override
    @NonNull <T> ConfigFileBuilder env(@NonNull String key, @NonNull TypeToken<T> typeToken);

    @Override
    @NonNull <T> ConfigFileBuilder env(@NonNull String key, @NonNull String envKey, @NonNull Class<T> simpleType);

    @Override
    @NonNull <T> ConfigFileBuilder env(@NonNull String key, @NonNull Class<T> simpleType);

    @NonNull ConfigFileBuilder filePath(@NonNull Path filePathWithoutExtension);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull String key, @NonNull TypeToken<T> typeToken,
            @NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull String key, @NonNull Class<T> simpleType,
            @NonNull Consumer<ConfigNodeBuilder<T>> nodeBuilder);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull String key, @NonNull TypeToken<T> typeToken);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull String key, @NonNull Class<T> simpleType);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull String key, @Nullable T value, @NonNull TypeToken<T> typeToken);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull String key, @NonNull T valueWithSimpleType);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull String key,
            @NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull TypeToken<T> typeToken,
            @NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder);

    @Override
    @NonNull ConfigFileBuilder section(@NonNull String key,
            @NonNull Consumer<? super ConfigSectionBuilder> nestedSectionBuilder);

    @Override
    @NonNull ConfigFileBuilder section(@NonNull Consumer<? super ConfigSectionBuilder> nestedSectionBuilder);

    @Override
    @NonNull <T> ConfigFileBuilder node(@NonNull String key, @Nullable T value, @NonNull Class<T> simpleType);

    @NonNull ConfigFile build();
}