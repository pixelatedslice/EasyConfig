package com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.time;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.NodeBuilders;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderValueStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.Nodes;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.function.Function;

public interface CommonNodesLocalDateTime {
    TypeToken<LocalDateTime> TYPE_TOKEN = TypeToken.of(LocalDateTime.class);

    default FactoryNodeBuilderValueStep<LocalDateTime> localDateTimeValue(String key) {
        return Nodes.INSTANCE.value(TYPE_TOKEN).key(key);
    }

    default ValueNode<LocalDateTime> emptyLocalDateTimeValue(String key) {
        return Nodes.INSTANCE.emptyValue(TYPE_TOKEN, key);
    }

    default ValueNode<LocalDateTime> localDateTimeValue(String key, @Nullable LocalDateTime defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, null);
    }

    default ValueNode<LocalDateTime> localDateTimeValue(String key, @Nullable LocalDateTime defaultValue,
            @Nullable LocalDateTime value) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, value);
    }

    default FactoryNodeBuilderEnvStep.VariableStep<LocalDateTime> localDateTimeEnv(String key) {
        return Nodes.INSTANCE.env(TYPE_TOKEN).key(key);
    }

    default EnvNode<LocalDateTime> localDateTimeEnv(String key, String variable) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable);
    }

    default EnvNode<LocalDateTime> localDateTimeEnv(String key, String variable,
            @Nullable Function<String, @Nullable LocalDateTime> adapter, @Nullable Validator<LocalDateTime> validator) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable, adapter, validator);
    }
}
