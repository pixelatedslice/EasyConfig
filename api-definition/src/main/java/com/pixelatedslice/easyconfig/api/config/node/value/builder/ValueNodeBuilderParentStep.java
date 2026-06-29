package com.pixelatedslice.easyconfig.api.config.node.value.builder;

import com.pixelatedslice.easyconfig.api.config.node.builder.GenericNodeBuilderParentStep;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface ValueNodeBuilderParentStep<T>
        extends GenericNodeBuilderParentStep<ValueNodeBuilderTypeStep<T>>, ValueNodeBuilderTypeStep<T> {
}