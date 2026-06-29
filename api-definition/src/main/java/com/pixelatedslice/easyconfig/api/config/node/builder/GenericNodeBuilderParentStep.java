package com.pixelatedslice.easyconfig.api.config.node.builder;

import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import org.jspecify.annotations.NullMarked;

@FunctionalInterface
@NullMarked
public interface GenericNodeBuilderParentStep<Next extends BuilderStep> extends BuilderStep {
    Next parent(ContainerNode parent);
}