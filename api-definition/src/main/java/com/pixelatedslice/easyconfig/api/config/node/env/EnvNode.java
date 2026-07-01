package com.pixelatedslice.easyconfig.api.config.node.env;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.NodeType;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.for_impl.ForImplNode;
import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.api.validator.option.ValidateOption;
import com.pixelatedslice.easyconfig.api.validator.option.ValidationOptions;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;

@NullMarked
public non-sealed interface EnvNode<T> extends Node, ForImplNode {

    default NodeType nodeType() {
        return NodeType.ENV_NODE;
    }

    String envKey();

    default Optional<T> value() {
        return this.value(ValidationOptions.throwExceptions());
    }

    Optional<T> value(ValidateOption<T> validateOption);


    Function<String, @Nullable T> adapter();

    Validator<T> validator();

    TypeToken<T> typeToken();

    @Override
    FactoryNodeBuilderKeySteps.Env<T> toBuilder();
}
