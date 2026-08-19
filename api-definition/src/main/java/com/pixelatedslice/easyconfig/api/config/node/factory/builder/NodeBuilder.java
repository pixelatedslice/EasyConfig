package com.pixelatedslice.easyconfig.api.config.node.factory.builder;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@NullMarked
public interface NodeBuilder {
    @NullMarked
    interface NodeTypeStep extends NodeBuilder {
        <T> NodeBuilderKeySteps.Value<T> value();

        <T> NodeBuilderKeySteps.Env<T> env();

        NodeBuilderKeySteps.Container container();

        NodeBuilderKeySteps.Collection collection();
    }

    @NullMarked
    interface KeyStep<Next extends NodeBuilder> extends NodeBuilder {
        Next key(String key);
    }

    @NullMarked
    interface BuildStep<NodeType extends Node> extends NodeBuilder {
        NodeType build();
    }
}
