package com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.collection;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.NodeBuilders;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderValueStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.Nodes;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.Nullable;

import java.util.Map;
import java.util.function.Function;

import static com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.collection.CommonNodesCollection.newTypeToken;

public interface CommonNodesMap {
    default <K, V> FactoryNodeBuilderValueStep<Map<K, V>> mapValue(String key) {
        return Nodes.INSTANCE.value(CommonNodesCollection.<Map<K, V>>newTypeToken()).key(key);
    }

    default <K, V> ValueNode<Map<K, V>> emptyMapValue(String key) {
        return Nodes.INSTANCE.emptyValue(newTypeToken(), key);
    }

    default <K, V> ValueNode<Map<K, V>> mapValue(String key, @Nullable Map<K, V> defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(newTypeToken(), key, defaultValue, null);
    }

    default <K, V> ValueNode<Map<K, V>> mapValue(String key, @Nullable Map<K, V> defaultValue,
            @Nullable Map<K, V> value) {
        return NodeBuilders.INSTANCE.createValueNode(newTypeToken(), key, defaultValue, value);
    }

    default <K, V> FactoryNodeBuilderEnvStep.VariableStep<Map<K, V>> mapEnv(String key) {
        return Nodes.INSTANCE.env(CommonNodesCollection.<Map<K, V>>newTypeToken()).key(key);
    }

    default <K, V> EnvNode<Map<K, V>> mapEnv(String key, String variable) {
        return Nodes.INSTANCE.env(newTypeToken(), key, variable);
    }

    default <K, V> EnvNode<Map<K, V>> mapEnv(String key, String variable,
            @Nullable Function<String, @Nullable Map<K, V>> adapter, @Nullable Validator<Map<K, V>> validator) {
        return Nodes.INSTANCE.env(newTypeToken(), key, variable, adapter, validator);
    }
}
