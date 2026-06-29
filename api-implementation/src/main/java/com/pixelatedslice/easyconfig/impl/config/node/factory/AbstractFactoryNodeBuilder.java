package com.pixelatedslice.easyconfig.impl.config.node.factory;

import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("unchecked")
@NullMarked
public abstract class AbstractFactoryNodeBuilder<NodeType extends Node, NextKeyStep extends FactoryNodeBuilder,
        NextParentStep extends FactoryNodeBuilder>
        implements FactoryNodeBuilder, FactoryNodeBuilder.KeyStep<NextKeyStep>,
        FactoryNodeBuilder.ParentStep<NextParentStep>, FactoryNodeBuilder.BuildStep<NodeType> {
    protected @Nullable String key;
    protected @Nullable Node parent;

    @Override
    public NextKeyStep key(String key) {
        this.key = key;
        return (NextKeyStep) this;
    }

    @Override
    public NextParentStep parent(@Nullable Node parent) {
        this.parent = parent;
        return (NextParentStep) this;
    }
}
