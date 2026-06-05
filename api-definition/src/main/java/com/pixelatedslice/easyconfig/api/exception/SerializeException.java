package com.pixelatedslice.easyconfig.api.exception;

import org.jspecify.annotations.NullMarked;


@NullMarked
public abstract class SerializeException extends Exception {
    protected SerializeException(String message) {
        super(message);
    }

    public static class GenericException extends SerializeException {
        public GenericException(@NonNull String message) {
            super(message);
        }
    }

    public static class InvalidNodeTypeException extends SerializeException {
        public InvalidNodeTypeException(@NonNull Node node) {
            super("Invalid node type of " + node.nodeType().name());
        }
    }

    public static class NoNodeException extends SerializeException {
        public NoNodeException(@NonNull SerializerNode node, int index) {
            super("Cannot find node with path: " + String.join(" -> ", Arrays.copyOfRange(node.path(), 0, index)));
        }
    }

}
