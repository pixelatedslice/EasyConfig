package com.pixelatedslice.easyconfig.api.config.node.collection.builder;

import com.pixelatedslice.easyconfig.api.config.node.builder.GenericNodeBuilderParentStep;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface CollectionNodeBuilderParentStep
        extends GenericNodeBuilderParentStep<CollectionNodeBuilderChildrenStep>,
        CollectionNodeBuilderChildrenStep {
}