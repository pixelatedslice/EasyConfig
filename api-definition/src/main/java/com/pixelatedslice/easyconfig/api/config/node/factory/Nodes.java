package com.pixelatedslice.easyconfig.api.config.node.factory;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.utils.typetoken.TypeTokenUtils;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface Nodes {
    default <T> FactoryNodeBuilder.KeyStep.Value<T> value(Class<T> simpleType) {
        return this.value(TypeTokenUtils.getSimpleOrThrow(simpleType));
    }

    <T> FactoryNodeBuilder.KeyStep.Value<T> value(TypeToken<T> type);

    default <T> FactoryNodeBuilder.KeyStep.Value<T> value(Class<T> simpleType, String key, T defaultValue) {
        return this.value(TypeTokenUtils.getSimpleOrThrow(simpleType), key, defaultValue);
    }

    <T> FactoryNodeBuilder.KeyStep.Value<T> value(TypeToken<T> type, String key, T defaultValue);

    default <T> FactoryNodeBuilder.KeyStep.Env<T> env(Class<T> simpleType) {
        return this.env(TypeTokenUtils.getSimpleOrThrow(simpleType));
    }

    <T> FactoryNodeBuilder.KeyStep.Env<T> env(TypeToken<T> type);

    default <T> FactoryNodeBuilder.KeyStep.Env<T> env(Class<T> simpleType, String key, String variable) {
        return this.env(TypeTokenUtils.getSimpleOrThrow(simpleType), key, variable);
    }

    <T> FactoryNodeBuilder.KeyStep.Env<T> env(TypeToken<T> type, String key, String variable);

    FactoryNodeBuilder.GroupStep.Container container(String key);

    FactoryNodeBuilder.GroupStep.Collection collection(String key);
}
