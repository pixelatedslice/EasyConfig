package com.pixelatedslice.easyconfig.impl.config.node.container;

import com.pixelatedslice.easyconfig.api.config.node.container.EditableContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.utils.DistinctByGatherer;
import org.jspecify.annotations.NullMarked;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.LinkedTransferQueue;
import java.util.function.Function;
import java.util.stream.Stream;

@NullMarked
public class ContainerNodeEditableImpl implements EditableContainerNode {

    private final ContainerNodeImpl target;
    private final LinkedTransferQueue<Node> removingNode = new LinkedTransferQueue<>();
    private final LinkedTransferQueue<Node> addingNodes = new LinkedTransferQueue<>();

    ContainerNodeEditableImpl(ContainerNodeImpl target) {
        this.target = Objects.requireNonNull(target);
    }

    @Override
    public EditableContainerNode addNodes(Collection<? extends Node> nodes) {
        this.addingNodes.addAll(nodes);
        return this;
    }

    @Override
    public EditableContainerNode setNodes(Collection<? extends Node> nodes) {
        this.clearNodes();
        return this.addNodes(nodes.toArray(Node[]::new));
    }

    @Override
    public EditableContainerNode removeNodes(Node... nodes) {
        return this.removeNodes(Stream.of(nodes).map(Node::key), t -> t);
    }

    @Override
    public EditableContainerNode removeNodes(Collection<? extends Node> nodes) {
        return this.removeNodes(nodes.stream().map(Node::key), t -> t);
    }

    @Override
    public EditableContainerNode removeNodes(String... keys) {
        return this.removeNodes(Stream.of(keys), t -> t);
    }

    @Override
    public EditableContainerNode clearNodes() {
        this.removingNode.addAll(this.target.children());
        return this;
    }

    private EditableContainerNode removeNodes(Stream<String> keys, Function<Stream<Node>, Stream<Node>> function) {
        final var targetChildren = this.target.children().stream().toList();
        final var removingFilter = keys
                .map(key -> targetChildren.stream().filter(n -> n.key().equals(key)).findFirst())
                .filter(Optional::isPresent)
                .map(Optional::orElseThrow);
        function.apply(removingFilter);
        this.removingNode.addAll(removingFilter.toList());
        return this;
    }

    @Override
    public void close() {
        final var distinctToRemove = this.removingNode.stream().gather(new DistinctByGatherer<>(Node::key)).toList();
        this.target.removeNodes(distinctToRemove);

        for (var node : this.addingNodes) {
            if (!(node instanceof AbstractNode)) {
                throw new IllegalArgumentException("Cannot add " + node.getClass().getName() + " to EasyConfig node");
            }
        }

        //TODO to builder -> swap parent to this
        final var distinctToAdd = this.addingNodes
                .stream()
                .map(n -> (AbstractNode) n)
                .gather(new DistinctByGatherer<>(Node::key))
                .toList();
        this.target.internalAppendChildren(distinctToAdd);
    }
}
