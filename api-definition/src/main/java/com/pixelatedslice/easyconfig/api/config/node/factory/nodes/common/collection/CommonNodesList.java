package com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.collection;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.NodeBuilders;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderValueStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.Nodes;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.function.Function;

import static com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.collection.CommonNodesCollection.newTypeToken;

public interface CommonNodesList {
    default <T> FactoryNodeBuilderValueStep<List<T>> listValue(String key) {
        return Nodes.INSTANCE.value(CommonNodesCollection.<List<T>>newTypeToken()).key(key);
    }

    default <T> ValueNode<List<T>> emptyListValue(String key) {
        return Nodes.INSTANCE.emptyValue(newTypeToken(), key);
    }

    default <T> ValueNode<List<T>> listValue(String key, @Nullable List<T> defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(newTypeToken(), key, defaultValue, null);
    }

    default <T> ValueNode<List<T>> listValue(String key, @Nullable List<T> defaultValue,
            @Nullable List<T> value) {
        return NodeBuilders.INSTANCE.createValueNode(newTypeToken(), key, defaultValue, value);
    }

    default <T> FactoryNodeBuilderEnvStep.VariableStep<List<T>> listEnv(String key) {
        return Nodes.INSTANCE.env(CommonNodesCollection.<List<T>>newTypeToken()).key(key);
    }

    default <T> EnvNode<List<T>> listEnv(String key, String variable) {
        return Nodes.INSTANCE.env(newTypeToken(), key, variable);
    }

    default <T> EnvNode<List<T>> listEnv(String key, String variable,
            @Nullable Function<String, @Nullable List<T>> adapter, @Nullable Validator<List<T>> validator) {
        return Nodes.INSTANCE.env(newTypeToken(), key, variable, adapter, validator);
    }
}
