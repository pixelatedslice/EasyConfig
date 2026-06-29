package com.pixelatedslice.easyconfig.impl.config.node.container.builder;

import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.node.NodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

@NullMarked
public abstract class AbstractContainerNodeBuilder<Self extends AbstractContainerNodeBuilder<Self>>
        implements NodeBuilder.ContainerFinalStep, InternalNodeBuilder<Self> {

    @Nullable String key;
    @Nullable AbstractNode parent;
    @Nullable Config config;
    Collection<InternalNodeBuilder<?>> children = new CopyOnWriteArrayList<>();

    @Override
    public Collection<InternalNodeBuilder<?>> children() {
        return Collections.unmodifiableCollection(this.children);
    }

    @Override
    public void appendChild(InternalNodeBuilder<?> builder) {
        this.children.add(builder);
    }

    @Override
    public Self parent(@Nullable AbstractNode node) {
        this.parent = node;
        //noinspection unchecked
        return (Self) this;
    }

    @Override
    public @Nullable AbstractNode parent() {
        return this.parent;
    }

    @Override
    public Self config(@Nullable Config config) {
        this.config = config;
        //noinspection unchecked
        return (Self) this;
    }

    @Override
    public @Nullable Config config() {
        return this.config;
    }

    public @Nullable String key() {
        return this.key;
    }
}
