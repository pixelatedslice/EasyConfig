package com.pixelatedslice.easyconfig.impl.config.node.factory;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.*;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface NodeBuilderHandlers {
    interface Value<T> extends
            NodeBuilderKeySteps.Value<T>,
            NodeBuilderValueStep<T>,
            NodeBuilder.BuildStep<ValueNode<T>> {
    }

    interface Env<T> extends
            NodeBuilderKeySteps.Env<T>,
            NodeBuilderEnvStep.VariableStep<T>,
            NodeBuilderEnvStep.AdapterValidatorStep<T>,
            NodeBuilder.BuildStep<EnvNode<T>> {
    }

    interface Container extends
            NodeBuilderKeySteps.Container,
            NodeBuilderGroupStep.Container {
    }

    interface Collection extends
            NodeBuilderKeySteps.Collection,
            NodeBuilderGroupStep.Collection {
    }
}
