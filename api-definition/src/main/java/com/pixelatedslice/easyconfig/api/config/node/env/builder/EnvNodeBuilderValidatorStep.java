package com.pixelatedslice.easyconfig.api.config.node.env.builder;

import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("unused")
@NullMarked
public interface EnvNodeBuilderValidatorStep<T> extends BuilderStep, EnvNodeBuilderFinalStep<T> {
    EnvNodeBuilderFinalStep<T> validator(Validator<@Nullable T> validator);
}