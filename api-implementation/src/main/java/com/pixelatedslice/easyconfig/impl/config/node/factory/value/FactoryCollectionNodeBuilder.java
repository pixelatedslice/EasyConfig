package com.pixelatedslice.easyconfig.impl.config.node.factory.value;

import com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderGroupStep;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.impl.config.node.collection.builder.CollectionNodeOriginalBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.factory.AbstractFactoryNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

@NullMarked
public class FactoryCollectionNodeBuilder
        extends
        AbstractFactoryNodeBuilder<CollectionNode, FactoryNodeBuilderKeySteps.Collection,
                FactoryNodeBuilderGroupStep.Collection>
        implements FactoryNodeBuilderGroupStep.Collection, FactoryNodeBuilderGroupStep.Collection.Buildable {

    private final CollectionNodeOriginalBuilder builder =
            new CollectionNodeOriginalBuilder(Objects.requireNonNull(this.key))
                    .parent(this.parent);

    @Override
    public CollectionNode build() {
        Objects.requireNonNull(this.key);
        return this.builder.build();
    }

    @Override
    public Buildable children(@Nullable FactoryNodeBuilder @Nullable ... nodes) {
        if (nodes == null) {
            return this;
        }

        for (FactoryNodeBuilder node : nodes) {
            this.builder.appendChild((ParentStep<?>) node);
        }

        return this;
    }

    @Override
    public Buildable children(java.util.@Nullable Collection<FactoryNodeBuilder> nodes) {
        return this;
    }
}
