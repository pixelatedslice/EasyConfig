package com.pixelatedslice.easyconfig.impl.config.node.factory;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.factory.FactoryNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public abstract class AbstractFactoryNodeBuilder<NextKeyStep extends FactoryNodeBuilder,
        NextParentStep extends FactoryNodeBuilder>
        implements FactoryNodeBuilder, FactoryNodeBuilder.KeyStep<NextKeyStep>,
        FactoryNodeBuilder.ParentStep<NextParentStep>, FactoryNodeBuilder.BuildStep<Node> {
    private final NextKeyStep nextKeyStep;
    private final NextParentStep nextParentStep;

    protected @Nullable String key;
    protected @Nullable Node parent;

    protected AbstractFactoryNodeBuilder(NextKeyStep nextKeyStep, NextParentStep nextParentStep) {
        this.nextKeyStep = nextKeyStep;
        this.nextParentStep = nextParentStep;
    }

    @Override
    public NextKeyStep key(String key) {
        this.key = key;
        return this.nextKeyStep;
    }

    @Override
    public NextParentStep parent(Node parent) {
        this.parent = parent;
        return this.nextParentStep;
    }
}
