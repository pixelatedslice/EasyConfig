package com.pixelatedslice.easyconfig.impl.config.node.factory;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilder;
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

    protected void internalChildren(
            Stream<? extends FactoryNodeBuilder.@Nullable BuildStep<?>> nodeStream) {
        final var builtNodes = nodeStream
                .filter((FactoryNodeBuilder.@Nullable BuildStep<?> buildStep) -> buildStep != null)
                .map(FactoryNodeBuilder.BuildStep::build);

        this.internalBuiltChildren(builtNodes);
    }

    protected void internalBuiltChildren(
            Stream<? extends @Nullable Node> nodeStream) {
        final var internalBuilders = nodeStream
                .filter(Objects::nonNull)
                .map(this::requireAbstractNode)
                .map(AbstractNode::toBuilder)
                .map(this::requireInternalNodeBuilder)
                .collect(Collectors.toSet());

        this.children.addAll(internalBuilders);
    }

    private AbstractNode requireAbstractNode(@Nullable Node node) {
        if (node instanceof AbstractNode abstractNode) {
            return abstractNode;
        }

        final String actual = (node == null) ? "null" : node.getClass().getName();
        throw new IllegalArgumentException(
                "Expected an AbstractNode implementation, but got: " + actual);
    }

    private InternalNodeBuilder<?> requireInternalNodeBuilder(FactoryNodeBuilder.KeyStep<?> keyStep) {
        if (keyStep instanceof InternalNodeBuilder<?> internalNodeBuilder) {
            return internalNodeBuilder;
        }

        final String actual = (keyStep == null) ? "null" : keyStep.getClass().getName();
        throw new IllegalArgumentException(
                "Expected an InternalNodeBuilder, but got: " + actual);
    }
}
