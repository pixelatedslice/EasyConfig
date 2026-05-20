package com.pixelatedslice.easyconfig.api.serialization;

import com.google.common.reflect.TypeToken;
import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface SerializerRegistry {
    static SerializerRegistry global() {
        return SerializerRegistryHidden.GLOBAL;
    }

    @NonNull SerializerRegistry createChild();

    @NonNull Optional<SerializerRegistry> parent();

    @NonNull Stream<@NonNull Serializer<?>> stream();

    @NonNull <T> Optional<Serializer<T>> serializerFor(@NonNull TypeToken<T> token);

    default @NonNull <T> Optional<Serializer<T>> serializerFor(@NonNull Class<T> token) {
        return serializerFor(TypeToken.of(token));
    }

    SerializerRegistry register(@NonNull Consumer<SerializerRegistryOptions> options, @NonNull Iterator<Serializer<?>> serializers);

    default SerializerRegistry register(@NonNull Iterator<@NonNull Serializer<?>> serializers) {
        return register(_ -> {
        }, serializers);
    }

    default SerializerRegistry register(@NonNull Consumer<SerializerRegistryOptions> options, @NonNull Iterable<@NonNull Serializer<?>> serializers){
        return register(options, serializers.iterator());
    }

    default SerializerRegistry register(@NonNull Iterable<@NonNull Serializer<?>> serializers){
        return register(_ -> {}, serializers.iterator());
    }

    default SerializerRegistry register(@NonNull Consumer<SerializerRegistryOptions> options, @NonNull Serializer<?> @NonNull ... serializers) {
        return register(options, List.of(serializers));
    }

    default SerializerRegistry register(@NonNull Serializer<?> @NonNull ... serializers) {
        return register(_ -> {
        }, serializers);
    }


}
