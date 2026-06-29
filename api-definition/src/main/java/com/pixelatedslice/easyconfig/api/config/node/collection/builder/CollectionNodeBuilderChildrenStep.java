package com.pixelatedslice.easyconfig.api.config.node.collection.builder;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.container.builder.ContainerNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.env.builder.EnvNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.config.node.value.builder.ValueNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@NullMarked
public interface CollectionNodeBuilderChildrenStep extends BuilderStep, CollectionNodeBuilderFinalStep {
    <T> CollectionNodeBuilder valueNode(Consumer<? super ValueNodeBuilder<T>> valueNodeBuilder);

    <T> CollectionNodeBuilder valueNode(ValueNode<T> valueNode);

    /**
     * Call {@link CollectionNodeBuilderChildrenStep#valueNode(ValueNode)} to add the node
     *
     * @param <T> The type
     * @return A new {@link ValueNodeBuilder}
     */
    <T> ValueNodeBuilder<T> node();

    default <T> CollectionNodeBuilder valueNode(String key, TypeToken<T> typeToken,
            @Nullable T value) {
        return this.valueNode((ValueNodeBuilder<T> builder) -> builder.key(key).type(typeToken).value(value));
    }

    default <T> CollectionNodeBuilder valueNode(String key, Class<T> simpleType,
            @Nullable T value) {
        return this.valueNode((ValueNodeBuilder<T> builder) -> builder.key(key).type(simpleType).value(value));
    }

    <T> CollectionNodeBuilder envNode(Consumer<? super EnvNodeBuilder<T>> envNodeBuilder);

    <T> CollectionNodeBuilder envNode(EnvNode<T> envNode);

    /**
     * Call {@link CollectionNodeBuilderChildrenStep#envNode(EnvNode)} to add the node
     *
     * @param <T> The type
     * @return A new {@link EnvNodeBuilder}
     */
    <T> EnvNodeBuilder<T> envNode();

    default <T> CollectionNodeBuilder envNode(String key, TypeToken<T> typeToken,
            String environmentVariable) {
        return this.envNode((EnvNodeBuilder<T> builder) -> builder
                .key(key)
                .type(typeToken)
                .environmentVariable(environmentVariable));
    }

    default <T> CollectionNodeBuilder envNode(String key, Class<T> simpleType,
            String environmentVariable) {
        return this.envNode((EnvNodeBuilder<T> builder) -> builder
                .key(key)
                .type(simpleType)
                .environmentVariable(environmentVariable));
    }

    CollectionNodeBuilder containerNode(Consumer<? super ContainerNodeBuilder> containerNodeBuilder);

    CollectionNodeBuilder containerNode(ContainerNode containerNodeBuilder);

    /**
     * Call {@link CollectionNodeBuilderChildrenStep#containerNode(ContainerNode)} to add the ContainerNode
     *
     * @return A new {@link ContainerNodeBuilder}
     */
    ContainerNodeBuilder containerNode();

    CollectionNodeBuilder collectionNode(
            Consumer<? super CollectionNodeBuilder> containerNodeBuilder);

    CollectionNodeBuilder collectionNode(CollectionNode containerNodeBuilder);

    /**
     * Call {@link CollectionNodeBuilderChildrenStep#collectionNode(CollectionNode)} to add the ContainerNode
     *
     * @return A new {@link ContainerNodeBuilder}
     */
    CollectionNodeBuilder collectionNode();
}
