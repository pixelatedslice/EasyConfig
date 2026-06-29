package com.pixelatedslice.easyconfig.impl.config.node.factory.value;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.factory.FactoryNodeBuilder;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.impl.config.node.factory.AbstractFactoryNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public abstract class AbstractFactoryValueNodeBuilder<NextParentStep extends FactoryNodeBuilder.ValueStep<NextParentStep, T>, T>
        extends
        AbstractFactoryNodeBuilder<FactoryNodeBuilder.KeyStep.Value<T>, FactoryNodeBuilder.ValueStep.Original<T>>
        implements FactoryNodeBuilder.ValueStep<NextParentStep, T> {

    private final NextParentStep nextParentStep;
    private @Nullable T defaultValue;
    private @Nullable T value;
    private @Nullable Validator<T> validator;
    private @Nullable Serializer<T> serializer;

    protected AbstractFactoryValueNodeBuilder(Value<T> tValue, Original<T> tOriginal, NextParentStep nextParentStep) {
        super(tValue, tOriginal);
        this.nextParentStep = nextParentStep;
    }

    @Override
    public Node build() {
        return null; // TODO: Build via internal builder.
    }

    @Override
    public NextParentStep defaultValue(@Nullable T defaultValue) {
        this.defaultValue = defaultValue;
        return this.nextParentStep;
    }

    @Override
    public NextParentStep value(@Nullable T value) {
        this.value = value;
        return this.nextParentStep;
    }

    @Override
    public NextParentStep validator(Validator<T> validator) {
        this.validator = validator;
        return this.nextParentStep;
    }

    @Override
    public NextParentStep serializer(Serializer<T> serializer) {
        this.serializer = serializer;
        return this.nextParentStep;
    }

    static class Buildable<T> extends AbstractFactoryValueNodeBuilder<AbstractFactoryValueNodeBuilder.Buildable<T>, T>
            implements FactoryNodeBuilder.ValueStep.Buildable<T> {
        protected Buildable(Value<T> tValue, Original<T> tOriginal) {
            super(tValue, tOriginal, this);
        }
    }
}
