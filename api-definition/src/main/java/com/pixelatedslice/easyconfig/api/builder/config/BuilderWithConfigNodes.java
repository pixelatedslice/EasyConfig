package com.pixelatedslice.easyconfig.api.builder.config;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.ConfigNodeBuilder;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public interface BuilderWithConfigNodes {
    <T> @NonNull BuilderWithConfigNodes node(@NonNull String key, @NonNull TypeToken<T> typeToken,
            @NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull String key, @NonNull Class<T> simpleType,
            @NonNull Consumer<ConfigNodeBuilder<T>> nodeBuilder);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull String key, @NonNull TypeToken<T> typeToken);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull String key, @NonNull Class<T> simpleType);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull String key, @Nullable T value, @NonNull TypeToken<T> typeToken);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull String key, @Nullable T value, @NonNull Class<T> simpleType);

    <T> @NonNull BuilderWithConfigNodes env(@NonNull String key, @NonNull String envKey,
            @NonNull TypeToken<T> typeToken);

    /**
     * @param key       The key in the file and of the ENV variable
     * @param typeToken The type
     */
    <T> @NonNull BuilderWithConfigNodes env(@NonNull String key, @NonNull TypeToken<T> typeToken);

    <T> @NonNull BuilderWithConfigNodes env(@NonNull String key, @NonNull String envKey,
            @NonNull Class<T> simpleType);

    /**
     * @param key        The key in the file and of the ENV variable
     * @param simpleType The type
     */
    <T> @NonNull BuilderWithConfigNodes env(@NonNull String key, @NonNull Class<T> simpleType);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull String key, @NonNull T valueWithSimpleType);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull String key,
            @NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull TypeToken<T> typeToken,
            @NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull String key, @NonNull TypeToken<T> typeToken,
            Validator<T> validator);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull String key, @NonNull Class<T> simpleType,
            Validator<T> validator);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull String key, @Nullable T value, @NonNull TypeToken<T> typeToken,
            Validator<T> validator);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull String key, @Nullable T value, @NonNull Class<T> simpleType,
            Validator<T> validator);

    <T> @NonNull BuilderWithConfigNodes env(@NonNull String key, @NonNull String envKey,
            @NonNull TypeToken<T> typeToken, Validator<T> validator);

    /**
     * @param key       The key in the file and of the ENV variable
     * @param typeToken The type
     */
    <T> @NonNull BuilderWithConfigNodes env(@NonNull String key, @NonNull TypeToken<T> typeToken,
            Validator<T> validator);

    <T> @NonNull BuilderWithConfigNodes env(@NonNull String key, @NonNull String envKey,
            @NonNull Class<T> simpleType, Validator<T> validator);

    /**
     * @param key        The key in the file and of the ENV variable
     * @param simpleType The type
     */
    <T> @NonNull BuilderWithConfigNodes env(@NonNull String key, @NonNull Class<T> simpleType, Validator<T> validator);

    <T> @NonNull BuilderWithConfigNodes node(@NonNull String key, @NonNull T valueWithSimpleType,
            Validator<T> validator);
}
