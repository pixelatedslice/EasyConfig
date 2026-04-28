package com.pixelatedslice.easyconfig.api;

import java.util.NoSuchElementException;
import java.util.ServiceLoader;

final class EasyConfigProvider {
    static final EasyConfig INSTANCE = ServiceLoader.load(EasyConfig.class)
            .findFirst()
            .orElseThrow(() ->
                    new NoSuchElementException("No implementation for EasyConfig found on the classpath.")
            );

    private EasyConfigProvider() {
    }
}
