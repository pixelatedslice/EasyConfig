package com.pixelatedslice.easyconfig.impl.fileformat;

import org.jspecify.annotations.NullMarked;

import java.util.Objects;


@NullMarked
public class ConfigUtils {

    public static Map. @NonNull Entry<ObjectMapper, ObjectNode> writeToDataMapper(@NonNull Config config, @NonNull Map<ContextProperty<?>, Object> properties) {
        Objects.requireNonNull(config);
        Objects.requireNonNull(properties);
        var mapper = new ObjectMapper();
        var objectNode = mapper.createObjectNode();

        var root = config.root();
        if (!(root instanceof AbstractNode abstractRoot)) {
            throw new IllegalArgumentException("Must implement AbstractNode");
        }
        var walk = AbstractNode.walk(abstractRoot);
        var valueNodes = walk.filter(node -> node instanceof ValueNode<?>).map(node -> (ValueNode<?>) node).toList();
        for (var valueNode : valueNodes) {
            if (!write(config, valueNode, mapper, objectNode, properties)) {
                continue;
            }
        }
        return Map.entry(mapper, objectNode);
    }

    private static <T> boolean write(@NonNull Config config, @NonNull ValueNode<T> node, @NonNull ObjectMapper mapper, @NonNull ObjectNode objectNode, @NonNull Map<ContextProperty<?>, Object> properties) {
        var opValue = node.valueOrDefault(ValidationOptions.ignoreValidation());
        if (opValue.isEmpty()) {
            //node is being erased
            return false;
        }
        var serializer = node.serializer().orElseThrow(() -> new SerializerException.MissingSerializerException(node));
        var context = new SerializerContextImpl(mapper, objectNode, properties);
        var nodePath = node.fullPath();
        if(nodePath.length == 0 || !nodePath[0].equals(JsonPointer.SEPARATOR + "")){
            var newPath = new String[nodePath.length + 1];
            newPath[0] = "" + JsonPointer.SEPARATOR;
            System.arraycopy(nodePath, 0, newPath, 1, nodePath.length);
            nodePath = newPath;

        }
        var nodeSerializer = new NodeSerializerImpl(config.serializers(), mapper, objectNode, nodePath);
        serializer.deserialize(opValue.get(), nodeSerializer, context);
        return true;
    }
}
