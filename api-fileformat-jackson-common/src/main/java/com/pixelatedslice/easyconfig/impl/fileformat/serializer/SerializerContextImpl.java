package com.pixelatedslice.easyconfig.impl.fileformat.serializer;

import com.pixelatedslice.easyconfig.api.serialization.context.ContextProperty;
import com.pixelatedslice.easyconfig.api.serialization.context.SerializeContext;
import org.jspecify.annotations.NonNull;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SerializerContextImpl implements SerializeContext {

    private final @NonNull ObjectMapper mapper;
    private final @NonNull ObjectNode node;
    private final @NonNull Map<ContextProperty<?>, Object> properties = new ConcurrentHashMap<>();

    public SerializerContextImpl(@NonNull ObjectMapper mapper, ObjectNode node, @NonNull Map<ContextProperty<?>, Object> properties) {
        this.mapper = Objects.requireNonNull(mapper);
        this.properties.putAll(properties);
        this.node = Objects.requireNonNull(node);
    }

    public @NonNull ObjectMapper mapper() {
        return this.mapper;
    }

    @Override
    public <T> Optional<T> property(@NonNull ContextProperty<T> property) {
        //noinspection unchecked
        return Optional.ofNullable((T) properties.get(property)).or(() -> {
            if (property instanceof ContextProperty.DefaultValue<T> defaultProperty) {
                return Optional.of(defaultProperty.defaultValue());
            }
            return Optional.empty();
        });
    }

    public ObjectNode objectNode() {
        return this.node;
    }
}
