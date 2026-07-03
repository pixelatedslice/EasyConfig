package com.pixelatedslice.easyconfig.api.config.node.factory;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderGroupStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.ServiceLoader;
import java.util.function.Function;

@NullMarked
public interface NodeBuilders {
    NodeBuilders INSTANCE = ServiceLoader
            .load(NodeBuilders.class)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No NodeBuilders implementation found"));

    <T> FactoryNodeBuilderKeySteps.Value<T> createValueNodeBuilder(TypeToken<T> typeToken);

    <T> ValueNode<T> createValueNode(TypeToken<T> type, String key, @Nullable T defaultValue, @Nullable T value);

    <T> FactoryNodeBuilderKeySteps.Env<T> createEnvNodeBuilder(TypeToken<T> typeToken);

    <T> EnvNode<T> createEnvNode(TypeToken<T> typeToken, String key, String variable,
            @Nullable Function<String, @Nullable T> adapter, @Nullable Validator<T> validator);

    <T> FactoryNodeBuilderEnvStep.AdapterValidatorStep<T> createEnvNodeBuilder(TypeToken<T> typeToken, String key,
            String variable);

    FactoryNodeBuilderGroupStep.Container createContainerNodeBuilder(String key);

    FactoryNodeBuilderGroupStep.Collection createCollectionNodeBuilder(String key);
}
