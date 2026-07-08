package com.pixelatedslice.easyconfig.api.config.node.factory.spi;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.NodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.NodeBuilderGroupStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.NodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.ServiceLoader;
import java.util.function.Function;

@NullMarked
public interface NodeFactorySpi {
    NodeFactorySpi INSTANCE = ServiceLoader
            .load(NodeFactorySpi.class)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No NodeBuilders implementation found"));

    <T> NodeBuilderKeySteps.Value<T> createValueNodeBuilder(TypeToken<T> typeToken);

    <T> ValueNode<T> createValueNode(TypeToken<T> type, String key, @Nullable T defaultValue, @Nullable T value);

    <T> NodeBuilderKeySteps.Env<T> createEnvNodeBuilder(TypeToken<T> typeToken);

    @SuppressWarnings("MethodWithTooManyParameters")
    <T> EnvNode<T> createEnvNode(TypeToken<T> typeToken, String key, String variable,
            @Nullable Function<String, @Nullable T> adapter, @Nullable Validator<T> validator);

    <T> NodeBuilderEnvStep.AdapterValidatorStep<T> createEnvNodeBuilder(TypeToken<T> typeToken, String key,
            String variable);

    NodeBuilderGroupStep.Container createContainerNodeBuilder(String key);

    NodeBuilderGroupStep.Collection createCollectionNodeBuilder(String key);
}
