package com.pixelatedslice.easyconfig.impl.fileformat.serializer;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.serializer.SerializerNode;
import com.pixelatedslice.easyconfig.api.exception.SerializeException;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.serialization.context.SerializeContext;
import org.jspecify.annotations.NonNull;
import tools.jackson.core.JsonPointer;
import tools.jackson.databind.JsonNode;

public class StringSerializer implements Serializer<String> {
    @Override
    public @NonNull TypeToken<String> type() {
        return TypeToken.of(String.class);
    }

    @Override
    public void buildStructure(@NonNull SerializerNode node) {
        //SINGLE USE, NO NODE MANIPULATION
    }

    @Override
    public void deserialize(@NonNull String value, @NonNull SerializerNode builder, @NonNull SerializeContext context) {
        if (!(context instanceof SerializerContextImpl implContext)) {
            throw new IllegalStateException("Must be context from implementation");
        }
        var mapper = implContext.mapper();
        var objectNode = implContext.objectNode();
        objectNode.put(JsonPointer.compile(String.join(JsonPointer.SEPARATOR + "", builder.path())), mapper.stringNode(value));
    }

    @Override
    public @NonNull String serialize(@NonNull Node rootNode, @NonNull SerializerNode serializerNode, @NonNull SerializeContext context) throws SerializeException {
        if (!(context instanceof SerializerContextImpl implContext)) {
            throw new SerializeException.InvalidNodeTypeException(rootNode);
        }
        var path = serializerNode.path();
        JsonNode jsonNode = implContext.objectNode();
        for (int index = 1; index < path.length; index++) {
            var pathName = path[index];
            var nextJsonNode = jsonNode.findValue(pathName);
            if (nextJsonNode == null) { //could be wrong caps
                var opPropertyName = jsonNode.propertyNames().stream().filter(name -> name.equalsIgnoreCase(pathName)).findFirst();
                if (opPropertyName.isPresent()) {
                    nextJsonNode = jsonNode.findValue(opPropertyName.get());
                }
            }
            if (nextJsonNode == null) {
                throw new SerializeException.NoNodeException(serializerNode, index);
            }
            jsonNode = nextJsonNode;
        }
        return jsonNode.asString();
    }
}
