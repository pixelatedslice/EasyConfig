package com.pixelatedslice.easyconfig.impl.config;

import com.pixelatedslice.easyconfig.api.config.TrueConfig;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public class TrueConfigImpl implements TrueConfig {

    private final @NonNull SerializerRegistry serializers;
    private final @NonNull Node root;

    public TrueConfigImpl(@NonNull Node node) {
        this(node, SerializerRegistry.global());
    }

    public TrueConfigImpl(@NonNull Node node, @NonNull SerializerRegistry serializers) {
        this.root = Objects.requireNonNull(node);
        this.serializers = Objects.requireNonNull(serializers).createChild();
    }


    @Override
    public @NonNull Node root() {
        return this.root;
    }

    @Override
    public @NonNull SerializerRegistry serializers() {
        return this.serializers;
    }
}
