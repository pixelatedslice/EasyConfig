package com.pixelatedslice.easyconfig.api.exception;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.NodeType;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@SuppressWarnings("unused")
@NullMarked
public class NodeException extends RuntimeException {
    public NodeException(String message) {
        Objects.requireNonNull(message);

        super(message);
    }

    public NodeException(String message, Object... args) {
        Objects.requireNonNull(message);
        Objects.requireNonNull(args);

        super(String.format(message, args));
    }

    public static NodeException DID_NOT_EXPECT_NODE_TYPE(
            String key,
            NodeType expected,
            NodeType received
    ) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(expected);
        Objects.requireNonNull(received);

        return new NodeException(
                "Type mismatch for node '%s': Expected %s but encountered %s.",
                key, expected.toString(), received
        );
    }

    public static NodeException DID_NOT_EXPECT_NODE_TYPE_EXPECTED_VALUE_NODE_BASED(
            String key,
            NodeType received
    ) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(received);

        return new NodeException(
                "Type mismatch for node '%s': Expected %s but encountered %s.",
                key, String.join(" / ", NodeType.valueNodeBased()), received
        );
    }

    public static NodeException NODE_HAS_WRONG_VALUE_TYPE(String key, TypeToken<?> nodeType,
            String expectedType) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(nodeType);
        Objects.requireNonNull(expectedType);

        return new NodeException("Value type mismatch for node '%s': Expected %s but found %s.",
                key, expectedType, nodeType.toString()
        );
    }
}
