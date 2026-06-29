package com.pixelatedslice.easyconfig.api.serialization;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.NodeBuilder;
import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
@NullMarked
public interface Serializer<T> extends Serialize<T>, Deserialize<T> {


    TypeToken<T> type();

    Node buildStructure(NodeBuilder.ContainerSafeStep.Original builder);

}
