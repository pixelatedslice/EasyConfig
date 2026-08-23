package com.pixelatedslice.easyconfig.impl.config.node.factory;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.NodeBuilder;
import com.pixelatedslice.easyconfig.api.validator.null_policy.NullPolicy;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractGroupNodeBuilder {
    protected final List<InternalNodeBuilder<?>> children = new CopyOnWriteArrayList<>();

    protected void internalChildren(NullPolicy nullPolicy,
            Stream<? extends NodeBuilder.@Nullable BuildStep<?>> nodeStream) {
        final var internalBuilders = nodeStream
                .filter(Objects::nonNull)
                .map(builder -> this.requireInternalNodeBuilder(nullPolicy, builder))
                .collect(Collectors.toSet());

        this.children.addAll(internalBuilders);
    }

    protected void internalBuiltChildren(NullPolicy nullPolicy, Stream<? extends @Nullable Node> nodeStream) {
        final var internalBuilders = nodeStream
                .filter(Objects::nonNull)
                .map(node -> this.requireAbstractNode(nullPolicy, node))
                .map(AbstractNode::toBuilder)
                .map(builder -> this.requireInternalNodeBuilder(nullPolicy, builder))
                .collect(Collectors.toSet());

        this.children.addAll(internalBuilders);
    }

    private AbstractNode requireAbstractNode(NullPolicy nullPolicy, @Nullable Node node) {
        nullPolicy.handle(node);

        if (node instanceof AbstractNode abstractNode) {
            return abstractNode;
        }

        final String actual = (node == null) ? "null" : node.getClass().getName();
        throw new IllegalArgumentException("Expected an AbstractNode implementation, but got: " + actual);
    }

    private InternalNodeBuilder<?> requireInternalNodeBuilder(NullPolicy nullPolicy, NodeBuilder step) {
        nullPolicy.handle(step);

        if (step instanceof InternalNodeBuilder<?> internalNodeBuilder) {
            return internalNodeBuilder;
        }

        final String actual = (step == null) ? "null" : step.getClass().getName();
        throw new IllegalArgumentException("Expected an InternalNodeBuilder, but got: " + actual);
    }
}
