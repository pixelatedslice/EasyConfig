package com.pixelatedslice.easyconfig.api.config.node.env;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.NodeBuilder;
import com.pixelatedslice.easyconfig.api.config.node.NodeType;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.api.validator.option.ValidateOption;
import com.pixelatedslice.easyconfig.api.validator.option.ValidationOptions;
import org.jspecify.annotations.NullMarked;

import java.util.Optional;
import java.util.function.Function;

@NullMarked
public interface EnvNode<T> extends Node {

    default NodeType nodeType() {
        return NodeType.ENV_NODE;
    }

    String envKey();

    default Optional<T> value() {
        return this.value(ValidationOptions.throwExceptions());
    }

    Optional<T> value(ValidateOption<T> validateOption);


    Function<String, T> adapter();

    Validator<T> validator();

    TypeToken<T> typeToken();

    @Override
    NodeBuilder.EnvFinalStep<T> toBuilder();
}
