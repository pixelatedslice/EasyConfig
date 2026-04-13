package com.pixelatedslice.easyconfig.api.config.node;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.section.ConfigSectionIterator;
import com.pixelatedslice.easyconfig.api.config.section.WithNestedConfigSection;
import com.pixelatedslice.easyconfig.api.utils.type_token.TypeTokenUtils;
import org.jspecify.annotations.NonNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

public interface ConfigNodeIterator extends Iterator<ConfigNode<?>> {
    @SuppressWarnings("unchecked")
    static <T, C extends WithConfigNodeChildren & WithNestedConfigSection>
    @NonNull Optional<@NonNull ConfigNode<T>> find(
            @NonNull C rootContainer,
            @NonNull TypeToken<@NonNull T> typeToken, @NonNull String @NonNull ... providedKeys
    ) {
        Objects.requireNonNull(rootContainer);
        Objects.requireNonNull(typeToken);
        Objects.requireNonNull(providedKeys);

        if (providedKeys.length == 0) {
            return Optional.empty();
        }

        if (providedKeys.length == 1) {
            var nodeKey = providedKeys[0];
            for (var node : rootContainer.nodes()) {
                if (!node.key().equals(nodeKey)) {
                    continue;
                }
                if (!TypeTokenUtils.matches(node.typeToken(), typeToken)) {
                    throw new IllegalStateException(String.format(
                            "The node (%s) is not of the expected typeToken", nodeKey
                    ));
                }
                return Optional.of((ConfigNode<T>) node);
            }
            return Optional.empty();
        }
        String[] parentKeys = Arrays.copyOf(providedKeys, providedKeys.length - 1);
        var nodeKey = providedKeys[providedKeys.length - 1];
        var sectionOptional = ConfigSectionIterator.findSection(rootContainer.sections(), parentKeys);
        return sectionOptional.flatMap((@NonNull WithConfigNodeChildren section) -> section.node(typeToken, nodeKey));
    }

    static <C extends WithConfigNodeChildren & WithNestedConfigSection>
    @NonNull Optional<@NonNull TypeToken<?>> findTypeToken(
            @NonNull C rootContainer,
            @NonNull String @NonNull ... providedKeys
    ) {
        Objects.requireNonNull(rootContainer);
        Objects.requireNonNull(providedKeys);

        if (providedKeys.length == 0) {
            return Optional.empty();
        }

        if (providedKeys.length == 1) {
            var nodeKey = providedKeys[0];
            for (var node : rootContainer.nodes()) {
                if (!node.key().equals(nodeKey)) {
                    continue;
                }
                return Optional.of(node.typeToken());
            }
            return Optional.empty();
        }

        String[] parentKeys = Arrays.copyOf(providedKeys, providedKeys.length - 1);
        var nodeKey = providedKeys[providedKeys.length - 1];
        var sectionOptional = ConfigSectionIterator.findSection(rootContainer.sections(), parentKeys);
        return sectionOptional.flatMap((@NonNull WithConfigNodeChildren section) -> section.nodeTypeToken(nodeKey));
    }
}
