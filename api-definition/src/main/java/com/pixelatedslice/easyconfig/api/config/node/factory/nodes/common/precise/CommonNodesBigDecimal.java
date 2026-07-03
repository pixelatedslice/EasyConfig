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

import java.math.BigDecimal;
import java.util.function.Function;

public interface CommonNodesBigDecimal {
    TypeToken<BigDecimal> TYPE_TOKEN = TypeToken.of(BigDecimal.class);

    default FactoryNodeBuilderValueStep<BigDecimal> bigDecimalValue(String key) {
        return Nodes.INSTANCE.value(TYPE_TOKEN).key(key);
    }

    default ValueNode<BigDecimal> emptyBigDecimalValue(String key) {
        return Nodes.INSTANCE.emptyValue(TYPE_TOKEN, key);
    }

    default ValueNode<BigDecimal> bigDecimalValue(String key, @Nullable BigDecimal defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, null);
    }

    default ValueNode<BigDecimal> bigDecimalValue(String key, @Nullable BigDecimal defaultValue,
            @Nullable BigDecimal value) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, value);
    }

    default FactoryNodeBuilderEnvStep.VariableStep<BigDecimal> bigDecimalEnv(String key) {
        return Nodes.INSTANCE.env(TYPE_TOKEN).key(key);
    }

    default EnvNode<BigDecimal> bigDecimalEnv(String key, String variable) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable);
    }

    default EnvNode<BigDecimal> bigDecimalEnv(String key, String variable,
            @Nullable Function<String, @Nullable BigDecimal> adapter, @Nullable Validator<BigDecimal> validator) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable, adapter, validator);
    }
}
