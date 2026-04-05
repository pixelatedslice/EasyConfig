package com.pixelatedslice.easyconfig.api.config.node;

import com.google.common.reflect.TypeToken;
import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.ServiceLoader;

public interface WithConfigNodeChildren {
    @NonNull Collection<@NonNull ConfigNode<?>> nodes();

    <T> @NonNull Optional<@NonNull ConfigNode<T>> node(
            @NonNull TypeToken<@NonNull T> typeToken,
            @NonNull String... providedKeys
    );

    <T> @NonNull Optional<@NonNull ConfigNode<T>> nodeButInTheBukkitAPIStyle(
            @NonNull TypeToken<@NonNull T> typeToken,
            @NonNull String key
    );

    @NonNull WithConfigNodeChildren addNode(@NonNull ConfigNode<?> child);

    @NonNull WithConfigNodeChildren removeNode(@NonNull String key);

    default @NonNull ConfigNodeIterator nodeIterator() {
        return ServiceLoader.load(ConfigNodeIterator.class).findFirst().orElseThrow();
    }
}

