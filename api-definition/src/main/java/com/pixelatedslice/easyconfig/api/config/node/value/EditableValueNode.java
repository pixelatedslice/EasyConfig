package com.pixelatedslice.easyconfig.api.config.node.value;

import com.pixelatedslice.easyconfig.api.editable.EditableVariant;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public interface EditableValueNode<T> extends EditableVariant {
    EditableValueNode<T> setValue(@Nullable T value);
}
