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
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class ValueNodeImpl<T> extends AbstractNode implements ValueNode<T> {

    private final @NonNull TypeToken<T> token;
    private final @NonNull Validator<T> validator;
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
    public @NonNull Optional<T> value(@NonNull ValidateOption<T> option) {
        return Optional.ofNullable(this.value).flatMap(value -> {
            var context = new ValidatorContextImpl();
            this.validator.validate(value, context);
            if (context.hasError()) {
                return option.onValidationError(value, context);
            }
            return Optional.of(value);
        });
    }

    @Override
    public @NonNull Optional<T> defaultValue() {
        return Optional.ofNullable(this.defaultValue);
    }

    @Override
    public @NonNull Optional<@NonNull Serializer<T>> serializer() {
        return Optional.ofNullable(this.serializer);
    }

    @Override
    public @NonNull Validator<T> validator() {
        return this.validator;
    }

    @Override
    public @NonNull TypeToken<T> typeToken() {
        return this.token;
    }

    @Override
    public EditableValueNode<T> editable() {
        return new EditableValueNodeImpl<>(this);
    }

    @Override
    public @NonNull ValueNodeOriginalBuilder<T> toBuilder() {
        return new ValueNodeOriginalBuilder<>(this.token, this.key())
                .defaultValue(this.defaultValue)
                .value(this.value)
                .serializer(this.serializer)
                .validator(this.validator);
    }

    @Override
    protected void internalAppendChild(@NonNull AbstractNode node) {
        throw new IllegalStateException("Value node! should not have called");
    }
}
