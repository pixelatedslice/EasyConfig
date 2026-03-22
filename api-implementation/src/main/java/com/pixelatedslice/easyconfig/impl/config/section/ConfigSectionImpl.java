package com.pixelatedslice.easyconfig.impl.config.section;

import com.pixelatedslice.easyconfig.api.config.node.ConfigNode;
import com.pixelatedslice.easyconfig.api.config.node.ConfigNodeIterator;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSection;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSectionIterator;
import com.pixelatedslice.easyconfig.impl.config.node.ConfigNodeIteratorImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ConfigSectionImpl implements ConfigSection {
    private final String key;
    private final List<ConfigNode<?>> childNodes;
    private final List<ConfigSection> nestedSections;
    private final List<String> comments;
    private ConfigSection parent;

    public ConfigSectionImpl(@NotNull String key, @Nullable ConfigSection parent,
            @NotNull List<@NotNull ConfigNode<?>> childNodes,
            @NotNull List<@NotNull ConfigSection> nestedSections, @NotNull List<@NotNull String> comments) {
        this.key = key;
        this.parent = parent;
        this.childNodes = childNodes;
        this.nestedSections = nestedSections;
        this.comments = comments;
    }

    @Override
    public @NotNull String key() {
        return this.key;
    }

    @Override
    public @NotNull Optional<@NotNull ConfigSection> parent() {
        return Optional.ofNullable(this.parent);
    }

    @Override
    public void setParent(@Nullable ConfigSection parent) {
        this.parent = parent;
    }

    @Override
    public @NotNull List<@NotNull ConfigNode<?>> childNodes() {
        return Collections.unmodifiableList(this.childNodes);
    }

    @Override
    public void addChildNode(@NotNull ConfigNode<?> child) {
        this.childNodes.add(child);
    }

    @Override
    public void removeChildNode(@NotNull String key) {
        this.childNodes.removeIf((ConfigNode<?> node) -> node.key().equals(key));
    }

    @Override
    public @NotNull ConfigNodeIterator nodeIterator() {
        return new ConfigNodeIteratorImpl(this);
    }

    @Override
    public @NotNull List<@NotNull ConfigSection> nestedSections() {
        return Collections.unmodifiableList(this.nestedSections);
    }

    @Override
    public void addNestedSection(@NotNull ConfigSection section) {
        this.nestedSections.add(section);
    }

    @Override
    public void removeNestedSection(@NotNull String key) {
        this.nestedSections.removeIf((ConfigSection section) -> section.key().equals(key));
    }

    @Override
    public @NotNull ConfigSectionIterator sectionIterator() {
        return new ConfigSectionIteratorImpl(this);
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
}
