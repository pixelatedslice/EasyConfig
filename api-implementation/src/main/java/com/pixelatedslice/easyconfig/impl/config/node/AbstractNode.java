package com.pixelatedslice.easyconfig.impl.config.node;

import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import com.pixelatedslice.easyconfig.api.config.node.ReturnedNode;
import com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.for_impl.ForImplNode;
import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import com.pixelatedslice.easyconfig.impl.config.ConfigStructureImpl;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.stream.Stream;

@NullMarked
public abstract class AbstractNode implements ForImplNode {

    protected final @Nullable AbstractNode parent;
    protected final @Nullable Config attached;
    private final String key;

    public AbstractNode(InternalNodeBuilder<?> builder) {
        this.key = Objects.requireNonNull(builder.key());
        this.parent = builder.parent();
        this.attached = builder.config();
    }

    public static ReturnedNode travel(Node node, String... path) {
        return travel(node, 0, path);
    }

    private static ReturnedNode travel(Node node, int index, String... path) {
        Objects.requireNonNull(node);
        if (path.length == index) {
            return new ReturnKnownNodeImpl(node);
        }
        final var targetKey = path[index];
        final var opChildNode = children(node).filter(n -> n.key().equals(targetKey)).findFirst();
        return opChildNode.map(value -> travel(value, index + 1, path)).orElseGet(() -> new ReturnKnownNodeImpl(null));
    }

    private static Stream<Node> children(Node node) {
        if (node instanceof ContainerNode container) {
            return container.children().stream();
        }
        return (node instanceof CollectionNode collection) ? collection
                .nodes()
                .stream()
                .map(t -> t.plainNode().orElseThrow()) : Stream.empty();
    }

    protected abstract void internalAppendChild(AbstractNode node);

    @Override
    public abstract InternalNodeBuilder<?> toBuilder();

    @Override
    public String key() {
        return this.key;
    }

    @Override
    public ReturnedNode parent() {
        return new ReturnKnownNodeImpl(this.parent);
    }

    @Override
    public ConfigStructure toStructure() {
        return new ConfigStructureImpl(this.toBuilder().build());
    }

    public @Nullable Config config() {
        if (this.attached != null) {
            return this.attached;
        }
        return (this.parent == null) ? null : this.parent.config();
    }
}
