package com.pixelatedslice.easyconfig.api.config.node.value;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.NodeType;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.for_impl.ForImplNode;
import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import com.pixelatedslice.easyconfig.api.editable.Editable;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.api.validator.option.ValidateOption;
import com.pixelatedslice.easyconfig.api.validator.option.ValidationOptions;
import org.jspecify.annotations.NullMarked;

import java.util.Optional;

@NullMarked
public non-sealed interface ValueNode<T> extends Node, ForImplNode, Editable<EditableValueNode<T>> {

    @Override
    FactoryNodeBuilderKeySteps.Value<T> toBuilder();

    default NodeType nodeType() {
        return NodeType.VALUE_NODE;
    }

    default Optional<T> value() {
        return this.value(ValidationOptions.throwExceptions());
    }

    Optional<T> value(ValidateOption<T> option);

    Optional<T> defaultValue();

    default Optional<T> valueOrDefault() {
        return this.value().or(this::defaultValue);
    }

    Optional<Serializer<T>> serializer();

    Validator<T> validator();

    TypeToken<T> typeToken();

}