package com.pixelatedslice.easyconfig.impl.serializer;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistryOptions;
import com.pixelatedslice.easyconfig.impl.utils.DistinctByGatherer;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class SerializerRegistryImpl implements SerializerRegistry {

    private final @Nullable SerializerRegistryImpl parent;
    private final @NonNull Map<TypeToken<?>, Serializer<?>> serializers = new ConcurrentHashMap<>();

    public SerializerRegistryImpl(@Nullable SerializerRegistryImpl parent) {
        this.parent = parent;
    }

    @Override
    public @NonNull SerializerRegistry createChild() {
        return new SerializerRegistryImpl(this);
    }

    @Override
    public @NonNull Optional<SerializerRegistry> parent() {
        return Optional.ofNullable(parent);
    }

    @Override
    public @NonNull Stream<@NonNull Serializer<?>> stream() {
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
    public @NonNull <T> Optional<Serializer<T>> serializerFor(@NonNull TypeToken<T> token) {
        @SuppressWarnings("unchecked") var result = (Serializer<T>) this.serializers.get(Objects.requireNonNull(token));
        if (result == null && this.parent != null) {
            return this.parent.serializerFor(token);
        }
        return Optional.ofNullable(result);
    }

    @Override
    public SerializerRegistry register(@NonNull Consumer<SerializerRegistryOptions> options, @NonNull Iterator<Serializer<?>> serializers) {
        var optionsImpl = new SerializerRegistryOptionsImpl();
        options.accept(optionsImpl);
        while (serializers.hasNext()) {
            var serializer = serializers.next();
            var type = serializer.type();
            var originalSerializer = this.serializers.get(type);
            if (originalSerializer != null) {
                serializer = optionsImpl.duplicateRegisterStyle().action().apply(originalSerializer, serializer);
            }
            this.serializers.put(type, serializer);
        }
        return this;
    }
}
