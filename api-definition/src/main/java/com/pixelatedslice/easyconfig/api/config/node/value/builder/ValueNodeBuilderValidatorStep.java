package com.pixelatedslice.easyconfig.api.config.node.value.builder;

import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public interface ValueNodeBuilderValidatorStep<T> extends BuilderStep, ValueNodeBuilderFinalStep<T> {
    ValueNodeBuilderFinalStep<T> validator(Validator<@Nullable T> validator);
}