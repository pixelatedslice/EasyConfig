package com.pixelatedslice.easyconfig.api.config.section;

import com.pixelatedslice.easyconfig.api.config.node.ConfigNodeBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * An interface for building configuration sections in a structured configuration system.
 * This builder allows defining hierarchical sections, adding comments, and creating
 * nested or flat configuration nodes with detailed customization.
 */
public interface ConfigSectionBuilder {
    /**
     * Sets the key for the current configuration section.
     * The key uniquely identifies this section within its parent context.
     *
     * @param key the non-null key to set for the configuration section
     * @return this {@code ConfigSectionBuilder} instance, to allow for method chaining
     * @throws NullPointerException if {@code key} is null
     */
    ConfigSectionBuilder key(@NotNull String key);

    /**
     * Sets the parent configuration section for this builder.
     * The parent represents the immediate higher-level configuration section in the hierarchy,
     * providing context for this builder's configuration structure.
     *
     * @param parent the non-null {@link ConfigSection} to be set as the parent for this builder
     * @return this {@link ConfigSectionBuilder} instance, to allow for method chaining
     * @throws NullPointerException if the {@code parent} is null
     */
    ConfigSectionBuilder parent(@NotNull ConfigSection parent);

    /**
     * Adds a configuration node to the current section with the specified key, type, and builder.
     *
     * @param <T>         the type of value associated with the configuration node
     * @param key         the non-null key for the configuration node
     * @param type        the non-null class type of the value the node represents
     * @param nodeBuilder a non-null {@link Consumer} to customize the configuration node
     * @return the current instance of {@code ConfigSectionBuilder} for method chaining
     * @throws NullPointerException if {@code key}, {@code type}, or {@code nodeBuilder} is null
     */
    <T> ConfigSectionBuilder node(@NotNull String key, @NotNull Class<T> type,
            @NotNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder);

    /**
     * Adds a node to the current configuration section using the specified key and node builder.
     * This method allows configuring the node's properties via the provided {@link Consumer}.
     *
     * @param <T>         the type of the value associated with the node being built
     * @param key         the non-null key to associate with the configuration node
     * @param nodeBuilder a {@link Consumer} that configures the {@link ConfigNodeBuilder} for the node
     * @return the current instance of {@code ConfigSectionBuilder}, allowing method chaining
     * @throws NullPointerException if {@code key} or {@code nodeBuilder} is null
     */
    <T> ConfigSectionBuilder node(@NotNull String key,
            @NotNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder);

    /**
     * Adds a configuration node to the current section with the specified type and builder.
     *
     * @param <T>         the type of the value associated with the configuration node
     * @param type        the non-null class representing the type of the configuration node's value
     * @param nodeBuilder a non-null consumer to configure the {@link ConfigNodeBuilder} for the node
     * @return the current instance of {@code ConfigSectionBuilder}, allowing method chaining
     * @throws NullPointerException if {@code type} or {@code nodeBuilder} is null
     */
    <T> ConfigSectionBuilder node(@NotNull Class<T> type, @NotNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder);

    /**
     * Adds a configuration node using the specified node builder. This method allows
     * customization of the node through the provided {@link Consumer}.
     *
     * @param <T>         the type of the value associated with the node being built
     * @param nodeBuilder a non-null {@link Consumer} that configures the {@link ConfigNodeBuilder}
     *                    for the node
     * @return the current instance of {@link ConfigSectionBuilder} to allow for method chaining
     * @throws NullPointerException if {@code nodeBuilder} is null
     */
    <T> ConfigSectionBuilder node(@NotNull Consumer<? super ConfigNodeBuilder<T>> nodeBuilder);

    /**
     * Adds a comment or series of comments to the current configuration section.
     * Each comment line will be added sequentially as part of the section's comments.
     *
     * @param comment the non-null array of strings representing the comment lines to add
     * @return the current {@code ConfigSectionBuilder} instance to allow method chaining
     * @throws NullPointerException if {@code comment} is null
     */
    ConfigSectionBuilder comment(@NotNull String... comment);

    /**
     * Creates a nested configuration section associated with the given key and allows
     * configuring it using the provided {@code nestedSectionBuilder}.
     *
     * @param key                  the non-null key to associate with the nested section
     * @param nestedSectionBuilder a non-null {@link Consumer} used to define the properties of the nested section
     * @return the current {@link ConfigSectionBuilder} instance for method chaining
     * @throws NullPointerException if {@code key} or {@code nestedSectionBuilder} is null
     */
    ConfigSectionBuilder nestedSection(
            @NotNull String key,
            @NotNull Consumer<? super ConfigSectionBuilder> nestedSectionBuilder
    );

    /**
     * Adds a nested configuration section to the current section by using the provided
     * {@link ConfigSectionBuilder} consumer to define its properties.
     *
     * @param nestedSectionBuilder a non-null consumer that configures the nested {@link ConfigSectionBuilder}
     * @return the current {@link ConfigSectionBuilder} to allow for method chaining
     * @throws NullPointerException if {@code nestedSectionBuilder} is null
     */
    ConfigSectionBuilder nestedSection(@NotNull Consumer<? super ConfigSectionBuilder> nestedSectionBuilder);

    /**
     * Builds and returns a {@link ConfigSection} based on the properties and nodes
     * configured in this {@link ConfigSectionBuilder}.
     *
     * @return a non-null {@link ConfigSection} instance representing the final
     * configuration structure
     * @throws NullPointerException if any required argument annotated with {@code @NotNull}
     *                              was not properly provided during the builder's configuration
     */
    @NotNull ConfigSection build();
}
