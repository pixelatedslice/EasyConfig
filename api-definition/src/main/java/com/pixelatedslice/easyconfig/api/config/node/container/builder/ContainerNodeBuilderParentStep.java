package com.pixelatedslice.easyconfig.api.config.node.container.builder;

import com.pixelatedslice.easyconfig.api.config.node.builder.GenericNodeBuilderParentStep;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface ContainerNodeBuilderParentStep
        extends GenericNodeBuilderParentStep<ContainerNodeBuilderChildrenStep>, ContainerNodeBuilderChildrenStep {
}
