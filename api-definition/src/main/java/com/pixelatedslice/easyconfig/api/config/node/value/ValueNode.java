package com.pixelatedslice.easyconfig.api.config.node.value;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.NodeType;
import com.pixelatedslice.easyconfig.api.config.node.factory.Nodes;
import com.pixelatedslice.easyconfig.api.config.node.factory.builder.NodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.editable.Editable;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.utils.typetoken.TypeTokenUtils;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.api.validator.option.ValidateOption;
import com.pixelatedslice.easyconfig.api.validator.option.ValidationOptions;
import org.jspecify.annotations.NullMarked;

import java.util.Optional;

@NullMarked
public interface ValueNode<T> extends Node, Editable<EditableValueNode<T>> {
    static NodeBuilderKeySteps.Value<?> of(Class<?> simpleType) {
        return of(TypeTokenUtils.getSimpleOrThrow(simpleType));
    }

    static NodeBuilderKeySteps.Value<?> of(TypeToken<?> typeToken) {
        return Nodes.value(typeToken);
    }

    @Override
    NodeBuilderKeySteps.Value<T> toBuilder();

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