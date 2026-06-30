package com.pixelatedslice.easyconfig.api.config.node.builder;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderGroupStep;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public interface NodeBuilders {
    <T> FactoryNodeBuilderKeySteps.Value<T> createValueNodeBuilder(TypeToken<T> typeToken);

    <T> ValueNode<T> createValueNode(TypeToken<T> type, String key, @Nullable T defaultValue, @Nullable T value);

    <T> FactoryNodeBuilderKeySteps.Env<T> createEnvNodeBuilder(TypeToken<T> typeToken);

    <T> EnvNode<T> createEnvNode(TypeToken<T> typeToken, String key, String variable);

    <T> FactoryNodeBuilderEnvStep.AdapterValidatorStep<T> createEnvNodeBuilder(TypeToken<T> typeToken, String key,
            String variable);

    FactoryNodeBuilderGroupStep.Container createContainerNodeBuilder(String key);

    FactoryNodeBuilderGroupStep.Collection createCollectionNodeBuilder(String key);
}
