package com.pixelatedslice.easyconfig.api.config.node.factory.nodes;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.NodeBuilders;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderGroupStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.utils.typetoken.TypeTokenUtils;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Function;

@SuppressWarnings("unused")
@NullMarked
public interface Nodes {
    Nodes INSTANCE = new Nodes() {
    };

    default <T> FactoryNodeBuilderKeySteps.Value<T> value(Class<T> simpleType) {
        return this.value(TypeTokenUtils.getSimpleOrThrow(simpleType));
    }

    default <T> ValueNode<T> emptyValue(Class<T> simpleType, String key) {
        return this.emptyValue(TypeTokenUtils.getSimpleOrThrow(simpleType), key);
    }

    default <T> FactoryNodeBuilderKeySteps.Value<T> value(TypeToken<T> typeToken) {
        return NodeBuilders.INSTANCE.createValueNodeBuilder(typeToken);
    }

    default <T> ValueNode<T> emptyValue(TypeToken<T> typeToken, String key) {
        return this.value(typeToken, key, null, null);
    }

    default <T> ValueNode<T> value(Class<T> simpleType, String key, @Nullable T defaultValue) {
        return this.value(TypeTokenUtils.getSimpleOrThrow(simpleType), key, defaultValue);
    }

    default <T> ValueNode<T> value(Class<T> simpleType, String key, @Nullable T defaultValue, @Nullable T value) {
        return this.value(TypeTokenUtils.getSimpleOrThrow(simpleType), key, defaultValue, value);
    }

    default <T> ValueNode<T> value(TypeToken<T> typeToken, String key, @Nullable T defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(typeToken, key, defaultValue, null);
    }

    default <T> ValueNode<T> value(TypeToken<T> typeToken, String key, @Nullable T defaultValue, @Nullable T value) {
        return NodeBuilders.INSTANCE.createValueNode(typeToken, key, defaultValue, value);
    }

    default <T> FactoryNodeBuilderKeySteps.Env<T> env(Class<T> simpleType) {
        return this.env(TypeTokenUtils.getSimpleOrThrow(simpleType));
    }

    default <T> FactoryNodeBuilderKeySteps.Env<T> env(TypeToken<T> typeToken) {
        return NodeBuilders.INSTANCE.createEnvNodeBuilder(typeToken);
    }

    default <T> EnvNode<T> env(Class<T> simpleType, String key, String variable) {
        return this.env(TypeTokenUtils.getSimpleOrThrow(simpleType), key, variable);
    }

    default <T> EnvNode<T> env(Class<T> simpleType, String key, String variable,
            @Nullable Function<String, @Nullable T> adapter, @Nullable Validator<T> validator) {
        return this.env(TypeTokenUtils.getSimpleOrThrow(simpleType), key, variable, adapter, validator);
    }

    default <T> EnvNode<T> env(TypeToken<T> typeToken, String key, String variable) {
        return NodeBuilders.INSTANCE.createEnvNode(typeToken, key, variable, null, null);
    }

    default <T> EnvNode<T> env(TypeToken<T> typeToken, String key, String variable,
            @Nullable Function<String, @Nullable T> adapter, @Nullable Validator<T> validator) {
        return NodeBuilders.INSTANCE.createEnvNode(typeToken, key, variable, adapter, validator);
    }

    default <T> FactoryNodeBuilderEnvStep.AdapterValidatorStep<T> envBuilder(Class<T> simpleType, String key,
            String variable) {
        return this.envBuilder(TypeTokenUtils.getSimpleOrThrow(simpleType), key, variable);
    }

    default <T> FactoryNodeBuilderEnvStep.AdapterValidatorStep<T> envBuilder(TypeToken<T> typeToken, String key,
            String variable) {
        return NodeBuilders.INSTANCE.createEnvNodeBuilder(typeToken, key, variable);
    }

    default FactoryNodeBuilderGroupStep.Container container(String key) {
        return NodeBuilders.INSTANCE.createContainerNodeBuilder(key);
    }

    default FactoryNodeBuilderGroupStep.Collection collection(String key) {
        return NodeBuilders.INSTANCE.createCollectionNodeBuilder(key);
    }
}
