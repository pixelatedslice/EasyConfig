package com.pixelatedslice.easyconfig.api.config.node.value.builder;

import com.pixelatedslice.easyconfig.api.config.node.builder.GenericNodeBuilder;
import org.jspecify.annotations.NullMarked;

@FunctionalInterface
@NullMarked
public interface ValueNodeBuilder<T> extends GenericNodeBuilder<ValueNodeBuilderParentStep<T>> {
}