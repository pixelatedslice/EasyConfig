package com.pixelatedslice.easyconfig.impl.config.node.env;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.NodeBuilder;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.api.validator.option.ValidateOption;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.env.builder.EnvNodeBuilder;
import com.pixelatedslice.easyconfig.impl.validator.ValidatorContextImpl;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@NullMarked
public class EnvNodeImpl<T> extends AbstractNode implements EnvNode<T> {
    private final String envKey;
    private final Function<String, @Nullable T> adapter;
    private final TypeToken<T> type;
    private final Validator<T> validator;

    public EnvNodeImpl(EnvNodeBuilder<T> builder) {
        super(builder);
        this.envKey = Objects.requireNonNull(builder.envKey());
        this.adapter = Objects.requireNonNull(builder.adapter());
        this.type = Objects.requireNonNull(builder.type());
        this.validator = Objects.requireNonNullElseGet(builder.validator(), Validator::empty);
    }

    @Override
    public @NonNull String envKey() {
        return this.envKey;
    }

    @Override
    public Optional<T> value(ValidateOption<T> option) {
        final var envValue = System.getenv(this.envKey);
        return (envValue == null)
                ? Optional.empty()
                : Optional.ofNullable(this.adapter.apply(envValue)).flatMap(value -> {
                    final var context = new ValidatorContextImpl();
                    this.validator.validate(value, context);
                    return context.hasError() ? option.onValidationError(value, context) : Optional.of(value);
                });
    }

    @Override
    public @NonNull Function<String, T> adapter() {
        return this.adapter;
    }

    @Override
    public Validator<T> validator() {
        return this.validator;
    }

    @Override
    public @NonNull TypeToken<T> typeToken() {
        return this.type;
    }

    @Override
    protected void internalAppendChild(@NonNull AbstractNode node) {
        throw new IllegalArgumentException("child nodes are not supported on EnvNode");
    }

    @Override
    public NodeBuilder toBuilder() {
        return (EnvNodeBuilder<T>) new EnvNodeBuilder<>(this.type)
                .config(this.attached)
                .parent(this.parent)
                .adapter(this.adapter)
                .key(this.key())
                .variable(this.envKey);
    }

    @Override
    public String toString(){
        return "EnvNodeImpl{" +
                "key='" + this.key() + '\'' +
                ", envKey='" + this.envKey + '\'' +
                ", type=" + this.type +
                ", fullPath=" + String.join(",", this.fullPath()) +
                '}';
    }
}
