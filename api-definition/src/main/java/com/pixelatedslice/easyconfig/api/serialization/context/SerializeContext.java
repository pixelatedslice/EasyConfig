package com.pixelatedslice.easyconfig.api.serialization.context;

import org.jspecify.annotations.NonNull;

import java.util.Optional;

public interface SerializeContext {

    <T> Optional<T> property(@NonNull ContextProperty<T> property);
}
