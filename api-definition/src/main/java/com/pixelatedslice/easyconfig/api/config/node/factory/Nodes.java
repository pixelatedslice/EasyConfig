package com.pixelatedslice.easyconfig.api.config.node.factory;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.NodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.NodeBuilderGroupStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.NodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.factory.spi.NodeFactoryService;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.utils.typetoken.TypeTokenUtils;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Function;

@SuppressWarnings("unused")
@NullMarked
public interface Nodes {
    static <T> NodeBuilderKeySteps.Value<T> value(Class<T> simpleType) {
        return Nodes.value(TypeTokenUtils.getSimpleOrThrow(simpleType));
    }

    static <T> ValueNode<T> emptyValue(Class<T> simpleType, String key) {
        return Nodes.emptyValue(TypeTokenUtils.getSimpleOrThrow(simpleType), key);
    }

    static <T> NodeBuilderKeySteps.Value<T> value(TypeToken<T> typeToken) {
        return NodeFactoryService.INSTANCE.createValueNodeBuilder(typeToken);
    }

    static <T> ValueNode<T> emptyValue(TypeToken<T> typeToken, String key) {
        return Nodes.value(typeToken, key, null, null);
    }

    static <T> ValueNode<T> value(Class<T> simpleType, String key, @Nullable T defaultValue) {
        return Nodes.value(TypeTokenUtils.getSimpleOrThrow(simpleType), key, defaultValue);
    }

    static <T> ValueNode<T> value(Class<T> simpleType, String key, @Nullable T defaultValue, @Nullable T value) {
        return Nodes.value(TypeTokenUtils.getSimpleOrThrow(simpleType), key, defaultValue, value);
    }

    static <T> ValueNode<T> value(TypeToken<T> typeToken, String key, @Nullable T defaultValue) {
        return NodeFactoryService.INSTANCE.createValueNode(typeToken, key, defaultValue, null);
    }

    static <T> ValueNode<T> value(TypeToken<T> typeToken, String key, @Nullable T defaultValue, @Nullable T value) {
        return NodeFactoryService.INSTANCE.createValueNode(typeToken, key, defaultValue, value);
    }

    static <T> NodeBuilderKeySteps.Env<T> env(Class<T> simpleType) {
        return Nodes.env(TypeTokenUtils.getSimpleOrThrow(simpleType));
    }

    static <T> NodeBuilderKeySteps.Env<T> env(TypeToken<T> typeToken) {
        return NodeFactoryService.INSTANCE.createEnvNodeBuilder(typeToken);
    }

    static <T> EnvNode<T> env(Class<T> simpleType, String key, String variable) {
        return Nodes.env(TypeTokenUtils.getSimpleOrThrow(simpleType), key, variable);
    }

    @SuppressWarnings("MethodWithTooManyParameters")
    static <T> EnvNode<T> env(Class<T> simpleType, String key, String variable,
            @Nullable Function<String, @Nullable T> adapter, @Nullable Validator<T> validator) {
        return Nodes.env(TypeTokenUtils.getSimpleOrThrow(simpleType), key, variable, adapter, validator);
    }

    static <T> EnvNode<T> env(TypeToken<T> typeToken, String key, String variable) {
        return NodeFactoryService.INSTANCE.createEnvNode(typeToken, key, variable, null, null);
    }

    @SuppressWarnings("MethodWithTooManyParameters")
    static <T> EnvNode<T> env(TypeToken<T> typeToken, String key, String variable,
            @Nullable Function<String, @Nullable T> adapter, @Nullable Validator<T> validator) {
        return NodeFactoryService.INSTANCE.createEnvNode(typeToken, key, variable, adapter, validator);
    }

    static <T> NodeBuilderEnvStep.AdapterValidatorStep<T> envBuilder(Class<T> simpleType, String key,
            String variable) {
        return Nodes.envBuilder(TypeTokenUtils.getSimpleOrThrow(simpleType), key, variable);
    }

    static <T> NodeBuilderEnvStep.AdapterValidatorStep<T> envBuilder(TypeToken<T> typeToken, String key,
            String variable) {
        return NodeFactoryService.INSTANCE.createEnvNodeBuilder(typeToken, key, variable);
    }

    static NodeBuilderGroupStep.Container container(String key) {
        return NodeFactoryService.INSTANCE.createContainerNodeBuilder(key);
    }

    static NodeBuilderGroupStep.Collection collection(String key) {
        return NodeFactoryService.INSTANCE.createCollectionNodeBuilder(key);
    }
}
