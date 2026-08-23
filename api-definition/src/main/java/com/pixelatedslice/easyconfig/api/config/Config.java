package com.pixelatedslice.easyconfig.api.config;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface Config {
    Node root();

    SerializerRegistry serializers();

}
