package com.pixelatedslice.easyconfig.impl.config.node.env.builder;

import com.pixelatedslice.easyconfig.api.config.node.NodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.value.builder.AbstractValueNodeBuilder;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class ChildEnvNodeBuilder<T, Previous extends InternalNodeBuilder<?>>
        extends AbstractEnvNodeBuilderImpl<T, ChildEnvNodeBuilder<T, Previous>>
        implements NodeBuilder.EnvAdapterStep.Child<T, Previous>, NodeBuilder.EnvFinalStep.Child<T, Previous> {

    private final Previous previous;

    public ChildEnvNodeBuilder(AbstractValueNodeBuilder<?, T> builder, Previous previous, String envKey) {
        super(builder, envKey);
        this.previous = previous;

    }

    @Override
    public Previous complete() {
        return this.previous;
    }
}
