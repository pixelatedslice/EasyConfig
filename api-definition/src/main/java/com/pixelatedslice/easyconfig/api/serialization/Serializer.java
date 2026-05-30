package com.pixelatedslice.easyconfig.api.serialization;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.NodeBuilder;
import org.jspecify.annotations.NonNull;

public interface Serializer<T> extends Serialize<T>, Deserialize<T> {

    @NonNull
    TypeToken<T> type();

    @NonNull Node buildStructure(NodeBuilder.ContainerSafeStep.@NonNull Original builder);

}
