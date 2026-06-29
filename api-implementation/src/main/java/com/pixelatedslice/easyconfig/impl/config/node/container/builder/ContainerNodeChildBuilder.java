package com.pixelatedslice.easyconfig.impl.config.node.container.builder;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.builder.NodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.collection.builder.CollectionNodeChildBuilder;
import org.jspecify.annotations.NullMarked;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

@NullMarked
public class ContainerNodeChildBuilder<Previous extends InternalNodeBuilder<?>>
        extends AbstractContainerNodeBuilder<ContainerNodeChildBuilder<Previous>>
        implements NodeBuilder.ContainerFinalStep.Child<Previous> {

    private final Previous previous;
    private final Collection<InternalNodeBuilder<?>> children = new LinkedList<>();

    public ContainerNodeChildBuilder(Previous previous, String key) {
        this.key = key;
        this.previous = previous;
    }

    @Override
    public Child<Child<Previous>> append(String key) {
        final ContainerNodeChildBuilder<ContainerNodeChildBuilder<Previous>> builder = new ContainerNodeChildBuilder<>(
                this,
                key);
        //noinspection unchecked
        return (Child<Child<Previous>>) (Object) builder;
    }

    @Override
    public CollectionStep.Child<Previous> collection() {
        return new CollectionNodeChildBuilder<>(this, this.previous);
    }

    @Override
    public Previous complete() {
        this.previous.appendChild(this);
        return this.previous;
    }

    @Override
    public <T> ValueNodeChildBuilder<T, Previous> of(TypeToken<T> token) {
        return new ValueNodeChildBuilder<>(token, Objects.requireNonNull(this.key()), this.previous);
    }

    @Override
    public Collection<InternalNodeBuilder<?>> children() {
        return Collections.unmodifiableCollection(this.children);
    }

    @Override
    public void appendChild(InternalNodeBuilder<?> builder) {
        this.children.add(builder);
    }
}
