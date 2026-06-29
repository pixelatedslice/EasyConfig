package com.pixelatedslice.easyconfig.api.config.node.value.builder;

import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("unused")
@NullMarked
public interface ValueNodeBuilderValueStep<T> extends BuilderStep, ValueNodeBuilderSerializerStep<T> {
    ValueNodeBuilderValueStep<T> value(@Nullable T value);

    ValueNodeBuilderValueStep<T> defaultValue(@Nullable T defaultValue);
}