package com.pixelatedslice.easyconfig.api.config.node.factory.builder;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.validator.null_policy.NullPolicy;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@SuppressWarnings("unused")
@NullMarked
public interface FactoryNodeBuilderGroupStep<Next extends FactoryNodeBuilder, NodeType extends Node> extends
        FactoryNodeBuilder, FactoryNodeBuilder.BuildStep<NodeType> {
    Next children(NullPolicy nullPolicy, @Nullable BuildStep<?> @Nullable ... nodes);

    default Next children(@Nullable BuildStep<?> @Nullable ... nodes) {
        return this.children(NullPolicy.THROW, nodes);
    }

    Next children(NullPolicy nullPolicy, java.util.@Nullable Collection<? extends @Nullable BuildStep<?>> nodes);

    default Next children(java.util.@Nullable Collection<? extends @Nullable BuildStep<?>> nodes) {
        return this.children(NullPolicy.THROW, nodes);
    }

    Next builtChildren(NullPolicy nullPolicy, @Nullable Node @Nullable ... nodes);

    default Next builtChildren(@Nullable Node @Nullable ... nodes) {
        return this.builtChildren(NullPolicy.THROW, nodes);
    }

    Next builtChildren(NullPolicy nullPolicy, java.util.@Nullable Collection<? extends Node> nodes);

    default Next builtChildren(java.util.@Nullable Collection<? extends Node> nodes) {
        return this.builtChildren(NullPolicy.THROW, nodes);
    }

    @NullMarked
    interface Container
            extends FactoryNodeBuilderGroupStep<Container, ContainerNode> {
    }

    @NullMarked
    interface Collection
            extends FactoryNodeBuilderGroupStep<Collection, CollectionNode> {
    }
}