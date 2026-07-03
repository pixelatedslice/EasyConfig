package com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.floating;

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

public interface CommonNodesDouble {
    TypeToken<Double> TYPE_TOKEN = TypeToken.of(double.class);

    default FactoryNodeBuilderValueStep<Double> doubleValue(String key) {
        return Nodes.INSTANCE.value(TYPE_TOKEN).key(key);
    }

    default ValueNode<Double> emptyDoubleValue(String key) {
        return Nodes.INSTANCE.emptyValue(TYPE_TOKEN, key);
    }

    default ValueNode<Double> doubleValue(String key, @Nullable Double defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, null);
    }

    default ValueNode<Double> doubleValue(String key, @Nullable Double defaultValue,
            @Nullable Double value) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, value);
    }

    default FactoryNodeBuilderEnvStep.VariableStep<Double> doubleEnv(String key) {
        return Nodes.INSTANCE.env(TYPE_TOKEN).key(key);
    }

    default EnvNode<Double> doubleEnv(String key, String variable) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable);
    }

    default EnvNode<Double> doubleEnv(String key, String variable,
            @Nullable Function<String, @Nullable Double> adapter, @Nullable Validator<Double> validator) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable, adapter, validator);
    }
}
