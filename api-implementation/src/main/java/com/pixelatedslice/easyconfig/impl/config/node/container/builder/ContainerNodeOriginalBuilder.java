package com.pixelatedslice.easyconfig.impl.config.node.container.builder;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.builder.NodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.collection.builder.CollectionNodeOriginalBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.container.ContainerNodeImpl;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
public class ContainerNodeOriginalBuilder extends AbstractContainerNodeBuilder<ContainerNodeOriginalBuilder>
        implements NodeBuilder.ContainerFinalStep.Original, NodeBuilder.FirstStep {
    @Override
    public ContainerNodeImpl build() {
        final var built = new ContainerNodeImpl(this);
        this.buildChildren(built);
        return built;
    }

    @Override
    public ContainerNodeOriginalBuilder key(String key) {
        this.key = key;
        return this;
    }

    @Override
    public CollectionStep.Original collection() {
        return new CollectionNodeOriginalBuilder(this);
    }

    @Override
    public Child<Original> append(String key) {
        //noinspection RedundantCast,unchecked
        return (Child<Original>) (Object) new ContainerNodeChildBuilder<>(this, key);
    }

    @Override
    public <T> ValueNodeOriginalBuilder<T> of(TypeToken<T> token) {
        return new ValueNodeOriginalBuilder<>(token, Objects.requireNonNull(this.key))
                .config(this.config)
                .parent(this.parent);
    }
}
