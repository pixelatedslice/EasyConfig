package com.pixelatedslice.easyconfig.api.serialization.context;

import org.jspecify.annotations.NonNull;

public class GenericContextProperty {

    public static final ContextProperty<ContextWriteStyle> WRITE_STYLE = new PropertyImpl<>("WRITE_STYLE", ContextWriteStyle.EASY_READING);

    private record PropertyImpl<T>(@NonNull String id,
                                   @NonNull T defaultValue) implements ContextProperty.DefaultValue<T> {
    }
}
