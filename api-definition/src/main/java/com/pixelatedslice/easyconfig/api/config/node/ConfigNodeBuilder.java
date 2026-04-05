package com.pixelatedslice.easyconfig.api.config.node;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public interface ConfigNodeBuilder<T> {
    @NonNull ConfigNodeBuilder<T> comments(@NonNull String @NonNull ... comment);

    @NonNull ConfigNodeBuilder<T> key(@NonNull String key);

    @NonNull ConfigNodeBuilder<T> value(@Nullable T value);

    @NonNull ConfigNodeBuilder<T> defaultValue(@Nullable T defaultValue);

    @NonNull ConfigNodeBuilder<T> typeToken(@NonNull TypeToken<T> typeToken);

    @NonNull ConfigNodeBuilder<T> parent(@NonNull ConfigSection parent);

    @NonNull ConfigNode<T> build();
}