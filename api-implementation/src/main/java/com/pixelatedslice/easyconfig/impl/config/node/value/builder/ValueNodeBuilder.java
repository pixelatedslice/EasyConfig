package com.pixelatedslice.easyconfig.impl.config.node.value.builder;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.FactoryNodeBuilderValueStep;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.InternalNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.factory.AbstractFactoryNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.value.ValueNodeImpl;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@NullMarked
public class ValueNodeBuilder<T>
        extends
        AbstractFactoryNodeBuilder<ValueNode<T>, FactoryNodeBuilderKeySteps.Value<T>, FactoryNodeBuilderValueStep<T>>
        implements FactoryNodeBuilderValueStep.FirstStep<T>, FactoryNodeBuilderValueStep.DefaultValueAndExtrasStep<T>,
        FactoryNodeBuilderValueStep.ValueAndExtrasStep<T>, InternalNodeBuilder<ValueNodeBuilder<T>> {

    String key;
    @Nullable T defaultValue;
    @Nullable T value;
    TypeToken<T> typeToken;
    @Nullable Validator<T> validator;
    @Nullable Serializer<T> serializer;
    @Nullable Config config;
    @Nullable AbstractNode parent;
    Collection<InternalNodeBuilder<?>> children = new CopyOnWriteArrayList<>();

    public ValueNodeBuilder(TypeToken<T> token, String key) {
        this.key = Objects.requireNonNull(key);
        this.typeToken = Objects.requireNonNull(token);
    }

    public String key() {
        return this.key;
    }

    public @Nullable T defaultValue() {
        return this.defaultValue;
    }

    public @Nullable T value() {
        return this.value;
    }

    public TypeToken<T> type() {
        return this.typeToken;
    }

    @Override
    public ValueAndExtrasStep<T> defaultValue(@Nullable T defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Override
    public DefaultValueAndExtrasStep<T> value(@Nullable T value) {
        this.value = value;
        return this;
    }

    @Override
    public ExtrasStep<T> validator(@Nullable Validator<T> validator) {
        this.validator = validator;
        return this;
    }

    public @Nullable Validator<T> validator() {
        return this.validator;
    }

    public @Nullable Serializer<T> serializer() {
        return this.serializer;
    }

    @Override
    public ExtrasStep<T> serializer(@Nullable Serializer<T> serializer) {
        this.serializer = serializer;
        return this;
    }

    @Override
    public ValueNodeBuilder<T> parent(@Nullable AbstractNode node) {
        this.parent = node;
        return this;
    }

    @Override
    public @Nullable AbstractNode parent() {
        return this.parent;
    }

    @Override
    public ValueNodeBuilder<T> config(@Nullable Config config) {
        this.config = config;
        return this;
    }

    @Override
    public @Nullable Config config() {
        return this.config;
    }

    @Override
    public Collection<InternalNodeBuilder<?>> children() {
        return Collections.unmodifiableCollection(this.children);
    }

    @Override
    public void appendChild(InternalNodeBuilder<?> builder) {
        this.children.add(builder);
    }

    @Override
    public ValueNodeImpl<T> build() {
        return new ValueNodeImpl<>(this);
    }
}
