package com.pixelatedslice.easyconfig.impl.fileformat.serializer;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.serializer.SerializerNode;
import com.pixelatedslice.easyconfig.api.exception.SerializeException;
import com.pixelatedslice.easyconfig.api.exception.SerializerException;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import com.pixelatedslice.easyconfig.api.serialization.context.ContextProperty;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import tools.jackson.core.JsonPointer;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class NodeSerializerImpl implements SerializerNode {

    private final @NonNull String @NonNull [] path;
    private final @NonNull ObjectMapper mapper;
    private final @NonNull ObjectNode objectNode;
    private final @NonNull SerializerRegistry serializerRegistry;
    private final @Nullable Serializer<?> serializer;
    private final Map<ContextProperty<?>, Object> properties = new ConcurrentHashMap<>();
    private final @NonNull Node target;

    public NodeSerializerImpl(@NonNull SerializerRegistry serializerRegistry, @NonNull ObjectMapper mapper, @NonNull ObjectNode objectNode, @NonNull Node node, @NonNull String @NonNull ... path) {
        this(serializerRegistry, null, mapper, objectNode, node, path);
    }

    public NodeSerializerImpl(@NonNull SerializerRegistry serializerRegistry, @Nullable Serializer<?> serializer, @NonNull ObjectMapper mapper, @NonNull ObjectNode objectNode, @NonNull Node node, @NonNull String @NonNull ... path) {
        this.path = Objects.requireNonNull(path);
        if (path.length == 0) {
            throw new IllegalArgumentException("Path cannot be null");
        }
        if (!path[0].equals(JsonPointer.SEPARATOR + "")) {
            throw new IllegalArgumentException("Path must start with " + JsonPointer.SEPARATOR);
        }
        this.mapper = Objects.requireNonNull(mapper);
        this.serializer = serializer;
        this.serializerRegistry = Objects.requireNonNull(serializerRegistry);
        this.objectNode = Objects.requireNonNull(objectNode);
        this.target = Objects.requireNonNull(node);
    }

    @Override
    public @NonNull SerializerNode node(@NonNull String... node) {
        var newPath = new String[node.length + this.path.length];
        System.arraycopy(this.path, 0, newPath, 0, node.length);
        System.arraycopy(node, 0, newPath, this.path.length, node.length);
        return new NodeSerializerImpl(serializerRegistry, this.mapper, this.objectNode, this.target, newPath);
    }

    @Override
    public @NonNull Optional<SerializerNode> parent() {
        if (this.path.length == 0) {
            return Optional.empty();
        }
        var newPath = new String[this.path.length - 1];
        System.arraycopy(this.path, 0, newPath, 0, this.path.length - 1);
        return Optional.of(new NodeSerializerImpl(this.serializerRegistry, this.mapper, this.objectNode, this.target, newPath));
    }

    @Override
    public void set(@NonNull Object value) {
        Class<Object> targetClass = (Class<Object>) value.getClass();
        Optional<Serializer<Object>> opSerializer = this.serializerRegistry.serializerFor(targetClass).or(() -> {
            if (this.serializer == null) {
                return Optional.empty();
            }
            if (this.serializer.type().isSubtypeOf(value.getClass())) {
                return Optional.of(this.serializer).map(serializer1 -> (Serializer<Object>) serializer1);
            }
            return Optional.empty();
        });
        if (opSerializer.isEmpty()) {
            throw new IllegalArgumentException("No serializer for " + value + ":" + value.getClass().getName());
        }
        var serializer = opSerializer.get();
        var context = new SerializerContextImpl(this.mapper, this.objectNode, this.properties);
        serializer.deserialize(value, this, context);
    }

    @Override
    public <T> T read(TypeToken<T> token) throws SerializeException {
        var serializer = Optional
                .ofNullable(this.serializer)
                .filter(serial -> serial.type().isSubtypeOf(token))
                .map(serial -> (Serializer<T>) serial)
                .or(() -> this.serializerRegistry.serializerFor(token))
                .orElseThrow(() -> new SerializerException.MissingSerializerException(this));
        var context = new SerializerContextImpl(this.mapper, this.objectNode, this.properties);
        return serializer.serialize(this.target, this, context);
    }

    @Override
    public @NonNull String[] path() {
        return Arrays.copyOf(this.path, this.path.length);
    }
}
