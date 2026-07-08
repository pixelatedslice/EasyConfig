package com.pixelatedslice.easyconfig.impl.config.node.container.builder;

import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.NodeBuilderGroupStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.NodeBuilderHandlers;
import com.pixelatedslice.easyconfig.api.validator.null_policy.NullPolicy;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.container.ContainerNodeImpl;
import com.pixelatedslice.easyconfig.impl.config.node.factory.AbstractGroupNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

@NullMarked
public class ContainerNodeBuilder extends AbstractGroupNodeBuilder
        implements NodeBuilderHandlers.Container,
        InternalNodeBuilder<ContainerNodeBuilder> {

    @Nullable AbstractNode parent;
    @Nullable Config config;
    private String key;

    public ContainerNodeBuilder(String key) {
        this.key = Objects.requireNonNull(key);
    }

    @Override
    public ContainerNodeBuilder parent(@Nullable AbstractNode node) {
        this.parent = node;
        return this;
    }

    @Override
    public @Nullable AbstractNode parent() {
        return this.parent;
    }

    @Override
    public ContainerNodeBuilder config(@Nullable Config config) {
        this.config = config;
        return this;
    }

    @Override
    public @Nullable Config config() {
        return this.config;
    }

    public @Nullable String key() {
        return this.key;
    }

    @Override
    public java.util.Collection<InternalNodeBuilder<?>> children() {
        return Collections.unmodifiableCollection(this.children);
    }

    @Override
    public void appendChild(InternalNodeBuilder<?> builder) {
        this.children.add(builder);
    }

    @Override
    public ContainerNodeImpl build() {
        final var built = new ContainerNodeImpl(this);
        this.buildChildren(built);
        return built;
    }

    @Override
    public NodeBuilderGroupStep.Container children(NullPolicy nullPolicy,
            @Nullable BuildStep<?> @Nullable ... nodes) {
        if (nodes == null) {
            return this;
        }
        this.internalChildren(nullPolicy, Arrays.stream(nodes));
        return this;
    }

    @Override
    public NodeBuilderGroupStep.Container children(NullPolicy nullPolicy,
            java.util.@Nullable Collection<? extends @Nullable BuildStep<?>> nodes) {
        if (nodes == null) {
            return this;
        }
        this.internalChildren(nullPolicy, nodes.stream());
        return this;
    }

    @Override
    public NodeBuilderGroupStep.Container builtChildren(NullPolicy nullPolicy,
            @Nullable Node @Nullable ... nodes) {
        if (nodes == null) {
            return this;
        }
        this.internalBuiltChildren(nullPolicy, Arrays.stream(nodes));
        return this;
    }

    @Override
    public NodeBuilderGroupStep.Container builtChildren(NullPolicy nullPolicy,
            java.util.@Nullable Collection<? extends Node> nodes) {
        if (nodes == null) {
            return this;
        }
        this.internalBuiltChildren(nullPolicy, nodes.stream());
        return this;
    }

    @Override
    public NodeBuilderGroupStep.Container key(String key) {
        this.key = key;
        return this;
    }
}
