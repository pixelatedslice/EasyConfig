package com.pixelatedslice.easyconfig.impl.config.node.collection;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.ReturnedNode;
import com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.ReturnKnownNodeImpl;
import com.pixelatedslice.easyconfig.impl.config.node.collection.builder.CollectionNodeBuilder;
import org.jspecify.annotations.NullMarked;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

@NullMarked
public class CollectionNodeImpl extends AbstractNode implements CollectionNode {
    private final List<AbstractNode> children = new CopyOnWriteArrayList<>();

    public CollectionNodeImpl(InternalNodeBuilder<?> builder) {
        super(builder);
    }

    @Override
    public Collection<AbstractNode> internalChildren() {
        return this.children;
    }

    @Override
    public ImmutableCollection<ReturnedNode> nodes() {
        return ImmutableList.copyOf(this.children.stream().map(ReturnKnownNodeImpl::new).toList());
    }

    @Override
    public Stream<ReturnedNode> stream() {
        return this.children.stream().map(ReturnKnownNodeImpl::new);
    }

    @Override
    public ReturnedNode atIndex(int index) {
        final Node child = ((index < 0) || (index >= this.children.size())) ? null : this.children.get(index);

        return new ReturnKnownNodeImpl(child);
    }

    @Override
    protected void internalAppendChild(AbstractNode node) {
        this.children.add(node);
    }

    @Override
    public CollectionNodeBuilder toBuilder() {
        final var builder = new CollectionNodeBuilder(this.key()).parent(this.parent).config(this.attached);
        this.children
                .stream()
                .map(AbstractNode::toBuilder)
                .map(keyStep -> (InternalNodeBuilder<?>) keyStep)
                .forEach(builder::appendChild);
        return builder;
    }

    @Override
    public String toString() {
        final var joiner = new java.util.StringJoiner(", ", "[", "]");
        for (final var child : this.children) {
            joiner.add(child.key());
        }

        return "CollectionNodeImpl{"
               + "key='"
               + this.key()
               + '\''
               + ", childCount="
               + this.children.size()
               + ", children="
               + joiner
               + ", fullPath="
               + String.join(",", this.fullPath())
               + '}';
    }
}
