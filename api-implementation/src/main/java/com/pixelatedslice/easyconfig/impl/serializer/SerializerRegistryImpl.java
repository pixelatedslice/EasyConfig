package com.pixelatedslice.easyconfig.impl.serializer;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistryOptions;
import com.pixelatedslice.easyconfig.impl.utils.DistinctByGatherer;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

@NullMarked
public class SerializerRegistryImpl implements SerializerRegistry {

    private final @Nullable SerializerRegistryImpl parent;
    private final Map<TypeToken<?>, Serializer<?>> serializers = new ConcurrentHashMap<>();

    public SerializerRegistryImpl(@Nullable SerializerRegistryImpl parent) {
        this.parent = parent;
    }

    @Override
    public SerializerRegistry createChild() {
        return new SerializerRegistryImpl(this);
    }

    @Override
    public Optional<SerializerRegistry> parent() {
        return Optional.ofNullable(this.parent);
    }

    @Override
    public Stream<Serializer<?>> stream() {
        Stream<SerializerRegistryImpl> stream = Stream.of(this);
        var target = this;
        while (target.parent != null) {
            stream = Stream.concat(stream, Stream.of(target.parent));
            target = target.parent;
        }
        return stream
                .flatMap(impl -> impl.serializers.entrySet().stream())
                .gather(new DistinctByGatherer<>(Map.Entry::getKey))
                .map(Map.Entry::getValue);
    }

    @Override
    public <T> Optional<Serializer<T>> serializerFor(TypeToken<T> token) {
        @SuppressWarnings("unchecked") final var result = (Serializer<T>) this.serializers.get(Objects.requireNonNull(
                token));
        return ((result == null) && (this.parent != null))
                ? this.parent.serializerFor(token)
                : Optional.ofNullable(result);
    }

    @Override
    public SerializerRegistry register(Consumer<SerializerRegistryOptions> options,
            Iterator<Serializer<?>> serializers) {
        final var optionsImpl = new SerializerRegistryOptionsImpl();
        options.accept(optionsImpl);
        while (serializers.hasNext()) {
            var serializer = serializers.next();
            final var type = serializer.type();
            final var originalSerializer = this.serializers.get(type);
            if (originalSerializer != null) {
                serializer = optionsImpl.duplicateRegisterStyle().action().apply(originalSerializer, serializer);
            }
            this.serializers.put(type, serializer);
        }
        return this;
    }
}
