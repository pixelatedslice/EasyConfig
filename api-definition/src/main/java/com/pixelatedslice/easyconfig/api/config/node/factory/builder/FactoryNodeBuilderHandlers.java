package com.pixelatedslice.easyconfig.api.config.node.factory.builder;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface FactoryNodeBuilderHandlers {
    interface Value<T> extends
            FactoryNodeBuilderKeySteps.Value<T>,
            FactoryNodeBuilderValueStep<T>,
            FactoryNodeBuilder.BuildStep<ValueNode<T>> {
    }

    interface Env<T> extends
            FactoryNodeBuilderKeySteps.Env<T>,
            FactoryNodeBuilderEnvStep.VariableStep<T>,
            FactoryNodeBuilderEnvStep.AdapterValidatorStep<T>,
            FactoryNodeBuilder.BuildStep<EnvNode<T>> {
    }

    interface Container extends
            FactoryNodeBuilderKeySteps.Container,
            FactoryNodeBuilderGroupStep.Container {
    }

    interface Collection extends
            FactoryNodeBuilderKeySteps.Collection,
            FactoryNodeBuilderGroupStep.Collection {
    }
}
