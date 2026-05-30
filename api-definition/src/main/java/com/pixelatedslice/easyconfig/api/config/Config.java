package com.pixelatedslice.easyconfig.api.config;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import org.jspecify.annotations.NonNull;

public interface Config {
    @NonNull Node root();
}
