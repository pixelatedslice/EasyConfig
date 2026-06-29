package com.pixelatedslice.easyconfig.api.config.node.factory.builder;

import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@NullMarked
public interface FactoryNodeBuilderKeySteps {
    @NullMarked
    interface Value<T> extends FactoryNodeBuilderKeySteps,
            FactoryNodeBuilder.KeyStep<FactoryNodeBuilderValueStep.FirstStep<T>> {
    }

    @NullMarked
    interface Env<T>
            extends FactoryNodeBuilderKeySteps, FactoryNodeBuilder.KeyStep<FactoryNodeBuilderEnvStep.VariableStep<T>> {
    }

    @NullMarked
    interface Container
            extends FactoryNodeBuilderKeySteps, FactoryNodeBuilder.KeyStep<FactoryNodeBuilderGroupStep.Container> {
    }

    @NullMarked
    interface Collection
            extends FactoryNodeBuilderKeySteps, FactoryNodeBuilder.KeyStep<FactoryNodeBuilderGroupStep.Collection> {
    }
}
