package com.pixelatedslice.easyconfig.impl.config.node.factory;

import com.google.auto.service.AutoService;
import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.NodeBuilders;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderGroupStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.impl.config.node.collection.builder.CollectionNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.container.builder.ContainerNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.env.builder.EnvNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.value.builder.ValueNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Function;

@AutoService(NodeBuilders.class)
@NullMarked
public class NodeBuildersImpl implements NodeBuilders {
    @Override
    public <T> FactoryNodeBuilderKeySteps.Value<T> createValueNodeBuilder(TypeToken<T> typeToken) {
        return new ValueNodeBuilder<>(typeToken);
    }

    @Override
    public <T> ValueNode<T> createValueNode(TypeToken<T> type, String key, @Nullable T defaultValue,
            @Nullable T value) {
        return new ValueNodeBuilder<>(type).key(key).defaultValue(defaultValue).value(value).build();
    }

    @Override
    public <T> FactoryNodeBuilderKeySteps.Env<T> createEnvNodeBuilder(TypeToken<T> typeToken) {
        return new EnvNodeBuilder<>(typeToken);
    }

    @Override
    public <T> EnvNode<T> createEnvNode(TypeToken<T> typeToken, String key, String variable,
            @Nullable Function<String, @Nullable T> adapter, @Nullable Validator<T> validator) {
        return new EnvNodeBuilder<>(typeToken)
                .key(key)
                .variable(variable)
                .adapter(adapter)
                .validator(validator)
                .build();
    }

    @Override
    public <T> FactoryNodeBuilderEnvStep.AdapterValidatorStep<T> createEnvNodeBuilder(TypeToken<T> typeToken,
            String key, String variable) {
        return new EnvNodeBuilder<>(typeToken).key(key).variable(variable);
    }

    @Override
    public FactoryNodeBuilderGroupStep.Container createContainerNodeBuilder(String key) {
        return new ContainerNodeBuilder(key);
    }

    @Override
    public FactoryNodeBuilderGroupStep.Collection createCollectionNodeBuilder(String key) {
        return new CollectionNodeBuilder(key);
    }
}
