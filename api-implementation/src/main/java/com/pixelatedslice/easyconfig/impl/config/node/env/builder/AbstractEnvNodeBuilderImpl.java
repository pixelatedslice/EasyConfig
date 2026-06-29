package com.pixelatedslice.easyconfig.impl.config.node.env.builder;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.node.NodeBuilder;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.env.EnvNodeImpl;
import com.pixelatedslice.easyconfig.impl.config.node.value.builder.AbstractValueNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Function;

@NullMarked
public class AbstractEnvNodeBuilderImpl<T, Self extends AbstractEnvNodeBuilderImpl<T, Self>>
        implements InternalNodeBuilder<Self>, NodeBuilder.EnvAdapterStep<T>, NodeBuilder.EnvFinalStep<T> {

    private final String key;
    private final String envKey;
    private final TypeToken<T> typeToken;
    private @Nullable AbstractNode parent;
    private @Nullable Config config;
    private @Nullable Function<String, @Nullable T> adapter;
    private @Nullable Validator<T> validator;

    public AbstractEnvNodeBuilderImpl(AbstractValueNodeBuilder<?, T> builder, String envKey) {
        this.key = Objects.requireNonNull(builder.key());
        this.config = builder.config();
        this.parent = builder.parent();
        this.envKey = Objects.requireNonNull(envKey);
        this.typeToken = Objects.requireNonNull(builder.type());
    }

    public AbstractEnvNodeBuilderImpl(String key, TypeToken<T> typeToken, String envKey) {
        this.key = Objects.requireNonNull(key);
        this.envKey = Objects.requireNonNull(envKey);
        this.typeToken = Objects.requireNonNull(typeToken);
    }

    public Validator<T> validator() {
        return this.validator;
    }

    public @Nullable Function<String, T> adapter() {
        return this.adapter;
    }

    public TypeToken<T> type() {
        return this.typeToken;
    }

    public String envKey() {
        return this.envKey;
    }

    @Override
    public Self parent(@Nullable AbstractNode node) {
        this.parent = node;
        //noinspection unchecked
        return (Self) this;
    }

    @Override
    public @Nullable AbstractNode parent() {
        return this.parent;
    }

    @Override
    public Self config(@Nullable Config config) {
        this.config = config;
        //noinspection unchecked
        return (Self) this;
    }

    @Override
    public @Nullable Config config() {
        return this.config;
    }

    @Override
    public @Nullable String key() {
        return this.key;
    }

    @Override
    public Collection<InternalNodeBuilder<?>> children() {
        return Collections.emptyList();
    }

    @Override
    public void appendChild(InternalNodeBuilder<?> builder) {
        throw new IllegalArgumentException("Cannot append to Env");
    }

    @Override
    public EnvNodeImpl<T> build() {
        return new EnvNodeImpl<>(this);
    }

    @Override
    public Self adapter(Function<String, T> adapter) {
        this.adapter = adapter;
        //noinspection unchecked
        return (Self) this;
    }

    @Override
    public Self validator(Validator<T> validator) {
        this.validator = validator;
        //noinspection unchecked
        return (Self) this;
    }
}
