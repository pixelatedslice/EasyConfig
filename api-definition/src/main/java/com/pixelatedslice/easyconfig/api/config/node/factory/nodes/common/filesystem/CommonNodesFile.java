package com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common.filesystem;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.NodeBuilders;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderValueStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.Nodes;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.Nullable;

import java.io.File;
import java.util.function.Function;

public interface CommonNodesFile {
    TypeToken<File> TYPE_TOKEN = TypeToken.of(File.class);

    default FactoryNodeBuilderValueStep<File> fileValue(String key) {
        return Nodes.INSTANCE.value(TYPE_TOKEN).key(key);
    }

    default ValueNode<File> emptyFileValue(String key) {
        return Nodes.INSTANCE.emptyValue(TYPE_TOKEN, key);
    }

    default ValueNode<File> fileValue(String key, @Nullable File defaultValue) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, null);
    }

    default ValueNode<File> fileValue(String key, @Nullable File defaultValue,
            @Nullable File value) {
        return NodeBuilders.INSTANCE.createValueNode(TYPE_TOKEN, key, defaultValue, value);
    }

    default FactoryNodeBuilderEnvStep.VariableStep<File> fileEnv(String key) {
        return Nodes.INSTANCE.env(TYPE_TOKEN).key(key);
    }

    default EnvNode<File> fileEnv(String key, String variable) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable);
    }

    default EnvNode<File> fileEnv(String key, String variable,
            @Nullable Function<String, @Nullable File> adapter, @Nullable Validator<File> validator) {
        return Nodes.INSTANCE.env(TYPE_TOKEN, key, variable, adapter, validator);
    }
}
