package com.pixelatedslice.easyconfig.api.serialization;

import com.google.common.reflect.TypeToken;
import org.jspecify.annotations.NullMarked;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

@NullMarked
public interface SerializerRegistry {
    static SerializerRegistry global() {
        return SerializerRegistryHidden.global();
    }

    @NonNull SerializerRegistry createChild();

    @NonNull Optional<SerializerRegistry> parent();

    @NonNull Stream<@NonNull Serializer<?>> stream();

    @NonNull <T> Optional<Serializer<T>> serializerFor(@NonNull TypeToken<T> token);

    default @NonNull <T> Optional<Serializer<T>> serializerFor(@NonNull Class<T> token) {
        return this.serializerFor(TypeToken.of(token));
    }

    SerializerRegistry register(@NonNull Consumer<SerializerRegistryOptions> options, @NonNull Iterator<Serializer<?>> serializers);

    default SerializerRegistry register(@NonNull Iterator<@NonNull Serializer<?>> serializers) {
        return this.register(_ -> {
        }, serializers);
    }

    default SerializerRegistry register(@NonNull Consumer<SerializerRegistryOptions> options, @NonNull Iterable<@NonNull Serializer<?>> serializers){
        return this.register(options, serializers.iterator());
    }

    default SerializerRegistry register(@NonNull Iterable<@NonNull Serializer<?>> serializers){
        return this.register(_ -> {}, serializers.iterator());
    }

    default SerializerRegistry register(@NonNull Consumer<SerializerRegistryOptions> options, @NonNull Serializer<?> @NonNull ... serializers) {
        return this.register(options, List.of(serializers));
    }

    default SerializerRegistry register(@NonNull Serializer<?> @NonNull ... serializers) {
        return this.register(_ -> {
        }, serializers);
    }


}
