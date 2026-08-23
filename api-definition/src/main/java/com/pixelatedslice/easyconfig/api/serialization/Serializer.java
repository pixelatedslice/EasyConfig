package com.pixelatedslice.easyconfig.api.serialization;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.serializer.SerializerNode;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface Serializer<T> extends Serialize<T>, Deserialize<T> {

    TypeToken<T> type();

    void buildStructure(SerializerNode builder);
}
