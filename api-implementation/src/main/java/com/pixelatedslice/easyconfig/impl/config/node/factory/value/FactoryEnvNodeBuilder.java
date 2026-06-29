package com.pixelatedslice.easyconfig.impl.config.node.factory.value;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.impl.config.node.factory.AbstractFactoryNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;

@NullMarked
public class FactoryEnvNodeBuilder<T>
        extends
        AbstractFactoryNodeBuilder<EnvNode<T>, FactoryNodeBuilderKeySteps.Env<T>,
                FactoryNodeBuilderEnvStep.VariableStep<T>>
        implements FactoryNodeBuilderEnvStep.VariableStep<T>, FactoryNodeBuilderEnvStep.AdapterValidatorStep<T> {
    private final TypeToken<T> typeToken;
    private @Nullable Function<String, @Nullable T> adapter;
    private @Nullable Validator<T> validator;
    private @Nullable String variable;

    public FactoryEnvNodeBuilder(TypeToken<T> typeToken) {
        this.typeToken = typeToken;
    }

    @Override
    public FactoryNodeBuilderEnvStep.AdapterValidatorStep<T> adapter(@Nullable Function<String, @Nullable T> adapter) {
        this.adapter = adapter;
        return this;
    }

    @Override
    public FactoryNodeBuilderEnvStep.AdapterValidatorStep<T> validator(@Nullable Validator<T> validator) {
        this.validator = validator;
        return this;
    }

    @Override
    public EnvNode<T> build() {
        Objects.requireNonNull(this.variable);

        return new ValueNodeOriginalBuilder<>(this.typeToken, Objects.requireNonNull(this.key))
                .parent(this.parent)
                .env(this.variable)
                .adapter(this.adapter)
                .validator(this.validator)
                .build();
    }

    @Override
    public FactoryNodeBuilderEnvStep.AdapterValidatorStep<T> variable(String variable) {
        this.variable = variable;
        return this;
    }
}
