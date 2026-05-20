package com.pixelatedslice.easyconfig.api.serialization;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.exception.SerializeException;
import org.jspecify.annotations.NonNull;

@FunctionalInterface
public interface Serialize<T> {

    @NonNull T serialize(@NonNull Node rootNode, @NonNull SerializeContext context) throws SerializeException;

}
