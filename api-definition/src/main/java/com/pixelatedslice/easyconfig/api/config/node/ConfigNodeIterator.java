package com.pixelatedslice.easyconfig.api.config.node;

import com.pixelatedslice.easyconfig.api.EasyConfig;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSectionIterator;
import com.pixelatedslice.easyconfig.api.exception.BrokenNodeKeyException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Represents an iterator for traversing configuration nodes in a hierarchical structure.
 * This interface provides methods to search for specific {@code ConfigNode} elements,
 * leveraging key-based lookups and optional type filtering.
 */
public interface ConfigNodeIterator extends Iterator<ConfigNode<?>> {
    /**
     * Searches for a configuration node matching the specified type and keys within the hierarchy
     * starting from the given root section. If the node is not found, an empty {@link Optional} is returned.
     *
     * @param rootSection  the root configuration section to search within; must not be null
     * @param type         the expected class type of the configuration node's value; must not be null
     * @param providedKeys an array of keys defining the path to the desired node; must not be null
     * @return an {@link Optional} containing the matching configuration node if it exists, or an empty
     * {@link Optional} otherwise
     * @throws NullPointerException  if {@code rootSection}, {@code type}, or {@code providedKeys} is null
     * @throws IllegalStateException if the node at the target key exists but does not match the expected type
     */
    @SuppressWarnings("unchecked")
    static <T> @NotNull Optional<@NotNull ConfigNode<T>> findNode(
            @NotNull ConfigSection rootSection,
            @NotNull Class<@NotNull T> type,
            @NotNull String... providedKeys
    ) {
        Objects.requireNonNull(rootSection);
        Objects.requireNonNull(type);
        Objects.requireNonNull(providedKeys);

        if (providedKeys.length == 0) {
            return Optional.empty();
        }

        if (providedKeys.length == 1) {
            var nodeKey = providedKeys[0];
            for (var node : rootSection.childNodes()) {
                if (!node.key().equals(nodeKey)) {
                    continue;
                }

                if (!node.valueClass().equals(type)) {
                    throw new IllegalStateException(String.format(
                            "The node (%s) is not of the expected type",
                            nodeKey
                    ));
                }

                return Optional.of((ConfigNode<T>) node);
            }
            return Optional.empty();
        }

        String[] parentKeys = Arrays.copyOf(providedKeys, providedKeys.length - 1);
        var nodeKey = providedKeys[providedKeys.length - 1];

        var sectionOptional = ConfigSectionIterator.findSection(rootSection.nestedSections(), parentKeys);

        return sectionOptional.flatMap((@NotNull ConfigSection section) -> section.childNode(type, nodeKey));
    }

    /**
     * Searches for a specific configuration node within a given configuration section using a key-based lookup.
     * This method adheres to a style inspired by the Bukkit API for streamlined access.
     *
     * @param <T>         the type of the value associated with the configuration node
     * @param rootSection the non-null root configuration section where the search begins
     * @param type        the non-null class of the value type expected for the configuration node
     * @param key         the non-null key identifying the configuration node; supports hierarchical keys separated
     *                    by dots
     * @return an {@link Optional} containing the matching {@link ConfigNode}, or an empty {@link Optional} if no
     * matching node is found
     * @throws NullPointerException   if {@code rootSection}, {@code type}, or {@code key} is null
     * @throws BrokenNodeKeyException if the provided key does not match the expected key pattern
     */
    @SuppressWarnings("DuplicatedCode")
    static <T> @NotNull Optional<@NotNull ConfigNode<T>> findNodeButInTheBukkitAPIStyle(
            @NotNull ConfigSection rootSection,
            @NotNull Class<@NotNull T> type,
            @NotNull String key
    ) {
        Objects.requireNonNull(rootSection);
        Objects.requireNonNull(type);
        Objects.requireNonNull(key);

        List<String> keys;
        if (!EasyConfig.KEY_REGEX.matcher(key).matches()) {
            throw new BrokenNodeKeyException(key, EasyConfig.KEY_REGEX.pattern());
        }
        keys = List.of(key.split("\\."));

        return findNode(rootSection, type, keys.toArray(new String[0]));
    }

    /**
     * Checks if there are more elements to iterate over in the configuration node hierarchy.
     *
     * @return {@code true} if there are more elements to iterate, {@code false} otherwise
     */
    @Override
    boolean hasNext();

    /**
     * Retrieves the next configuration node in the iteration sequence.
     * This method progresses the iterator to the next element, if available,
     * and returns it. If there are no more elements to iterate, the method
     * may return {@code null}.
     *
     * @return the next {@link ConfigNode} in the sequence
     * @throws NoSuchElementException if the next element is null
     */
    @Override
    @Nullable ConfigNode<?> next();
}
