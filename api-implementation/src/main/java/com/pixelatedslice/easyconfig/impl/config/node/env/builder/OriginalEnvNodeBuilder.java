package com.pixelatedslice.easyconfig.impl.config.node.env.builder;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.NodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.value.builder.AbstractValueNodeBuilder;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class OriginalEnvNodeBuilder<T> extends AbstractEnvNodeBuilderImpl<T, OriginalEnvNodeBuilder<T>>
        implements NodeBuilder.EnvAdapterStep.Original<T>, NodeBuilder.EnvFinalStep.Original<T> {

    public OriginalEnvNodeBuilder(AbstractValueNodeBuilder<?, T> builder, String envKey) {
        super(builder, envKey);
    }

    public OriginalEnvNodeBuilder(String key, TypeToken<T> type, String envKey) {
        super(key, type, envKey);
    }
}
