package com.pixelatedslice.easyconfig.impl.config.node.factory.value;

import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.FactoryNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.container.builder.ContainerNodeOriginalBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.factory.AbstractFactoryNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

@NullMarked
public class FactoryContainerNodeBuilder
        extends
        AbstractFactoryNodeBuilder<ContainerNode, FactoryNodeBuilder.KeyStep.Container,
                FactoryNodeBuilder.GroupStep.Container>
        implements FactoryNodeBuilder.GroupStep.Container, FactoryNodeBuilder.GroupStep.Container.Buildable {

    private final ContainerNodeOriginalBuilder builder =
            new ContainerNodeOriginalBuilder()
                    .key(Objects.requireNonNull(this.key))
                    .parent(this.parent);

    @Override
    public ContainerNode build() {
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
