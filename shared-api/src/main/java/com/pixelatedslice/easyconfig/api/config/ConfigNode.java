package com.pixelatedslice.easyconfig.api.config;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Represents a configuration node in a hierarchical structure. Each configuration node can store
 * a value, maintain a unique key, and be a part of a tree-like hierarchy. It supports optional
 * parent-child relationships and provides methods to manage its value and hierarchy.
 *
 * @param <T> the type of the value held by this configuration node
 */
public interface ConfigNode<T> extends WithConfigNodeChildren {
    /**
     * Retrieves the unique key that identifies this configuration node.
     *
     * @return the non-null unique key of this configuration node
     */
    @NotNull String key();

    /**
     * Retrieves the value associated with this configuration node, if one exists.
     * The value is optional and may not be present for all configuration nodes.
     *
     * @return an {@link Optional} containing the value associated with this configuration node,
     * or an empty optional if no value is set.
     */
    @NotNull Optional<T> value();

    /**
     * Sets the value associated with this configuration node.
     * The value may be null to indicate that the node does not hold a value.
     *
     * @param value the value to associate with this configuration node,
     *              or null to unset the value.
     */
    void setValue(@Nullable T value);

    /**
     * Retrieves the parent of this configuration node, if one exists. The parent node
     * represents the direct hierarchical ancestor of this node within the configuration
     * structure. If this node is a top-level node, the result will be an empty optional.
     *
     * @return an {@link Optional} containing the parent {@link ConfigNode} of this node,
     * or an empty optional if this node does not have a parent.
     */
    @NotNull Optional<ConfigNode> parent();

    /**
     * Sets the parent node for this configuration node.
     * The parent represents the immediate higher node in the configuration hierarchy.
     * Setting the parent to null indicates that this node is a top-level node
     * with no parent in the hierarchy.
     *
     * @param parent the parent {@link ConfigNode} to associate with this node,
     *               or null if this node should not have a parent.
     */
    void setParent(@Nullable ConfigNode parent);
}
