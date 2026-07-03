package com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.character;

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

public interface CommonNodesString {
    TypeToken<String> TYPE_TOKEN = TypeToken.of(String.class);

    default FactoryNodeBuilderValueStep<String> stringValue(String key) {
        return Nodes.INSTANCE.value(TYPE_TOKEN).key(key);
    }

    default ValueNode<String> emptyStringValue(String key) {
        return Nodes.INSTANCE.emptyValue(TYPE_TOKEN, key);
    }

    default ValueNode<String> stringValue(String key, @Nullable String defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, null);
    }

    default ValueNode<String> stringValue(String key, @Nullable String defaultValue,
            @Nullable String value) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, value);
    }

    default FactoryNodeBuilderEnvStep.VariableStep<String> stringEnv(String key) {
        return Nodes.INSTANCE.env(TYPE_TOKEN).key(key);
    }

    default EnvNode<String> stringEnv(String key, String variable) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable);
    }

    default EnvNode<String> stringEnv(String key, String variable,
            @Nullable Function<String, @Nullable String> adapter, @Nullable Validator<String> validator) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable, adapter, validator);
    }
}
