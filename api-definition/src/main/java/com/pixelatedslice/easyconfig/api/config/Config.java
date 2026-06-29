package com.pixelatedslice.easyconfig.api.config;

import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@NullMarked
public interface Config {
    Node root();
}
