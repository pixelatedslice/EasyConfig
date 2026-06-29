package com.pixelatedslice.easyconfig.impl.config;

import com.pixelatedslice.easyconfig.api.config.BuiltConfig;
import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
public class BuiltConfigImpl implements BuiltConfig {

    private final SerializerRegistry serializers;
    private final Node root;

    public BuiltConfigImpl(Node node) {
        this(node, SerializerRegistry.global());
    }

    public BuiltConfigImpl(Node node, SerializerRegistry serializers) {
        this.root = Objects.requireNonNull(node);
        this.serializers = Objects.requireNonNull(serializers).createChild();
    }


    @Override
    public Node root() {
        return this.root;
    }

    @Override
    public SerializerRegistry serializers() {
        return this.serializers;
    }
}
