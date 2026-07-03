package com.pixelatedslice.easyconfig.impl.config.node.collection.builder;

import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderGroupStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderHandlers;
import com.pixelatedslice.easyconfig.api.validator.null_policy.NullPolicy;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.collection.CollectionNodeImpl;
import com.pixelatedslice.easyconfig.impl.config.node.container.builder.ContainerNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.factory.AbstractGroupNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@NullMarked
public class CollectionNodeBuilder extends AbstractGroupNodeBuilder implements FactoryNodeBuilderHandlers.Collection,
        InternalNodeBuilder<CollectionNodeBuilder> {

    private String key;
    private @Nullable Config config;
    private @Nullable AbstractNode parent;

    public CollectionNodeBuilder(InternalNodeBuilder<?> from) {
        this.key = Objects.requireNonNull(from.key());
        this.parent = from.parent();
        this.config = from.config();
        from.children().forEach(this::appendChild);
    }

    public CollectionNodeBuilder(String key) {
        this.key = key;
    }

    @Override
    public CollectionNodeBuilder parent(@Nullable AbstractNode node) {
        this.parent = node;
        return this;
    }

    @Override
    public @Nullable AbstractNode parent() {
        return this.parent;
    }

    @Override
    public CollectionNodeBuilder config(@Nullable Config config) {
        this.config = config;
        return this;
    }

    @Override
    public @Nullable Config config() {
        return this.config;
    }

    @Override
    public @Nullable String key() {
        return this.key;
    }

    @Override
    public List<InternalNodeBuilder<?>> children() {
        return Collections.unmodifiableList(this.children);
    }

    @Override
    public void appendChild(InternalNodeBuilder<?> builder) {
        if (builder instanceof ContainerNodeBuilder containerBuilder) {
            this.children.add(containerBuilder);
            return;
        }
        if (builder instanceof CollectionNodeBuilder collectionBuilder) {
            this.children.add(collectionBuilder);
            return;
        }
        throw new IllegalArgumentException("Cannot append child of " + builder.getClass().getName());
    }

    @Override
    public FactoryNodeBuilderGroupStep.Collection children(NullPolicy nullPolicy,
            @Nullable BuildStep<?> @Nullable ... nodes) {
        if (nodes == null) {
            return this;
        }
        this.internalChildren(nullPolicy, Arrays.stream(nodes));
        return this;
    }

    @Override
    public FactoryNodeBuilderGroupStep.Collection children(NullPolicy nullPolicy,
            java.util.@Nullable Collection<? extends @Nullable BuildStep<?>> nodes) {
        if (nodes == null) {
            return this;
        }
        this.internalChildren(nullPolicy, nodes.stream());
        return this;
    }

    @Override
    public FactoryNodeBuilderGroupStep.Collection builtChildren(NullPolicy nullPolicy,
            @Nullable Node @Nullable ... nodes) {
        if (nodes == null) {
            return this;
        }
        this.internalBuiltChildren(nullPolicy, Arrays.stream(nodes));
        return this;
    }

    @Override
    public FactoryNodeBuilderGroupStep.Collection builtChildren(NullPolicy nullPolicy,
            java.util.@Nullable Collection<? extends Node> nodes) {
        if (nodes == null) {
            return this;
        }
        this.internalBuiltChildren(nullPolicy, nodes.stream());
        return this;
    }

    @Override
    public CollectionNodeImpl build() {
        final var result = new CollectionNodeImpl(this);
        this.buildChildren(result);
        return result;
    }

    @Override
    public FactoryNodeBuilderGroupStep.Collection key(String key) {
        this.key = key;
        return this;
    }
}
