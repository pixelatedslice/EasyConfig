package com.pixelatedslice.easyconfig.api.descriptor.config;

import org.jspecify.annotations.NonNull;

@FunctionalInterface
public interface DescriptorBuilderWithKey {
    @NonNull DescriptorBuilderWithKey key(@NonNull String key);
}
