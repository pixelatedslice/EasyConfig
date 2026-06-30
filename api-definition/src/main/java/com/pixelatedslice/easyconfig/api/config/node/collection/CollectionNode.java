package com.pixelatedslice.easyconfig.api.config.node.collection;

import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CheckReturnValue;
import com.pixelatedslice.easyconfig.api.config.node.NodeType;
import com.pixelatedslice.easyconfig.api.config.node.ReturnedNode;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.for_impl.ForImplNode;
import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import org.jspecify.annotations.NullMarked;

import java.util.stream.Stream;

@NullMarked
public non-sealed interface CollectionNode extends Node, ForImplNode {
    @Override
    default NodeType nodeType() {
        return NodeType.COLLECTION_NODE;
    }


    @CheckReturnValue
    ImmutableCollection<ReturnedNode> nodes();


    @CheckReturnValue
    Stream<ReturnedNode> stream();


    @CheckReturnValue
    ReturnedNode atIndex(int index);

    @Override
    FactoryNodeBuilderKeySteps.Collection toBuilder();
}
