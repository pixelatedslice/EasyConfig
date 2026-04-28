package com.pixelatedslice.easyconfig.api.config.node;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.comments.BuilderWithComments;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import com.pixelatedslice.easyconfig.api.validator.ValidatorContext;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.BiConsumer;

public interface ConfigNodeBuilder<T> extends BuilderWithComments {
    @Override
    @NonNull ConfigNodeBuilder<T> comments(@NonNull String @NonNull ... comment);

    @Override
    @NonNull ConfigNodeBuilder<T> addComment(@NonNull String comment);

    @NonNull ConfigNodeBuilder<T> key(@NonNull String key);

    @NonNull ConfigNodeBuilder<T> value(@Nullable T value);

    @NonNull ConfigNodeBuilder<T> defaultValue(@Nullable T defaultValue);

    @NonNull ConfigNodeBuilder<T> validator(@NonNull BiConsumer<? super T, ? super ValidatorContext> validator);

    @NonNull ConfigNodeBuilder<T> typeToken(@NonNull TypeToken<T> typeToken);

    @NonNull ConfigNodeBuilder<T> parent(@NonNull ConfigSection parent);

    @NonNull ConfigNode<T> build();
}