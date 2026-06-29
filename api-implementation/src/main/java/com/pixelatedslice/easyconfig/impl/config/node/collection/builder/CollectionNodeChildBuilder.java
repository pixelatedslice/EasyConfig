package com.pixelatedslice.easyconfig.impl.config.node.collection.builder;

import com.pixelatedslice.easyconfig.api.config.node.builder.NodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.container.builder.ContainerNodeChildBuilder;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class CollectionNodeChildBuilder<Previous extends InternalNodeBuilder<?>>
        extends AbstractCollectionNodeBuilder<CollectionNodeChildBuilder<Previous>>
        implements NodeBuilder.CollectionStep.Child<Previous> {

    private final Previous previous;

    public CollectionNodeChildBuilder(InternalNodeBuilder<?> from, Previous previous) {
        super(from);
        this.previous = previous;
    }

    @Override
    public ContainerSafeStep.Child<Child<Previous>> append() {
        final ContainerNodeChildBuilder<CollectionNodeChildBuilder<Previous>> builder = new ContainerNodeChildBuilder<>(
                this,
                "index_" + this.children().size());
        //noinspection unchecked
        return (ContainerSafeStep.Child<Child<Previous>>) (Object) builder;

    }

    @Override
    public Previous complete() {
        this.previous.appendChild(this);
        return this.previous;
    }
}
