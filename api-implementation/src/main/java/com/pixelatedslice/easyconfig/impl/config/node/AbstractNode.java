package com.pixelatedslice.easyconfig.impl.config.node;

import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.ReturnedNode;
import com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import com.pixelatedslice.easyconfig.impl.config.ConfigStructureImpl;
import com.pixelatedslice.easyconfig.impl.config.node.container.builder.ContainerNodeBuilder;
import com.pixelatedslice.easyconfig.impl.utils.DeepRecursiveGatherer;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

@NullMarked
public abstract class AbstractNode implements Node {

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

    public static Stream<AbstractNode> walk(AbstractNode node) {
        return Stream.of(node).gather(new DeepRecursiveGatherer<>(AbstractNode::internalChildren));
    }

    private static ReturnedNode travel(Node node, int index, String... path) {
        Objects.requireNonNull(node);
        if (path.length == index) {
            return new ReturnKnownNodeImpl(node);
        }
        final var targetKey = path[index];
        final var opChildNode = children(node)
                .filter((Node childNode) -> childNode.key().equals(targetKey))
                .findFirst();
        return opChildNode
                .map((Node value) -> travel(value, index + 1, path))
                .orElseGet(() -> new ReturnKnownNodeImpl(null));
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

    public abstract Collection<AbstractNode> internalChildren();

    protected abstract void internalAppendChild(AbstractNode node);

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
        var target = this;
        if (!target.key().isEmpty()) {
            var newBuilder = new ContainerNodeBuilder("");
            newBuilder.appendChild((InternalNodeBuilder<?>) target.toBuilder());
            target = newBuilder.build();
        }
        return new ConfigStructureImpl(target, SerializerRegistry.global().createChild());
    }

    public @Nullable Config config() {
        if (this.attached != null) {
            return this.attached;
        }
        return (this.parent == null) ? null : this.parent.config();
    }
}
