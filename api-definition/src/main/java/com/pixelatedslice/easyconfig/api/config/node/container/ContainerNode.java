package com.pixelatedslice.easyconfig.api.config.node.container;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.NodeType;
import com.pixelatedslice.easyconfig.api.config.node.ReturnedNode;
import com.pixelatedslice.easyconfig.api.config.node.builder.builder.FactoryNodeBuilderKeySteps;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.for_impl.ForImplNode;
import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.editable.Editable;
import com.pixelatedslice.easyconfig.api.utils.typetoken.TypeTokenUtils;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unused")
@NullMarked
public non-sealed interface ContainerNode extends Node, ForImplNode, Editable<EditableContainerNode> {

    default NodeType nodeType() {
        return NodeType.CONTAINER_NODE;
    }

    @Override
    FactoryNodeBuilderKeySteps.Container toBuilder();

    ImmutableList<Node> children();

    boolean isRootNode();

    ReturnedNode node(String... path);

    default Optional<ContainerNode> containerNode(String... path) {
        return this.node(path).container();
    }

    default <T> Optional<ValueNode<T>> valueNode(
            Class<T> simpleType,
            String... path
    ) {
        Objects.requireNonNull(simpleType);
        Objects.requireNonNull(path);

        return this.valueNode(TypeTokenUtils.getSimpleOrThrow(simpleType), path);
    }

    default <T> Optional<ValueNode<T>> valueNode(
            TypeToken<T> typeToken,
            String... path
    ) {
        Objects.requireNonNull(typeToken);
        Objects.requireNonNull(path);

        return this.node(path).value(typeToken);
    }

    default <T> Optional<EnvNode<T>> envNode(
            Class<T> simpleType,
            String... path
    ) {
        Objects.requireNonNull(simpleType);
        Objects.requireNonNull(path);

        return this.envNode(TypeTokenUtils.getSimpleOrThrow(simpleType), path);
    }

    default <T> Optional<EnvNode<T>> envNode(
            TypeToken<T> typeToken,
            String... path
    ) {
        Objects.requireNonNull(typeToken);
        Objects.requireNonNull(path);

        return this.node(path).env(typeToken);
    }
}
