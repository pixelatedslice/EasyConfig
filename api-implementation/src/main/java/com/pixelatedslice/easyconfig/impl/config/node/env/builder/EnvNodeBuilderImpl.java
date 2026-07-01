package com.pixelatedslice.easyconfig.impl.config.node.env.builder;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderEnvStep;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderHandlers;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.env.EnvNodeImpl;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.Objects;
import java.util.function.Function;

@SuppressWarnings("PublicMethodNotExposedInInterface")
@NullMarked
public class EnvNodeBuilderImpl<T>
        implements FactoryNodeBuilderHandlers.Env<T>, InternalNodeBuilder<EnvNodeBuilderImpl<T>> {

    private final TypeToken<T> typeToken;
    private @Nullable String key;
    private @Nullable String envKey;
    private @Nullable AbstractNode parent;
    private @Nullable Config config;
    private @Nullable Function<String, @Nullable T> adapter;
    private @Nullable Validator<T> validator;

    public EnvNodeBuilderImpl(TypeToken<T> typeToken) {
        this.typeToken = Objects.requireNonNull(typeToken);
    }

    public Validator<T> validator() {
        return Objects.requireNonNull(this.validator);
    }

    public @Nullable Function<String, @Nullable T> adapter() {
        return Objects.requireNonNull(this.adapter);
    }

    public TypeToken<T> type() {
        return this.typeToken;
    }

    public String envKey() {
        return Objects.requireNonNull(this.envKey);
    }

    @Override
    public EnvNodeBuilderImpl<T> parent(@Nullable AbstractNode node) {
        this.parent = node;
        return this;
    }

    @Override
    public @Nullable AbstractNode parent() {
        return this.parent;
    }

    @Override
    public EnvNodeBuilderImpl<T> config(@Nullable Config config) {
        this.config = config;
        return this;
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
    public java.util.Collection<InternalNodeBuilder<?>> children() {
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
    public EnvNodeBuilderImpl<T> adapter(@Nullable Function<String, @Nullable T> adapter) {
        this.adapter = adapter;
        return this;
    }

    @Override
    public EnvNodeBuilderImpl<T> validator(@Nullable Validator<T> validator) {
        this.validator = validator;
        return this;
    }

    @Override
    public FactoryNodeBuilderEnvStep.AdapterValidatorStep<T> variable(String variable) {
        this.envKey = variable;
        return this;
    }

    @Override
    public FactoryNodeBuilderEnvStep.VariableStep<T> key(String key) {
        this.key = key;
        return this;
    }
}
