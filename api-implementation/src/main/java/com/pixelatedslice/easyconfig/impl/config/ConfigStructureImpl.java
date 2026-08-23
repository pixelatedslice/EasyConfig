package com.pixelatedslice.easyconfig.impl.config;

import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
public record ConfigStructureImpl(Node root,
                                  SerializerRegistry serializers) implements ConfigStructure {
    @SuppressWarnings("unchecked")
    public ConfigStructureImpl(Node root, SerializerRegistry serializers) {
        Objects.requireNonNull(root);
        Objects.requireNonNull(serializers);
        var builder = (InternalNodeBuilder<? extends InternalNodeBuilder<?>>) root.toBuilder();
        this.root = builder.config(this).build();
        this.serializers = serializers;
    }

    public BuiltConfigImpl toBuilt() {
        return new BuiltConfigImpl(root, serializers);
    }
}
