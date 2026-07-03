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

import java.net.InetAddress;
import java.util.function.Function;

public interface CommonNodesInetAddress {
    TypeToken<InetAddress> TYPE_TOKEN = TypeToken.of(InetAddress.class);

    default FactoryNodeBuilderValueStep<InetAddress> inetAddressValue(String key) {
        return Nodes.INSTANCE.value(TYPE_TOKEN).key(key);
    }

    default ValueNode<InetAddress> emptyInetAddressValue(String key) {
        return Nodes.INSTANCE.emptyValue(TYPE_TOKEN, key);
    }

    default ValueNode<InetAddress> inetAddressValue(String key, @Nullable InetAddress defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, null);
    }

    default ValueNode<InetAddress> inetAddressValue(String key, @Nullable InetAddress defaultValue,
            @Nullable InetAddress value) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, value);
    }

    default FactoryNodeBuilderEnvStep.VariableStep<InetAddress> inetAddressEnv(String key) {
        return Nodes.INSTANCE.env(TYPE_TOKEN).key(key);
    }

    default EnvNode<InetAddress> inetAddressEnv(String key, String variable) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable);
    }

    default EnvNode<InetAddress> inetAddressEnv(String key, String variable,
            @Nullable Function<String, @Nullable InetAddress> adapter, @Nullable Validator<InetAddress> validator) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable, adapter, validator);
    }
}
