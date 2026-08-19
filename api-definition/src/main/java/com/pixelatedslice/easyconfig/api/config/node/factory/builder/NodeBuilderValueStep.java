package com.pixelatedslice.easyconfig.api.config.node.factory.builder;

import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("unused")
@NullMarked
public interface NodeBuilderValueStep<T>
        extends NodeBuilder, NodeBuilder.BuildStep<ValueNode<T>> {
    NodeBuilderValueStep<T> value(@Nullable T value);

    NodeBuilderValueStep<T> defaultValue(@Nullable T defaultValue);

    NodeBuilderValueStep<T> validator(@Nullable Validator<T> validator);

    NodeBuilderValueStep<T> serializer(@Nullable Serializer<T> serializer);
}