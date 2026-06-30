package com.pixelatedslice.easyconfig.api.config.node.builder.builder;

import com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("unused")
@NullMarked
public interface FactoryNodeBuilderGroupStep<Next extends FactoryNodeBuilder, NodeType extends Node> extends
        FactoryNodeBuilder {
    Next children(@Nullable BuildStep<?> @Nullable ... nodes);

    Next children(java.util.@Nullable Collection<? extends @Nullable BuildStep<?>> nodes);

    @NullMarked
    interface Original<NodeType extends Node>
            extends FactoryNodeBuilder, FactoryNodeBuilderGroupStep<Buildable<NodeType>, NodeType> {
    }

    @NullMarked
    interface Buildable<NodeType extends Node>
            extends FactoryNodeBuilder, BuildStep<NodeType> {
    }

    @NullMarked
    interface Container
            extends FactoryNodeBuilder, FactoryNodeBuilderGroupStep<Container.Buildable, ContainerNode> {

        @NullMarked
        interface Buildable extends FactoryNodeBuilderGroupStep.Buildable<ContainerNode> {
        }
    }

    @NullMarked
    interface Collection
            extends FactoryNodeBuilder, FactoryNodeBuilderGroupStep<Collection.Buildable, CollectionNode> {

        @NullMarked
        interface Buildable extends FactoryNodeBuilderGroupStep.Buildable<CollectionNode> {
        }
    }
}