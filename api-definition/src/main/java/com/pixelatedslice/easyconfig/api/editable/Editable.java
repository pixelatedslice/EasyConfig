package com.pixelatedslice.easyconfig.api.editable;

import org.jspecify.annotations.NullMarked;


@FunctionalInterface
@NullMarked
public interface Editable<E extends EditableVariant> {
    E editable();
}
