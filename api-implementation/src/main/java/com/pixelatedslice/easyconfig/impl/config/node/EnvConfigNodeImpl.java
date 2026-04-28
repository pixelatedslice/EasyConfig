package com.pixelatedslice.easyconfig.impl.config.node;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.EnvConfigNode;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class EnvConfigNodeImpl<T> extends ConfigNodeImpl<T> implements EnvConfigNode<T> {
    private final String envKey;

    public EnvConfigNodeImpl(@NonNull String key,
            @NonNull String envKey,
            @NonNull TypeToken<T> typeToken,
            @NonNull ConfigSection parent,
            @NonNull List<@NonNull String> comments) {
        super(key, typeToken, null, null, parent, comments);
        this.envKey = envKey;
    }

    @Override
    public String envKey() {
        return this.envKey;
    }
}