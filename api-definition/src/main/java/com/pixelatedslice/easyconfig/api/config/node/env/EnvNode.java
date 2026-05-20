package com.pixelatedslice.easyconfig.api.config.node.env;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.NodeBuilder;
import com.pixelatedslice.easyconfig.api.config.node.NodeType;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.api.validator.option.ValidateOption;
import com.pixelatedslice.easyconfig.api.validator.option.ValidationOptions;
import org.jspecify.annotations.NonNull;

import java.util.Optional;
import java.util.function.Function;

public interface EnvNode<T> extends Node {

    default @NonNull NodeType nodeType() {
        return NodeType.ENV_NODE;
    }

    @NonNull String envKey();

    default Optional<T> value() {
        return value(ValidationOptions.throwExceptions());
    }

    Optional<T> value(@NonNull ValidateOption<T> validateOption);

    @NonNull
    Function<String, T> adapter();

    Validator<T> validator();

    @NonNull TypeToken<T> typeToken();

    @Override
    NodeBuilder.@NonNull EnvFinalStep<T> toBuilder();
}
