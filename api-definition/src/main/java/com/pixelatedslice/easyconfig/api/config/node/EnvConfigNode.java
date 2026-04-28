package com.pixelatedslice.easyconfig.api.config.node;

public interface EnvConfigNode<T> extends ConfigNode<T> {
    String envKey();
}
