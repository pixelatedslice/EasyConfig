package com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.collection;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.NodeBuilders;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderValueStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.Nodes;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.Nullable;

import java.util.Set;
import java.util.function.Function;

import static com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.collection.CommonNodesCollection.newTypeToken;

public interface CommonNodesSet {
    default <T> FactoryNodeBuilderValueStep<Set<T>> setValue(String key) {
        return Nodes.INSTANCE.value(CommonNodesCollection.<Set<T>>newTypeToken()).key(key);
    }

    default <T> ValueNode<Set<T>> emptySetValue(String key) {
        return Nodes.INSTANCE.emptyValue(newTypeToken(), key);
    }

    default <T> ValueNode<Set<T>> setValue(String key, @Nullable Set<T> defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(newTypeToken(), key, defaultValue, null);
    }

    default <T> ValueNode<Set<T>> setValue(String key, @Nullable Set<T> defaultValue,
            @Nullable Set<T> value) {
        return NodeBuilders.INSTANCE.createValueNode(newTypeToken(), key, defaultValue, value);
    }

    default <T> FactoryNodeBuilderEnvStep.VariableStep<Set<T>> setEnv(String key) {
        return Nodes.INSTANCE.env(CommonNodesCollection.<Set<T>>newTypeToken()).key(key);
    }

    default <T> EnvNode<Set<T>> setEnv(String key, String variable) {
        return Nodes.INSTANCE.env(newTypeToken(), key, variable);
    }

    default <T> EnvNode<Set<T>> setEnv(String key, String variable,
            @Nullable Function<String, @Nullable Set<T>> adapter, @Nullable Validator<Set<T>> validator) {
        return Nodes.INSTANCE.env(newTypeToken(), key, variable, adapter, validator);
    }
}
