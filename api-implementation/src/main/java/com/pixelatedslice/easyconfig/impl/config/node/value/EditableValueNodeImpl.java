package com.pixelatedslice.easyconfig.impl.config.node.value;

import com.pixelatedslice.easyconfig.api.config.node.value.EditableValueNode;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.atomic.AtomicReference;

class EditableValueNodeImpl<T> implements EditableValueNode<T> {

    private final ValueNodeImpl<T> modifying;
    private final AtomicReference<@Nullable T> valueChangingTo = new AtomicReference<>();

    EditableValueNodeImpl(@NonNull ValueNodeImpl<T> modifying) {
        this.modifying = modifying;
    }

    @Override
    public @NonNull EditableValueNode<T> setValue(@Nullable T value) {
        valueChangingTo.set(value);
        return this;
    }

    @Override
    public void close() {
        this.modifying.internalSetValue(valueChangingTo.get());
    }
}
