package com.pixelatedslice.easyconfig.impl.config.node.container.builder;

import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderGroupStep;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.builder.GroupNodeBuilderHelper;
import com.pixelatedslice.easyconfig.impl.config.node.container.ContainerNodeImpl;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

@NullMarked
public class ContainerNodeBuilder
        implements FactoryNodeBuilderKeySteps.Container, FactoryNodeBuilderGroupStep.Container,
        FactoryNodeBuilderGroupStep.Container.Buildable,
        InternalNodeBuilder<ContainerNodeBuilder>, FactoryNodeBuilder.BuildStep<ContainerNode> {

    @Nullable AbstractNode parent;
    @Nullable Config config;
    java.util.Collection<InternalNodeBuilder<?>> children = new CopyOnWriteArrayList<>();
    private String key;

    public ContainerNodeBuilder(String key) {
        this.key = key;
    }

    @Override
    public ContainerNodeBuilder parent(@Nullable AbstractNode node) {
        this.parent = node;
        //noinspection unchecked
        return this;
    }

    @Override
    public @Nullable AbstractNode parent() {
        return this.parent;
    }

    @Override
    public ContainerNodeBuilder config(@Nullable Config config) {
        this.config = config;
        //noinspection unchecked
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
    public Buildable children(@Nullable BuildStep<?> @Nullable ... nodes) {
        if (nodes == null) {
            return this;
        }
        GroupNodeBuilderHelper.children(Arrays.stream(nodes), this.children);
        return this;
    }

    @Override
    public Buildable children(java.util.@Nullable Collection<? extends @Nullable BuildStep<?>> nodes) {
        if (nodes == null) {
            return this;
        }
        GroupNodeBuilderHelper.children(nodes.stream(), this.children);
        return this;
    }

    @Override
    public FactoryNodeBuilderGroupStep.Container key(String key) {
        this.key = key;
        return this;
    }
}
