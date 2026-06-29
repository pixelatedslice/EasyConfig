package com.pixelatedslice.easyconfig.impl.config.node.factory.value;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderValueStep;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.impl.config.node.factory.AbstractFactoryNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

@NullMarked
public class FactoryValueNodeBuilder<T>
        extends
        AbstractFactoryNodeBuilder<ValueNode<T>, FactoryNodeBuilderKeySteps.Value<T>, FactoryNodeBuilderValueStep<T>>
        implements FactoryNodeBuilderValueStep<T>, FactoryNodeBuilderValueStep.FirstStep<T>,
        FactoryNodeBuilderValueStep.DefaultValueAndExtrasStep<T>, FactoryNodeBuilderValueStep.ValueAndExtrasStep<T> {

    private final TypeToken<T> typeToken;
    private @Nullable T defaultValue;
    private @Nullable T value;
    private @Nullable Validator<T> validator;
    private @Nullable Serializer<T> serializer;

    public FactoryValueNodeBuilder(TypeToken<T> typeToken) {
        this.typeToken = typeToken;
    }

    @Override
    public ValueNode<T> build() {
        Objects.requireNonNull(this.key);

        return new ValueNodeOriginalBuilder<>(this.typeToken, this.key)
                .parent(this.parent)
                .defaultValue(this.defaultValue)
                .value(this.value)
                .validator(this.validator)
                .serializer(this.serializer)
                .build();
    }

    @Override
    public ValueAndExtrasStep<T> defaultValue(@Nullable T defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public DefaultValueAndExtrasStep<T> value(@Nullable T value) {
        this.value = value;
        return this;
    }

    @Override
    public ExtrasStep<T> validator(@Nullable Validator<T> validator) {
        this.validator = validator;
        return this;
    }

    @Override
    public ExtrasStep<T> serializer(@Nullable Serializer<T> serializer) {
        this.serializer = serializer;
        return this;
    }
}
