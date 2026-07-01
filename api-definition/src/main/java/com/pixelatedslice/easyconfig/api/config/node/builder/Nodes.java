package com.pixelatedslice.easyconfig.api.config.node.builder;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderGroupStep;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.utils.typetoken.TypeTokenUtils;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.ServiceLoader;

@SuppressWarnings("unused")
@NullMarked
public interface Nodes {
    NodeBuilders NODE_BUILDERS = ServiceLoader
            .load(NodeBuilders.class)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No NodeBuilders implementation found"));

    static <T> FactoryNodeBuilderKeySteps.Value<T> value(Class<T> simpleType) {
        return Nodes.value(TypeTokenUtils.getSimpleOrThrow(simpleType));
    }

    static <T> FactoryNodeBuilderKeySteps.Value<T> value(TypeToken<T> typeToken) {
        return NODE_BUILDERS.createValueNodeBuilder(typeToken);
    }

    static <T> ValueNode<T> value(Class<T> simpleType, String key, @Nullable T defaultValue) {
        return Nodes.value(TypeTokenUtils.getSimpleOrThrow(simpleType), key, defaultValue);
    }

    static <T> ValueNode<T> value(Class<T> simpleType, String key, @Nullable T defaultValue, @Nullable T value) {
        return Nodes.value(TypeTokenUtils.getSimpleOrThrow(simpleType), key, defaultValue, value);
    }

    static <T> ValueNode<T> value(TypeToken<T> typeToken, String key, @Nullable T defaultValue) {
        return NODE_BUILDERS.createValueNode(typeToken, key, defaultValue, null);
    }

    static <T> ValueNode<T> value(TypeToken<T> typeToken, String key, @Nullable T defaultValue, @Nullable T value) {
        return NODE_BUILDERS.createValueNode(typeToken, key, defaultValue, value);
    }

    static <T> FactoryNodeBuilderKeySteps.Env<T> env(Class<T> simpleType) {
        return Nodes.env(TypeTokenUtils.getSimpleOrThrow(simpleType));
    }

    static <T> FactoryNodeBuilderKeySteps.Env<T> env(TypeToken<T> typeToken) {
        return NODE_BUILDERS.createEnvNodeBuilder(typeToken);
    }

    static <T> EnvNode<T> env(Class<T> simpleType, String key, String variable) {
        return Nodes.env(TypeTokenUtils.getSimpleOrThrow(simpleType), key, variable);
    }

    static <T> EnvNode<T> env(TypeToken<T> typeToken, String key, String variable) {
        return NODE_BUILDERS.createEnvNode(typeToken, key, variable);
    }

    static <T> FactoryNodeBuilderEnvStep.AdapterValidatorStep<T> envBuilder(Class<T> simpleType, String key,
            String variable) {
        return Nodes.envBuilder(TypeTokenUtils.getSimpleOrThrow(simpleType), key, variable);
    }

    static <T> FactoryNodeBuilderEnvStep.AdapterValidatorStep<T> envBuilder(TypeToken<T> typeToken, String key,
            String variable) {
        return NODE_BUILDERS.createEnvNodeBuilder(typeToken, key, variable);
    }

    static FactoryNodeBuilderGroupStep.Container container(String key) {
        return NODE_BUILDERS.createContainerNodeBuilder(key);
    }

    static FactoryNodeBuilderGroupStep.Collection collection(String key) {
        return NODE_BUILDERS.createCollectionNodeBuilder(key);
    }
}
