package com.pixelatedslice.easyconfig.impl.fileformat;

import com.pixelatedslice.easyconfig.api.config.BuiltConfig;
import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.exception.SerializeException;
import com.pixelatedslice.easyconfig.api.exception.SerializerException;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import com.pixelatedslice.easyconfig.api.serialization.context.ContextProperty;
import com.pixelatedslice.easyconfig.api.validator.option.ValidationOptions;
import com.pixelatedslice.easyconfig.impl.config.ConfigStructureImpl;
import com.pixelatedslice.easyconfig.impl.config.node.AbstractNode;
import com.pixelatedslice.easyconfig.impl.fileformat.serializer.NodeSerializerImpl;
import com.pixelatedslice.easyconfig.impl.fileformat.serializer.SerializerContextImpl;
import org.jspecify.annotations.NullMarked;
import tools.jackson.core.JsonPointer;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.Map;
import java.util.Objects;


@NullMarked
public class ConfigUtils {

    public static BuiltConfig readIntoDataMapper(ConfigStructureImpl config, ObjectMapper mapper, ObjectNode node) {
        Objects.requireNonNull(config);
        Objects.requireNonNull(mapper);
        Objects.requireNonNull(node);
        var builtConfig = config.toBuilt();
        var valueNodes = AbstractNode.walk((AbstractNode) builtConfig.root()).filter(targetNode -> targetNode instanceof ValueNode<?>).map(targetNode -> (ValueNode<?>) targetNode).toList();
        for (var valueNode : valueNodes) {
            try {
                read(valueNode, mapper, node, config.serializers());
            } catch (SerializeException e) {
                throw new RuntimeException(e);
            }
        }
        return builtConfig;
    }

    private static <T> void read(ValueNode<T> valueNode, ObjectMapper mapper, ObjectNode objectNode, SerializerRegistry serializers) throws SerializeException {
        var serializer = valueNode.serializer().or(() -> serializers.serializerFor(valueNode.typeToken())).orElseThrow(() -> new SerializerException.MissingSerializerException(valueNode));
        var context = new SerializerContextImpl(mapper, objectNode, Map.of());
        var nodePath = valueNode.fullPath();
        if (nodePath.length != 0 && nodePath[0].isEmpty()) {
            nodePath[0] = JsonPointer.SEPARATOR + "";
        }
        if (nodePath.length == 0 || !nodePath[0].equals(JsonPointer.SEPARATOR + "")) {
            var newPath = new String[nodePath.length + 1];
            newPath[0] = "" + JsonPointer.SEPARATOR;
            System.arraycopy(nodePath, 0, newPath, 1, nodePath.length);
            nodePath = newPath;
        }
        var nodeSerializer = new NodeSerializerImpl(serializers, mapper, objectNode, valueNode, nodePath);

        var result = serializer.serialize(valueNode, nodeSerializer, context);
        try (var editable = valueNode.editable()) {
            editable.setValue(result);
        }
    }

    public static Map.Entry<ObjectMapper, ObjectNode> writeToDataMapper(Config config, Map<ContextProperty<?>, Object> properties) {
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

    private static <T> boolean write(Config config, ValueNode<T> node, ObjectMapper mapper, ObjectNode objectNode, Map<ContextProperty<?>, Object> properties) {
        var opValue = node.valueOrDefault(ValidationOptions.ignoreValidation());
        if (opValue.isEmpty()) {
            //node is being erased
            return false;
        }
        var serializer = node.serializer().or(() -> config.serializers().serializerFor(node.typeToken())).orElseThrow(() -> new SerializerException.MissingSerializerException(node));
        var context = new SerializerContextImpl(mapper, objectNode, properties);
        var nodePath = node.fullPath();
        if (nodePath.length == 0 || !nodePath[0].equals(JsonPointer.SEPARATOR + "")) {
            var newPath = new String[nodePath.length + 1];
            newPath[0] = "" + JsonPointer.SEPARATOR;
            System.arraycopy(nodePath, 0, newPath, 1, nodePath.length);
            nodePath = newPath;
        }
        var nodeSerializer = new NodeSerializerImpl(config.serializers(), mapper, objectNode, node, nodePath);
        serializer.deserialize(opValue.get(), nodeSerializer, context);
        return true;
    }
}
