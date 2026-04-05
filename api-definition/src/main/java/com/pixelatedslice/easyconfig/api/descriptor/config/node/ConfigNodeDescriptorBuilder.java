package com.pixelatedslice.easyconfig.api.descriptor.config.node;

import com.pixelatedslice.easyconfig.api.descriptor.Descriptor;
import com.pixelatedslice.easyconfig.api.descriptor.config.DescriptorBuilderWithComments;
import com.pixelatedslice.easyconfig.api.descriptor.config.DescriptorBuilderWithConfigSectionParent;
import com.pixelatedslice.easyconfig.api.descriptor.config.DescriptorBuilderWithKey;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public interface ConfigNodeDescriptorBuilder<T> extends Descriptor.Builder<T, ConfigNodeDescriptor<T>>,
        DescriptorBuilderWithKey, DescriptorBuilderWithConfigSectionParent, DescriptorBuilderWithComments {
    @NonNull ConfigNodeDescriptorBuilder<T> defaultValue(@Nullable T defaultValue);

    @NonNull ConfigNodeDescriptor<T> build();
}
