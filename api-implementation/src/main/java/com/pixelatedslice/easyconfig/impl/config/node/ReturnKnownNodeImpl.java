package com.pixelatedslice.easyconfig.impl.config.node;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.ReturnedNode;
import com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

@NullMarked
public class ReturnKnownNodeImpl implements ReturnedNode {

    private final @Nullable Node node;

    public ReturnKnownNodeImpl(@Nullable Node node) {
        this.node = node;
    }

    @Override
    public Optional<Node> plainNode() {
        return Optional.ofNullable(this.node);
    }

    private <N extends Node> Optional<N> node(Class<N> instanceOf) {
        return this.plainNode().filter(instanceOf::isInstance).map(node -> (N) node);
    }

    @Override
    public Optional<ContainerNode> container() {
        return this.node(ContainerNode.class);
    }

    @Override
    public Optional<CollectionNode> collectionNode() {
        return this.node(CollectionNode.class);
    }

    @Override
    public <T> Optional<ValueNode<T>> value(Class<T> simpleType) {
        return this.value(TypeToken.of(simpleType));
    }

    @Override
    public <T> Optional<ValueNode<T>> value(TypeToken<T> typeToken) {
        //noinspection unchecked
        return this.node(ValueNode.class)
                .filter(valueNode -> valueNode.typeToken().equals(typeToken))
                .map(valueNode -> (ValueNode<T>) valueNode);

    }

    @Override
    public Optional<ValueNode<?>> unsafeValue() {
        return this.node(ValueNode.class).map(valueNode -> (ValueNode<?>) valueNode);
    }

    @Override
    public <T> Optional<EnvNode<T>> env(Class<T> simpleType) {
        return this.env(TypeToken.of(simpleType));
    }

    @Override
    public <T> Optional<EnvNode<T>> env(TypeToken<T> typeToken) {
        //noinspection unchecked
        return this.node(EnvNode.class)
                .filter(envNode -> envNode.typeToken().equals(typeToken))
                .map(envNode -> (EnvNode<T>) envNode);
    }

    @Override
    public Optional<EnvNode<?>> unsafeEnv() {
        return this.node(EnvNode.class).map(node -> (EnvNode<?>) node);
    }
}
