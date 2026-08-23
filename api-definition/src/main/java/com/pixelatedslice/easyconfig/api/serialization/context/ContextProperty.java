package com.pixelatedslice.easyconfig.api.serialization.context;

import org.jspecify.annotations.NonNull;

public interface ContextProperty<V> {

    @NonNull
    String id();

    interface DefaultValue<V> extends ContextProperty<V> {
        @NonNull
        V defaultValue();
    }
}
