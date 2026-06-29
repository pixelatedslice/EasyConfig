package com.pixelatedslice.easyconfig.api.config.node;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import org.jspecify.annotations.NullMarked;

import java.util.Optional;

@SuppressWarnings("unused")
@NullMarked
public interface ReturnedNode {
    Optional<Node> plainNode();

    Optional<ContainerNode> container();

    Optional<CollectionNode> collectionNode();

    <T> Optional<ValueNode<T>> value(Class<T> simpleType);

    <T> Optional<ValueNode<T>> value(TypeToken<T> typeToken);

    Optional<ValueNode<?>> unsafeValue();

    <T> Optional<EnvNode<T>> env(Class<T> simpleType);

    <T> Optional<EnvNode<T>> env(TypeToken<T> typeToken);

    Optional<EnvNode<?>> unsafeEnv();
}
