package com.pixelatedslice.easyconfig.api.config.node.collection.builder;

import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import org.jspecify.annotations.NullMarked;

@FunctionalInterface
@NullMarked
public interface CollectionNodeBuilderFinalStep extends BuilderStep {
    ContainerNode build();
}