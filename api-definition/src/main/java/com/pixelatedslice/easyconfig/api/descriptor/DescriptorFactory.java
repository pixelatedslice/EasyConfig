package com.pixelatedslice.easyconfig.api.descriptor;

import com.google.common.reflect.TypeToken;
import org.jspecify.annotations.NonNull;

public interface DescriptorFactory {
    <T> @NonNull Descriptor<T> create(@NonNull TypeToken<T> typeToken);
}
