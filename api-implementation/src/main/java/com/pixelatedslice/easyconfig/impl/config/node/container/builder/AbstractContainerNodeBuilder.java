package com.pixelatedslice.easyconfig.impl.config.node.container.builder;

import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderGroupStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.container.ContainerNodeImpl;
import com.pixelatedslice.easyconfig.impl.config.node.factory.AbstractFactoryNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NullMarked
public class AbstractContainerNodeBuilder
        extends
        AbstractFactoryNodeBuilder<ContainerNode, FactoryNodeBuilderKeySteps.Container,
                FactoryNodeBuilderGroupStep.Container>
        implements FactoryNodeBuilderGroupStep.Container, FactoryNodeBuilderGroupStep.Container.Buildable,
        InternalNodeBuilder<AbstractContainerNodeBuilder> {

    @Nullable String key;
    @Nullable AbstractNode parent;
    @Nullable Config config;
    java.util.Collection<InternalNodeBuilder<?>> children = new CopyOnWriteArrayList<>();

    @Override
    public AbstractContainerNodeBuilder parent(@Nullable AbstractNode node) {
        this.parent = node;
        //noinspection unchecked
        return this;
    }

    @Override
    public @Nullable AbstractNode parent() {
        return this.parent;
    }

    @Override
    public AbstractContainerNodeBuilder config(@Nullable Config config) {
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
        return new ContainerNodeImpl(this);
    }

    @Override
    public Buildable children(@Nullable BuildStep<?> @Nullable ... nodes) {
        return (nodes != null) ? this.children(Arrays.stream(nodes)) : this;
    }

    @Override
    public Buildable children(java.util.@Nullable Collection<? extends @Nullable BuildStep<?>> nodes) {
        return (nodes != null) ? this.children(nodes.stream()) : this;
    }

    private Buildable children(Stream<? extends @Nullable BuildStep<?>> nodeStream) {
        final var internalBuilders = nodeStream
                .filter((@Nullable BuildStep<?> buildStep) -> buildStep != null)
                .map(BuildStep::build)
                .map(node -> (AbstractNode) node)
                .map(AbstractNode::toBuilder)
                .collect(Collectors.toSet());

        this.children.addAll(internalBuilders);

        return this;
    }
}
