package com.pixelatedslice.easyconfig.api.config.node.factory.builder;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@NullMarked
public interface FactoryNodeBuilder {
    @NullMarked
    interface NodeTypeStep extends FactoryNodeBuilder {
        <T> FactoryNodeBuilderKeySteps.Value<T> value();

        <T> FactoryNodeBuilderKeySteps.Env<T> env();

        FactoryNodeBuilderKeySteps.Container container();

        FactoryNodeBuilderKeySteps.Collection collection();
    }

    @NullMarked
    interface KeyStep<Next extends FactoryNodeBuilder> extends FactoryNodeBuilder {
        Next key(String key);
    }

    @NullMarked
    interface BuildStep<NodeType extends Node> extends FactoryNodeBuilder {
        NodeType build();
    }
}
