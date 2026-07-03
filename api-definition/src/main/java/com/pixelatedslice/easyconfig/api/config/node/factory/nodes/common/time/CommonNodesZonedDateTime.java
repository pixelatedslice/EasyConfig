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

import java.time.ZonedDateTime;
import java.util.function.Function;

public interface CommonNodesZonedDateTime {
    TypeToken<ZonedDateTime> TYPE_TOKEN = TypeToken.of(ZonedDateTime.class);

    default FactoryNodeBuilderValueStep<ZonedDateTime> zonedDateTimeValue(String key) {
        return Nodes.INSTANCE.value(TYPE_TOKEN).key(key);
    }

    default ValueNode<ZonedDateTime> emptyZonedDateTimeValue(String key) {
        return Nodes.INSTANCE.emptyValue(TYPE_TOKEN, key);
    }

    default ValueNode<ZonedDateTime> zonedDateTimeValue(String key, @Nullable ZonedDateTime defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, null);
    }

    default ValueNode<ZonedDateTime> zonedDateTimeValue(String key, @Nullable ZonedDateTime defaultValue,
            @Nullable ZonedDateTime value) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, value);
    }

    default FactoryNodeBuilderEnvStep.VariableStep<ZonedDateTime> zonedDateTimeEnv(String key) {
        return Nodes.INSTANCE.env(TYPE_TOKEN).key(key);
    }

    default EnvNode<ZonedDateTime> zonedDateTimeEnv(String key, String variable) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable);
    }

    default EnvNode<ZonedDateTime> zonedDateTimeEnv(String key, String variable,
            @Nullable Function<String, @Nullable ZonedDateTime> adapter, @Nullable Validator<ZonedDateTime> validator) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable, adapter, validator);
    }
}
