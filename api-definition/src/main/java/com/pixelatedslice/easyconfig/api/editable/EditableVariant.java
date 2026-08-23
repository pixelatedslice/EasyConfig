package com.pixelatedslice.easyconfig.api.editable;

import org.jspecify.annotations.NullMarked;


@FunctionalInterface
@NullMarked
public interface EditableVariant extends AutoCloseable {
    @Override
    void close();
}
