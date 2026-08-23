package com.pixelatedslice.easyconfig.api.config.node.factory.builder;

import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@NullMarked
public interface NodeBuilderKeySteps {
    @NullMarked
    interface Value<T> extends NodeBuilderKeySteps,
            NodeBuilder.KeyStep<NodeBuilderValueStep<T>> {
    }

    @NullMarked
    interface Env<T>
            extends NodeBuilderKeySteps, NodeBuilder.KeyStep<NodeBuilderEnvStep.VariableStep<T>> {
    }

    @NullMarked
    interface Container
            extends NodeBuilderKeySteps, NodeBuilder.KeyStep<NodeBuilderGroupStep.Container> {
    }

    @NullMarked
    interface Collection
            extends NodeBuilderKeySteps, NodeBuilder.KeyStep<NodeBuilderGroupStep.Collection> {
    }
}
