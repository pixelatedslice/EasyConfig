package com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.precise;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.NodeBuilders;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderValueStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.Nodes;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.Nullable;

import java.math.BigInteger;
import java.util.function.Function;

public interface CommonNodesBigInteger {
    TypeToken<BigInteger> TYPE_TOKEN = TypeToken.of(BigInteger.class);

    default FactoryNodeBuilderValueStep<BigInteger> bigIntegerValue(String key) {
        return Nodes.INSTANCE.value(TYPE_TOKEN).key(key);
    }

    default ValueNode<BigInteger> emptyBigIntegerValue(String key) {
        return Nodes.INSTANCE.emptyValue(TYPE_TOKEN, key);
    }

    default ValueNode<BigInteger> bigIntegerValue(String key, @Nullable BigInteger defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, null);
    }

    default ValueNode<BigInteger> bigIntegerValue(String key, @Nullable BigInteger defaultValue,
            @Nullable BigInteger value) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, value);
    }

    default FactoryNodeBuilderEnvStep.VariableStep<BigInteger> bigIntegerEnv(String key) {
        return Nodes.INSTANCE.env(TYPE_TOKEN).key(key);
    }

    default EnvNode<BigInteger> bigIntegerEnv(String key, String variable) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable);
    }

    default EnvNode<BigInteger> bigIntegerEnv(String key, String variable,
            @Nullable Function<String, @Nullable BigInteger> adapter, @Nullable Validator<BigInteger> validator) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable, adapter, validator);
    }
}
