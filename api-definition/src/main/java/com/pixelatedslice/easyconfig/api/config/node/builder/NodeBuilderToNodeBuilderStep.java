package com.pixelatedslice.easyconfig.api.config.node.builder;

import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import com.pixelatedslice.easyconfig.api.config.node.container.builder.ContainerNodeBuilderChildrenStep;
import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@NullMarked
public interface NodeBuilderToNodeBuilderStep extends BuilderStep {
    <T> ValueNodeBuilderTypeStep<T> valueNode();

    ContainerNodeBuilderChildrenStep containerNode();
}
