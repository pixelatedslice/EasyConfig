package com.pixelatedslice.easyconfig.impl.config.node;

import com.pixelatedslice.easyconfig.api.config.node.ConfigNode;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ConfigNodeImpl<T> implements ConfigNode<T> {
    private final List<String> comments = new ArrayList<>();

    /**
     * Represents the unique identifier or name of this configuration node within its parent configuration section.
     * Keys are typically used to retrieve or set values within the configuration hierarchy.
     */
    private final String key;
    /**
     * Represents the class type of the configuration node's value.
     *
     * <p>This variable indicates the class of the value held by the configuration node,
     * enabling type safety and runtime type checking.</p>
     *
     * @throws NullPointerException if the specified class type is null when passed in the constructor.
     */
    private final Class<T> valueClass;
    /**
     * Holds the value associated with a configuration node. This value represents the data
     * stored within the node and its type is determined by the generic type parameter {@code T}.
     * It may be {@code null} if no value is set for the node.
     */
    private T value;
    /**
     * Represents the parent configuration section associated with this configuration node.
     * The parent section provides the hierarchical context for this node, defining its
     * placement within the overall configuration structure.
     * <p>
     * This field may be null, indicating that the configuration node has no parent and
     * exists as a top-level node or standalone entry.
     */
    private ConfigSection parent;

    /**
     * Constructs an instance of the {@code ConfigNodeImpl} class.
     *
     * @param key        the non-null key associated with this configuration node
     * @param value      the value associated with this configuration node, may be null
     * @param valueClass the non-null class of the value type for this configuration node
     * @param parent     the parent configuration section for this node, may be null
     * @throws NullPointerException if {@code key} or {@code valueClass} is null
     */
    private ConfigNodeImpl(
            @NotNull String key,
            @Nullable T value,
            @NotNull Class<T> valueClass,
            @Nullable ConfigSection parent,
            @NotNull List<@NotNull String> comments
    ) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(valueClass);
        Objects.requireNonNull(comments);

        this.key = key;
        this.value = value;
        this.valueClass = valueClass;
        this.parent = parent;
    }

    /**
     * Constructs a new instance of ConfigNodeImpl with the specified key, value, and parent section.
     *
     * @param key    the non-null key of the configuration node
     * @param value  the non-null value to associate with the configuration node
     * @param parent the parent {@link ConfigSection}, or null if this node has no parent
     * @throws NullPointerException if the {@code key} or {@code value} is null
     */
    @SuppressWarnings("unchecked")
    public ConfigNodeImpl(
            @NotNull String key,
            @NotNull T value,
            @Nullable ConfigSection parent,
            @NotNull List<@NotNull String> comments
    ) {
        this(Objects.requireNonNull(key), Objects.requireNonNull(value), (Class<T>) value.getClass(), parent, comments);
    }

    /**
     * Constructs a new {@code ConfigNodeImpl} instance with the specified parameters.
     *
     * @param key        the non-null key that identifies this configuration node
     * @param valueClass the non-null class representing the type of value associated with this node
     * @param parent     the configuration section that acts as the parent of this node; can be null
     * @throws NullPointerException if {@code key} or {@code valueClass} is null
     */
    public ConfigNodeImpl(
            @NotNull String key,
            @NotNull Class<T> valueClass,
            @Nullable ConfigSection parent,
            @NotNull List<@NotNull String> comments
    ) {
        this(Objects.requireNonNull(key), null, Objects.requireNonNull(valueClass), parent, comments);
    }

    @Override
    public @NotNull Collection<String> comments() {
        return Collections.unmodifiableCollection(this.comments);
    }

    @Override
    public void addComment(@NotNull String comment) {
        this.comments.add(comment);
    }

    @Override
    public void removeComment(@NotNull String comment) {
        this.comments.remove(comment);
    }

    @Override
    public void removeComment(int index) {
        this.comments.remove(index);
    }

    @Override
    public void clearComments() {
        this.comments.clear();
    }

    @Override
    public @NotNull String key() {
        return this.key;
    }

    @Override
    public @NotNull Optional<T> value() {
        return Optional.ofNullable(this.value);
    }

    @Override
    public void setValue(@Nullable T value) {
        this.value = value;
    }

    @Override
    public @NotNull Class<T> valueClass() {
        return this.valueClass;
    }

    @Override
    public @NotNull Optional<@NotNull ConfigSection> parent() {
        return Optional.ofNullable(this.parent);
    }

    @Override
    public void setParent(@Nullable ConfigSection parent) {
        this.parent = parent;
    }
}
