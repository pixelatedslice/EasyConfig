package com.pixelatedslice.easyconfig.api.descriptor.config;

import com.pixelatedslice.easyconfig.api.descriptor.config.section.ConfigSectionDescriptor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@FunctionalInterface
public interface DescriptorBuilderWithConfigSectionParent {
    @NonNull DescriptorBuilderWithConfigSectionParent parent(@Nullable ConfigSectionDescriptor parent);
}
