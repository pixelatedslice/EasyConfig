package com.pixelatedslice.easyconfig.impl.config.node.value;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.value.EditableValueNode;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.api.validator.option.ValidateOption;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.value.builder.AbstractValueNodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.value.builder.ValueNodeOriginalBuilder;
import com.pixelatedslice.easyconfig.impl.validator.ValidatorContextImpl;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

@NullMarked
public class ValueNodeImpl<T> extends AbstractNode implements ValueNode<T> {

    private final TypeToken<T> token;
    private final Validator<T> validator;
    private final @Nullable Serializer<T> serializer;
    private final @Nullable T defaultValue;
    private @Nullable T value;

    public ValueNodeImpl(AbstractValueNodeBuilder<?, T> builder) {
        super(builder);
        this.defaultValue = builder.defaultValue();
        this.token = Objects.requireNonNull(builder.type());
        this.validator = Objects.requireNonNullElseGet(builder.validator(), Validator::empty);
        this.serializer = builder.serializer();
        this.value = builder.value();
    }

    synchronized void internalSetValue(@Nullable T value) {
        this.value = value;
    }

    @Override
    public Optional<T> value(ValidateOption<T> option) {
        return Optional.ofNullable(this.value).flatMap(value -> {
            final var context = new ValidatorContextImpl();
            this.validator.validate(value, context);
            return context.hasError() ? option.onValidationError(value, context) : Optional.of(value);
        });
    }

    @Override
    public Optional<T> defaultValue() {
        return Optional.ofNullable(this.defaultValue);
    }

    @Override
    public Optional<Serializer<T>> serializer() {
        return Optional.ofNullable(this.serializer);
    }

    @Override
    public Validator<T> validator() {
        return this.validator;
    }

    @Override
    public TypeToken<T> typeToken() {
        return this.token;
    }

    @Override
    public EditableValueNode<T> editable() {
        return new EditableValueNodeImpl<>(this);
    }

    @Override
    public ValueNodeOriginalBuilder<T> toBuilder() {
        return new ValueNodeOriginalBuilder<>(this.token, this.key())
                .defaultValue(this.defaultValue)
                .value(this.value)
                .serializer(this.serializer)
                .validator(this.validator);
    }

    @Override
    protected void internalAppendChild(AbstractNode node) {
        throw new IllegalStateException("Value node! should not have called");
    }
}
