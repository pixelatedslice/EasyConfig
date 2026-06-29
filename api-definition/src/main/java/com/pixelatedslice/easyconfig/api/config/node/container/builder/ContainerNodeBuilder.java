package com.pixelatedslice.easyconfig.api.config.node.container.builder;

import com.pixelatedslice.easyconfig.api.config.node.builder.GenericNodeBuilder;
import org.jspecify.annotations.NullMarked;

@FunctionalInterface
@NullMarked
public interface ContainerNodeBuilder extends GenericNodeBuilder<ContainerNodeBuilderParentStep> {
}
