package com.pixelatedslice.easyconfig.api.config.node.env.builder;

import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("unused")
@FunctionalInterface
@NullMarked
public interface EnvNodeBuilderEnvStep<T> extends BuilderStep {
    @SuppressWarnings("UnusedReturnValue")
    EnvNodeBuilderSerializerStep<T> environmentVariable(@Nullable String environmentVariable);
}
