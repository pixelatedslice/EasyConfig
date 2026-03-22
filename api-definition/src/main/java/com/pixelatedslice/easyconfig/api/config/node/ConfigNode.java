package com.pixelatedslice.easyconfig.api.config.node;

import com.pixelatedslice.easyconfig.api.config.Commentable;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Represents a configuration node within a hierarchical configuration structure.
 * A configuration node consists of a key, an optional value, and may also have
 * a parent and child nodes. This interface provides methods to manage and access
 * these properties, supporting a tree-like configuration model.
 *
 * @param <T> the type of the value associated with this configuration node
 */
public interface ConfigNode<T> extends Commentable {
    /**
     * Retrieves the key associated with this configuration node.
     * The key serves as the unique identifier for the node within its hierarchy.
     *
     * @return the non-null key of this configuration node
     */
    @NotNull String key();

    /**
     * Retrieves the optional value associated with this configuration node.
     * A value may or may not be present, depending on whether one has been set
     * for this node.
     *
     * @return an {@link Optional} containing the value if it is present, or an empty {@link Optional} if no value is
     * set
     */
    @NotNull Optional<T> value();

    /**
     * Sets the value associated with this configuration node.
     *
     * @param value the value to be set for this node; can be null to represent the absence of a value
     */
    void setValue(@Nullable T value);

    /**
     * Retrieves the class of the value associated with this configuration node.
     * The returned class represents the type parameter {@code T} of this node.
     *
     * @return the non-null class of the value associated with this node
     */
    @NotNull Class<@NotNull T> valueClass();

    /**
     * Retrieves the parent configuration section of this configuration node, if present.
     * The parent section represents the immediate higher level in the hierarchy,
     * providing context for this node within its associated configuration structure.
     *
     * @return an {@link Optional} containing the parent {@link ConfigSection} if it exists,
     * or an empty {@link Optional} if this node has no parent
     * @throws NullPointerException if the parent configuration section is null
     */
    @NotNull Optional<@NotNull ConfigSection> parent();

    /**
     * Sets the parent configuration section for this configuration node.
     * The parent defines the hierarchical relationship of this node within
     * the configuration structure.
     *
     * @param parent the {@link ConfigSection} to be set as the parent for this node;
     *               can be null to indicate no parent is assigned to this node
     */
    void setParent(@Nullable ConfigSection parent);
}
