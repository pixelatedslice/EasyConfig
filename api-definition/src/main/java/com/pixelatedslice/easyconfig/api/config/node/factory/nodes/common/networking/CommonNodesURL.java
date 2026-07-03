package com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.networking;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.NodeBuilders;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderValueStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.Nodes;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.Nullable;

import java.net.URL;
import java.util.function.Function;

public interface CommonNodesURL {
    TypeToken<URL> TYPE_TOKEN = TypeToken.of(URL.class);

    default FactoryNodeBuilderValueStep<URL> urlValue(String key) {
        return Nodes.INSTANCE.value(TYPE_TOKEN).key(key);
    }

    default ValueNode<URL> emptyURLValue(String key) {
        return Nodes.INSTANCE.emptyValue(TYPE_TOKEN, key);
    }

    default ValueNode<URL> urlValue(String key, @Nullable URL defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, null);
    }

    default ValueNode<URL> urlValue(String key, @Nullable URL defaultValue,
            @Nullable URL value) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, value);
    }

    default FactoryNodeBuilderEnvStep.VariableStep<URL> urlEnv(String key) {
        return Nodes.INSTANCE.env(TYPE_TOKEN).key(key);
    }

    default EnvNode<URL> urlEnv(String key, String variable) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable);
    }

    default EnvNode<URL> urlEnv(String key, String variable,
            @Nullable Function<String, @Nullable URL> adapter, @Nullable Validator<URL> validator) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable, adapter, validator);
    }
}
