package com.pixelatedslice.easyconfig.api.config;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import org.jspecify.annotations.NullMarked;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import org.jspecify.annotations.NonNull;

@NullMarked
public interface Config {
    @NonNull Node root();

    @NonNull SerializerRegistry serializers();

}
