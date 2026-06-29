package com.pixelatedslice.easyconfig.api.config.node.collection.builder;

import com.pixelatedslice.easyconfig.api.config.node.builder.GenericNodeBuilderHandler;
import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@NullMarked
public interface CollectionNodeBuilderHandler extends
        GenericNodeBuilderHandler<CollectionNodeBuilderParentStep, CollectionNodeBuilderChildrenStep>,
        CollectionNodeBuilderParentStep, CollectionNodeBuilderChildrenStep, CollectionNodeBuilderFinalStep {
}