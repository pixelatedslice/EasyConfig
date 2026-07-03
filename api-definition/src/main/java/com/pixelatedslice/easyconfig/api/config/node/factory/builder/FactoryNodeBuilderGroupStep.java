package com.pixelatedslice.easyconfig.api.config.node.factory.builder;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("unused")
@NullMarked
public interface FactoryNodeBuilderGroupStep<Next extends FactoryNodeBuilder, NodeType extends Node> extends
        FactoryNodeBuilder, FactoryNodeBuilder.BuildStep<NodeType> {
    Next builtChildren(@Nullable Node @Nullable ... nodes);

    Next children(@Nullable BuildStep<?> @Nullable ... nodes);

    Next children(java.util.@Nullable Collection<? extends @Nullable BuildStep<?>> nodes);

    Next builtChildren(java.util.@Nullable Collection<? extends Node> nodes);

    @NullMarked
    interface Container
            extends FactoryNodeBuilderGroupStep<Container, ContainerNode> {
    }

    @NullMarked
    interface Collection
            extends FactoryNodeBuilderGroupStep<Collection, CollectionNode> {
    }
}