package com.pixelatedslice.easyconfig.api.descriptor;

import org.jspecify.annotations.NonNull;

@FunctionalInterface
public interface WithDescriptor<T extends Descriptor<?>> {
    @NonNull T descriptor();
}
