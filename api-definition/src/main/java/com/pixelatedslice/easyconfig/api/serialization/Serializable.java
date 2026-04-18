package com.pixelatedslice.easyconfig.api.serialization;

import com.google.common.reflect.TypeToken;
import org.jspecify.annotations.NonNull;

public interface Serializable<T extends Serializable<T>> extends Serializer<T> {
    @Override
    default @NonNull TypeToken<T> forType() {
        return new TypeToken<>() {
        };
    }
}