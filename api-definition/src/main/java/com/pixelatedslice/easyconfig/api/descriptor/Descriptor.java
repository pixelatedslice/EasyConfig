package com.pixelatedslice.easyconfig.api.descriptor;

import com.google.common.reflect.TypeToken;
import org.jspecify.annotations.NonNull;

import java.util.Optional;
import java.util.ServiceLoader;

@FunctionalInterface
public interface Descriptor<T> {
    static <T> @NonNull Descriptor<T> of(TypeToken<T> typeToken) {
        return ServiceLoader.load(DescriptorFactory.class).findFirst().orElseThrow().create(typeToken);
    }

    Optional<TypeToken<T>> typeToken();

    interface Builder<T, R> {
        @NonNull Builder<T, R> typeToken(@NonNull TypeToken<T> typeToken);

        @NonNull R build();
    }
}
