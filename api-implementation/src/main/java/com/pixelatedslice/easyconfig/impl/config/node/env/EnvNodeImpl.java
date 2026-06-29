package com.pixelatedslice.easyconfig.impl.config.node.env;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.api.validator.option.ValidateOption;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.env.builder.EnvNodeBuilderImpl;
import com.pixelatedslice.easyconfig.impl.validator.ValidatorContextImpl;
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

    public EnvNodeImpl(EnvNodeBuilderImpl<T, ?> builder) {
        super(builder);
        this.envKey = Objects.requireNonNull(builder.envKey());
        this.adapter = Objects.requireNonNull(builder.adapter());
        this.type = Objects.requireNonNull(builder.type());
        this.validator = Objects.requireNonNullElseGet(builder.validator(), Validator::empty);
    }

    @Override
    public String envKey() {
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
    public Function<String, T> adapter() {
        return this.adapter;
    }

    @Override
    public Validator<T> validator() {
        return this.validator;
    }

    @Override
    public TypeToken<T> typeToken() {
        return this.type;
    }

    @Override
    protected void internalAppendChild(AbstractNode node) {
        throw new IllegalArgumentException("child nodes are not supported on EnvNode");
    }

    @Override
    public OriginalEnvNodeBuilder<T> toBuilder() {
        return new OriginalEnvNodeBuilder<>(this.key(), this.typeToken(), this.envKey())
                .config(this.attached)
                .parent(this.parent)
                .adapter(this.adapter);
    }
}
