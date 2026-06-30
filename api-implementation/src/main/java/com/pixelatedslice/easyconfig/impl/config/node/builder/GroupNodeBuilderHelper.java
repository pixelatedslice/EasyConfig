package com.pixelatedslice.easyconfig.impl.config.node.builder;

import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class GroupNodeBuilderHelper {
    private GroupNodeBuilderHelper() {
    }

    public static void children(
            Stream<? extends FactoryNodeBuilder.@Nullable BuildStep<?>> nodeStream,
            Collection<? super InternalNodeBuilder<?>> children) {
        final var builtNodes = nodeStream
                .filter((FactoryNodeBuilder.@Nullable BuildStep<?> buildStep) -> buildStep != null)
                .map(FactoryNodeBuilder.BuildStep::build);

        builtChildren(builtNodes, children);
    }

    public static void builtChildren(
            Stream<? extends @Nullable Node> nodeStream,
            Collection<? super InternalNodeBuilder<?>> children) {
        final var internalBuilders = nodeStream
                .filter((@Nullable Node node) -> node != null)
                .map(node -> (AbstractNode) node)
                .map(AbstractNode::toBuilder)
                .map(builder -> (InternalNodeBuilder<?>) builder)
                .collect(Collectors.toSet());

        children.addAll(internalBuilders);
    }
}
