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

    SerializerRegistry createChild();

    Optional<SerializerRegistry> parent();

    Stream<Serializer<?>> stream();

    <T> Optional<Serializer<T>> serializerFor(TypeToken<T> token);

    default <T> Optional<Serializer<T>> serializerFor(Class<T> token) {
        return this.serializerFor(TypeToken.of(token));
    }

    SerializerRegistry register(Consumer<SerializerRegistryOptions> options, Iterator<Serializer<?>> serializers);

    default SerializerRegistry register(Iterator<Serializer<?>> serializers) {
        return this.register(_ -> {
        }, serializers);
    }

    default SerializerRegistry register(Consumer<SerializerRegistryOptions> options, Iterable<Serializer<?>> serializers) {
        return this.register(options, serializers.iterator());
    }

    default SerializerRegistry register(Iterable<Serializer<?>> serializers) {
        return this.register(_ -> {
        }, serializers.iterator());
    }

    default SerializerRegistry register(Consumer<SerializerRegistryOptions> options, Serializer<?>... serializers) {
        return this.register(options, List.of(serializers));
    }

    default SerializerRegistry register(Serializer<?>... serializers) {
        return this.register(_ -> {
        }, serializers);
    }


}
