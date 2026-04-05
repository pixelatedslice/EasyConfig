package com.pixelatedslice.easyconfig.api.config.section;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.ConfigNode;
import com.pixelatedslice.easyconfig.api.config.node.ConfigNodeIterator;
import com.pixelatedslice.easyconfig.api.config.node.WithConfigNodeChildren;
import com.pixelatedslice.easyconfig.api.descriptor.WithDescriptor;
import com.pixelatedslice.easyconfig.api.descriptor.config.section.ConfigSectionDescriptor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.ServiceLoader;

public interface ConfigSection extends WithConfigNodeChildren, WithNestedConfigSection,
        WithDescriptor<ConfigSectionDescriptor> {
    static @NonNull ConfigSectionBuilder builder() {
        return ServiceLoader.load(ConfigSectionBuilder.class).findFirst().orElseThrow();
    }

    @NonNull Optional<@NonNull ConfigSection> parent();

    void setParent(@Nullable ConfigSection parent);

    @Override
    default @NonNull <T> Optional<@NonNull ConfigNode<T>> node(
            @NonNull TypeToken<T> typeToken,
            @NonNull String... providedKeys
    ) {
        return ConfigNodeIterator.findNode(this, typeToken, providedKeys);
    }

    @Override
    @NonNull
    default <T> Optional<@NonNull ConfigNode<T>> nodeButInTheBukkitAPIStyle(
            @NonNull TypeToken<T> typeToken,
            @NonNull String key
    ) {
        return ConfigNodeIterator.findNodeButInTheBukkitAPIStyle(this, typeToken, key);
    }
}