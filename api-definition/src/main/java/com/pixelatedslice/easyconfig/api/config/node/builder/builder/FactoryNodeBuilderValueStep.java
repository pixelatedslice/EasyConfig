package com.pixelatedslice.easyconfig.api.config.node.builder.builder;

import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("unused")
@NullMarked
public interface FactoryNodeBuilderValueStep<T>
        extends FactoryNodeBuilder {

    @NullMarked
    interface CurrentValueStep<T> extends FactoryNodeBuilderValueStep<T> {
        DefaultValueAndExtrasStep<T> value(@Nullable T value);
    }

    @NullMarked
    interface CurrentDefaultValueStep<T> extends FactoryNodeBuilderValueStep<T> {
        ValueAndExtrasStep<T> defaultValue(@Nullable T defaultValue);
    }

    @NullMarked
    interface FirstStep<T> extends FactoryNodeBuilderValueStep<T>, CurrentValueStep<T>,
            CurrentDefaultValueStep<T> {
    }

    @NullMarked
    interface DefaultValueAndExtrasStep<T>
            extends FactoryNodeBuilderValueStep<T>, CurrentDefaultValueStep<T>,
            ExtrasStep<T> {
    }

    @NullMarked
    interface ValueAndExtrasStep<T>
            extends FactoryNodeBuilderValueStep<T>, CurrentValueStep<T>, ExtrasStep<T> {
    }

    @NullMarked
    interface ExtrasStep<T> extends FactoryNodeBuilder, BuildStep<ValueNode<T>> {
        ExtrasStep<T> validator(@Nullable Validator<T> validator);

        ExtrasStep<T> serializer(@Nullable Serializer<T> serializer);
    }
}