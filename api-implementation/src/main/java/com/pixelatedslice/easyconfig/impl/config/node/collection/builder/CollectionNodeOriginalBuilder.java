package com.pixelatedslice.easyconfig.impl.config.node.collection.builder;

import com.pixelatedslice.easyconfig.api.config.node.builder.NodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.collection.CollectionNodeImpl;
import com.pixelatedslice.easyconfig.impl.config.node.container.builder.ContainerNodeChildBuilder;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class CollectionNodeOriginalBuilder extends AbstractCollectionNodeBuilder<CollectionNodeOriginalBuilder>
        implements NodeBuilder.CollectionStep.Original {

    public CollectionNodeOriginalBuilder(InternalNodeBuilder<?> from) {
        super(from);
    }

    public CollectionNodeOriginalBuilder(String key) {
        super(key);
    }

    @Override
    public ContainerSafeStep.Child<Original> append() {
        final ContainerNodeChildBuilder<CollectionNodeOriginalBuilder> builder = new ContainerNodeChildBuilder<>(this,
                "index_" + this.children().size());
        //noinspection unchecked
        return (ContainerSafeStep.Child<Original>) (Object) builder;
    }

    @Override
    public CollectionNodeImpl build() {
        final var result = super.build();
        this.buildChildren(result);
        return result;
    }
}
