package com.pixelatedslice.easyconfig.impl.config.node.container;

import com.google.common.collect.ImmutableList;
import com.pixelatedslice.easyconfig.api.config.node.ReturnedNode;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.container.EditableContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.container.builder.ContainerNodeBuilder;
import org.jspecify.annotations.NullMarked;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

@NullMarked
public class ContainerNodeImpl extends AbstractNode implements ContainerNode {
    private final Collection<Node> immediateChildren = new LinkedBlockingQueue<>();
    private String toString = this.generateToString();

    public ContainerNodeImpl(InternalNodeBuilder<?> builder) {
        super(builder);
    }

    @Override
    public boolean isRootNode() {
        return this.parent == null;
    }

    @Override
    protected synchronized void internalAppendChild(AbstractNode node) {
        this.immediateChildren.add(node);
        this.toString = this.generateToString();
    }

    protected synchronized void internalAppendChildren(Collection<AbstractNode> node) {
        this.immediateChildren.addAll(node);
        this.toString = this.generateToString();
    }

    @Override
    public ImmutableList<Node> children() {
        return ImmutableList.<Node>builder()
                .addAll(this.immediateChildren).build();
    }

    @Override
    public ReturnedNode node(String... path) {
        return travel(this, path);
    }


    synchronized void removeNodes(Collection<Node> nodes) {
        this.immediateChildren.removeAll(nodes);
    }

    @Override
    public EditableContainerNode editable() {
        return new ContainerNodeEditableImpl(this);
    }

    @Override
    public ContainerNodeBuilder toBuilder() {
        return new ContainerNodeBuilder(this.key())
                .parent(this.parent)
                .config(this.attached);
    }

    private String generateToString() {
        final var joiner = new java.util.StringJoiner(", ", "[", "]");
        for (final var child : this.immediateChildren) {
            joiner.add(child.key());
        }

        return "ContainerNodeImpl{" +
                "key='" + this.key() + '\'' +
                ", childCount=" + this.immediateChildren.size() +
                ", children=" + joiner +
                ", fullPath=" + String.join(",", this.fullPath()) +
                '}';
    }

    @Override
    public String toString() {
        return this.toString;
    }
}
