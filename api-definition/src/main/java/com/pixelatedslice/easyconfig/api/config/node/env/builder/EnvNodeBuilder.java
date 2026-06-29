package com.pixelatedslice.easyconfig.api.config.node.env.builder;

import com.pixelatedslice.easyconfig.api.config.node.builder.GenericNodeBuilder;
import org.jspecify.annotations.NullMarked;

@FunctionalInterface
@NullMarked
public interface EnvNodeBuilder<T> extends GenericNodeBuilder<EnvNodeBuilderTypeStep<T>> {
}
