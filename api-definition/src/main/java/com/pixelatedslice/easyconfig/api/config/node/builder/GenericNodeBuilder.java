package com.pixelatedslice.easyconfig.api.config.node.builder;

import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import org.jspecify.annotations.NullMarked;

@FunctionalInterface
@NullMarked
public interface GenericNodeBuilder<Next extends BuilderStep> {
    Next key(String key);
}
