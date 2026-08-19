package com.pixelatedslice.easyconfig.impl.config.node.value;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.value.EditableValueNode;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.api.validator.option.ValidateOption;
import com.pixelatedslice.easyconfig.api.validator.option.ValidationOptions;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.config.node.value.builder.ValueNodeBuilder;
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
    private String toString = this.generateToString();

    public ValueNodeImpl(ValueNodeBuilder<T> builder) {
        super(builder);
        this.defaultValue = builder.defaultValue();
        this.token = Objects.requireNonNull(builder.type());
        this.validator = Objects.requireNonNullElseGet(builder.validator(), Validator::empty);
        this.serializer = builder.serializer();
        this.value = builder.value();
    }

    synchronized void internalSetValue(@Nullable T value) {
        this.value = value;
        this.toString = this.generateToString();
    }

    @Override
    public Optional<T> value(ValidateOption<T> option) {
        return Optional.ofNullable(this.value).flatMap((T value) -> this.validate(option, value));
    }

    @Override
    public Optional<T> defaultValue(ValidateOption<T> option) {
        return Optional.ofNullable(this.defaultValue).flatMap((T defaultValue) -> this.validate(option, defaultValue));
    }

    @Override
    public Optional<T> valueOrDefault(ValidateOption<T> option) {
        return this.value(ValidationOptions.returnEmpty()).or(() -> this.defaultValue(option));
    }

    private Optional<T> validate(ValidateOption<T> option, @Nullable T validationValue) {
        final var context = new ValidatorContextImpl();
        this.validator.validate(validationValue, context);
        return context.hasError()
                ? option.onValidationError(validationValue, context)
                : Optional.ofNullable(validationValue);
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
    public ValueNodeBuilder<T> toBuilder() {
        return (ValueNodeBuilder<T>) new ValueNodeBuilder<>(this.token)
                .key(this.key())
                .defaultValue(this.defaultValue)
                .value(this.value)
                .serializer(this.serializer)
                .validator(this.validator);
    }

    @Override
    protected void internalAppendChild(AbstractNode node) {
        throw new IllegalStateException("Value node! should not have called");
    }

    private String generateToString() {
        return "ValueNodeImpl{"
               + "key='"
               + this.key()
               + '\''
               + ", type="
               + this.token
               + ", value="
               + this.value
               + ", defaultValue="
               + this.defaultValue
               + ", fullPath="
               + String.join(",", this.fullPath())
               + '}';
    }

    @Override
    public String toString() {
        return this.toString;
    }
}
