package com.pixelatedslice.easyconfig.impl.config.node.value;

import com.pixelatedslice.easyconfig.api.config.node.value.EditableValueNode;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.atomic.AtomicReference;

@NullMarked
class EditableValueNodeImpl<T> implements EditableValueNode<T> {

    private final ValueNodeImpl<T> modifying;
    private final AtomicReference<@Nullable T> valueChangingTo = new AtomicReference<>();

    EditableValueNodeImpl(ValueNodeImpl<T> modifying) {
        this.modifying = modifying;
    }

    @Override
    public EditableValueNode<T> setValue(@Nullable T value) {
        this.valueChangingTo.set(value);
        return this;
    }

    @Override
    public void close() {
        this.modifying.internalSetValue(this.valueChangingTo.get());
    }
}
