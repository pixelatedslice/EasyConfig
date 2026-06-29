package com.pixelatedslice.easyconfig.api.config.node.container.builder;

import com.pixelatedslice.easyconfig.api.config.node.builder.GenericNodeBuilderHandler;
import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@NullMarked
public interface ContainerNodeBuilderHandler extends
        GenericNodeBuilderHandler<ContainerNodeBuilderParentStep, ContainerNodeBuilderChildrenStep>,
        ContainerNodeBuilder,
        ContainerNodeBuilderParentStep, ContainerNodeBuilderChildrenStep, ContainerNodeBuilderFinalStep {
}