package com.pixelatedslice.easyconfig.api.config.node.builder.builder;

import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("unused")
@NullMarked
public interface FactoryNodeBuilderValueStep<T>
        extends FactoryNodeBuilder, FactoryNodeBuilder.BuildStep<ValueNode<T>> {
    FactoryNodeBuilderValueStep<T> value(@Nullable T value);

    FactoryNodeBuilderValueStep<T> defaultValue(@Nullable T defaultValue);

    FactoryNodeBuilderValueStep<T> validator(@Nullable Validator<T> validator);

    FactoryNodeBuilderValueStep<T> serializer(@Nullable Serializer<T> serializer);
}