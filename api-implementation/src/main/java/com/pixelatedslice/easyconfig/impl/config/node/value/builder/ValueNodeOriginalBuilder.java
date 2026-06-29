package com.pixelatedslice.easyconfig.impl.config.node.value.builder;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.NodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.env.builder.OriginalEnvNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.value.ValueNodeImpl;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class ValueNodeOriginalBuilder<T> extends AbstractValueNodeBuilder<ValueNodeOriginalBuilder<T>, T>
        implements NodeBuilder.ValueFinalStep.Original<T>, NodeBuilder.ValueSafeStep.Original<T> {

    public ValueNodeOriginalBuilder(TypeToken<T> token, String key) {
        super(token, key);
    }

    @Override
    public ValueNodeImpl<T> build() {
        return new ValueNodeImpl<>(this);
    }

    @Override
    public EnvAdapterStep.Original<T> env(String env) {
        return new OriginalEnvNodeBuilder<>(this, env);
    }
}
