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
    Next builtChildren(@Nullable Node @Nullable ... nodes);

    Next children(@Nullable BuildStep<?> @Nullable ... nodes);

    Next children(java.util.@Nullable Collection<? extends @Nullable BuildStep<?>> nodes);

    Next builtChildren(java.util.@Nullable Collection<? extends Node> nodes);

    @NullMarked
    interface Buildable<NestedBuildable extends Buildable<NestedBuildable, NodeType>, NodeType extends Node>
            extends FactoryNodeBuilderGroupStep<NestedBuildable, NodeType>, BuildStep<NodeType> {
    }

    @NullMarked
    interface Container
            extends FactoryNodeBuilder, FactoryNodeBuilderGroupStep<Container.Buildable, ContainerNode> {

        @NullMarked
        interface Buildable extends FactoryNodeBuilderGroupStep.Buildable<Container.Buildable, ContainerNode> {
        }
    }

    @NullMarked
    interface Collection
            extends FactoryNodeBuilder, FactoryNodeBuilderGroupStep<Collection.Buildable, CollectionNode> {

        @NullMarked
        interface Buildable
                extends Collection, FactoryNodeBuilderGroupStep.Buildable<Collection.Buildable, CollectionNode> {
        }
    }
}