package com.pixelatedslice.easyconfig.api.exception;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.serializer.SerializerNode;
import org.jspecify.annotations.NullMarked;

import java.util.Arrays;


@NullMarked
public abstract class SerializeException extends Exception {
    protected SerializeException(String message) {
        super(message);
    }

    public static class GenericException extends SerializeException {
        public GenericException(String message) {
            super(message);
        }
    }

    public static class InvalidNodeTypeException extends SerializeException {
        public InvalidNodeTypeException(Node node) {
            super("Invalid node type of " + node.nodeType().name());
        }
    }

    public static class NoNodeException extends SerializeException {
        public NoNodeException(SerializerNode node, int index) {
            super("Cannot find node with path: " + String.join(" -> ", Arrays.copyOfRange(node.path(), 0, index)));
        }
    }

}
