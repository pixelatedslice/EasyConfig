package com.pixelatedslice.easyconfig.impl.config.node;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.ConfigNode;
import com.pixelatedslice.easyconfig.api.config.node.EnvConfigNodeBuilder;
import org.jspecify.annotations.NonNull;

public class EnvConfigNodeBuilderImpl<T> extends ConfigNodeBuilderImpl<T> implements EnvConfigNodeBuilder<T> {
    private final String envKey;

    public EnvConfigNodeBuilderImpl(String key, String envKey, TypeToken<T> typeToken) {
        this.envKey = envKey;
        super(key, typeToken, null, null, null);
    }

    @Override
    public @NonNull ConfigNode<T> build() {
        return new EnvConfigNodeImpl<>(
                this.key,
                this.envKey,
                this.typeToken,
                this.parent,
                this.comments
        );
    }
}