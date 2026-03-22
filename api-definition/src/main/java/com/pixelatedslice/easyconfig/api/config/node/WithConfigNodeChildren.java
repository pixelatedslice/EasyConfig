package com.pixelatedslice.easyconfig.api.config.node;

import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * Functional interface representing an entity that supports operations for managing
 * and retrieving child configuration nodes within a hierarchical configuration structure.
 * This interface provides methods for locating specific configuration nodes and iterating
 * through child nodes.
 */
public interface WithConfigNodeChildren {
    /**
     * Retrieves the list of child configuration nodes associated with this configuration node container.
     * Each child node represents a sub-node in the hierarchical configuration structure.
     *
     * @return a non-null list containing non-null {@link ConfigNode} elements that are the children
     * of this node container
     * @throws NullPointerException if the implementing instance returns a null list or contains any null elements
     */
    @NotNull List<@NotNull ConfigNode<?>> childNodes();

    /**
     * Attempts to locate a child configuration node within the specified root section, based on
     * the provided keys and the expected type of the node value.
     *
     * @param <T>          the type of the value associated with the desired child configuration node
     * @param rootSection  the root {@link ConfigSection} from which to begin the search; must be non-null
     * @param type         the {@link Class} representing the type of the value expected in the located node; must be
     *                     non-null
     * @param providedKeys an array of keys representing the path to the desired node within the hierarchical
     *                     structure; must be non-null
     * @return an {@link Optional} containing the located {@link ConfigNode} if found, or an empty {@link Optional}
     * if no matching node exists
     * @throws NullPointerException if any of {@code rootSection}, {@code type}, or {@code providedKeys} is null
     */
    default <T> @NotNull Optional<@NotNull ConfigNode<T>> childNode(
            @NotNull ConfigSection rootSection,
            @NotNull Class<@NotNull T> type,
            @NotNull String... providedKeys
    ) {
        return ConfigNodeIterator.findNode(rootSection, type, providedKeys);
    }

    /**
     * Locates a child configuration node within the given root section using the specified key, in a manner
     * aligned with the Bukkit API style. This method searches for a single key instead of a path consisting
     * of multiple keys.
     *
     * @param <T>         the type of the value associated with the configuration node
     * @param rootSection the root configuration section in which to search; must not be null
     * @param type        the class type of the value expected to be associated with the node; must not be null
     * @param key         the key identifying the child node to be located; must not be null
     * @return an {@link Optional} containing the located {@link ConfigNode} if found, or an empty {@link Optional}
     * if no matching node is found
     * @throws NullPointerException if any of the rootSection, type, or key arguments are null
     */
    default <T> @NotNull Optional<@NotNull ConfigNode<T>> childNodeButInTheBukkitAPIStyle(
            @NotNull ConfigSection rootSection,
            @NotNull Class<@NotNull T> type,
            @NotNull String key
    ) {
        return ConfigNodeIterator.findNodeButInTheBukkitAPIStyle(rootSection, type, key);
    }

    /**
     * Adds a child configuration node to the current node's hierarchy.
     * The provided child node is incorporated as an immediate descendant of
     * the current node in the hierarchical configuration structure.
     *
     * @param child the non-null {@link ConfigNode} object to be added as a child
     *              of the current node; must not be null
     * @throws NullPointerException if the {@code child} argument is null
     */
    void addChildNode(@NotNull ConfigNode<?> child);

    /**
     * Removes a child configuration node associated with the specified key.
     * The method attempts to find a child node with a matching key and removes it
     * from the collection of child nodes maintained by this object.
     *
     * @param key the non-null key identifying the child configuration node to be removed.
     *            The key must refer to an existing child node for successful removal.
     * @return {@code true} if a child node was successfully removed;
     * {@code false} if no matching node was found.
     * @throws NullPointerException if the {@code key} argument is null.
     */
    void removeChildNode(@NotNull String key);

    /**
     * Provides an iterator over the child configuration nodes.
     * The returned iterator allows traversal through the hierarchical structure
     * of configuration nodes managed by the implementing entity.
     *
     * @return a non-null iterator containing non-null {@link ConfigNode} elements
     * @throws NullPointerException if the implementing class fails to uphold
     *                              the non-null constraint for any element in the iterator
     */
    @NotNull ConfigNodeIterator nodeIterator();
}
