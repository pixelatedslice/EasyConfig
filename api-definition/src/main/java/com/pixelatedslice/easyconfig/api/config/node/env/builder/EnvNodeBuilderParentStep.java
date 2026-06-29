package com.pixelatedslice.easyconfig.api.config.node.env.builder;

import com.pixelatedslice.easyconfig.api.config.node.builder.GenericNodeBuilderParentStep;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface EnvNodeBuilderParentStep<T>
        extends GenericNodeBuilderParentStep<EnvNodeBuilderTypeStep<T>>, EnvNodeBuilderTypeStep<T> {
}