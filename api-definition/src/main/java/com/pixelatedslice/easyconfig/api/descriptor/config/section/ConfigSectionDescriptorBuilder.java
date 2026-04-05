package com.pixelatedslice.easyconfig.api.descriptor.config.section;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.descriptor.Descriptor;
import com.pixelatedslice.easyconfig.api.descriptor.config.DescriptorBuilderWithComments;
import com.pixelatedslice.easyconfig.api.descriptor.config.DescriptorBuilderWithConfigSectionParent;
import com.pixelatedslice.easyconfig.api.descriptor.config.DescriptorBuilderWithKey;
import com.pixelatedslice.easyconfig.api.descriptor.config.node.DescriptorBuilderWithNodeChildren;
import org.jspecify.annotations.NonNull;

public interface ConfigSectionDescriptorBuilder
        extends Descriptor.Builder<ConfigSectionDescriptor, ConfigSectionDescriptor>, DescriptorBuilderWithKey,
        DescriptorBuilderWithConfigSectionParent, DescriptorBuilderWithComments, DescriptorBuilderWithNodeChildren,
        DescriptorBuilderWithNestedSections {
    @Override
    default @NonNull ConfigSectionDescriptorBuilder typeToken(
            @NonNull TypeToken<ConfigSectionDescriptor> typeToken) {
        throw new UnsupportedOperationException();
    }
}
