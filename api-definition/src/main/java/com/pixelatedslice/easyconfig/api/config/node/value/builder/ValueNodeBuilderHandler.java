package com.pixelatedslice.easyconfig.api.config.node.value.builder;

import com.pixelatedslice.easyconfig.api.config.node.builder.GenericNodeBuilderHandler;
import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@NullMarked
public interface ValueNodeBuilderHandler<T>
        extends GenericNodeBuilderHandler<ValueNodeBuilderParentStep<T>, ValueNodeBuilderTypeStep<T>>,
        ValueNodeBuilder<T>,
        ValueNodeBuilderParentStep<T>, ValueNodeBuilderTypeStep<T>, ValueNodeBuilderValueStep<T>,
        ValueNodeBuilderSerializerStep<T>, ValueNodeBuilderValidatorStep<T>,
        ValueNodeBuilderFinalStep<T> {
}