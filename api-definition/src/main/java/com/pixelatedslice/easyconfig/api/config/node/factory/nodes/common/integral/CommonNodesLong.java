package com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.integral;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.NodeBuilders;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderValueStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.Nodes;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.Nullable;

import java.util.function.Function;

public interface CommonNodesLong {
    TypeToken<Long> TYPE_TOKEN = TypeToken.of(long.class);

    default FactoryNodeBuilderValueStep<Long> longValue(String key) {
        return Nodes.INSTANCE.value(TYPE_TOKEN).key(key);
    }

    default ValueNode<Long> emptyLongValue(String key) {
        return Nodes.INSTANCE.emptyValue(TYPE_TOKEN, key);
    }

    default ValueNode<Long> longValue(String key, @Nullable Long defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, null);
    }

    default ValueNode<Long> longValue(String key, @Nullable Long defaultValue,
            @Nullable Long value) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, value);
    }

    default FactoryNodeBuilderEnvStep.VariableStep<Long> longEnv(String key) {
        return Nodes.INSTANCE.env(TYPE_TOKEN).key(key);
    }

    default EnvNode<Long> longEnv(String key, String variable) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable);
    }

    default EnvNode<Long> longEnv(String key, String variable,
            @Nullable Function<String, @Nullable Long> adapter, @Nullable Validator<Long> validator) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable, adapter, validator);
    }
}
