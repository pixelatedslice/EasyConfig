package com.pixelatedslice.easyconfig.api.config.node.container.builder;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import com.pixelatedslice.easyconfig.api.config.node.collection.CollectionNode;
import com.pixelatedslice.easyconfig.api.config.node.collection.builder.CollectionNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.config.node.env.builder.EnvNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.config.node.value.builder.ValueNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@NullMarked
public interface ContainerNodeBuilderChildrenStep extends BuilderStep, ContainerNodeBuilderFinalStep {
    <T> ContainerNodeBuilderChildrenStep valueNode(
            Consumer<? super ValueNodeBuilder<T>> valueNodeBuilder);

    <T> ContainerNodeBuilderChildrenStep valueNode(ValueNode<T> valueNode);

    /**
     * Call {@link ContainerNodeBuilderChildrenStep#valueNode(ValueNode)} to add the node.
     *
     * @param <T> The type
     * @return A new {@link ValueNodeBuilder}
     */
    <T> ValueNodeBuilder<T> node();

    default <T> ContainerNodeBuilderChildrenStep valueNode(String key,
            TypeToken<T> typeToken,
            @Nullable T value) {
        return this.valueNode((ValueNodeBuilder<T> builder) -> builder.key(key).type(typeToken).value(value));
    }

    default <T> ContainerNodeBuilderChildrenStep valueNode(String key, Class<T> simpleType,
            @Nullable T value) {
        return this.valueNode((ValueNodeBuilder<T> builder) -> builder.key(key).type(simpleType).value(value));
    }

    <T> ContainerNodeBuilderChildrenStep envNode(Consumer<? super EnvNodeBuilder<T>> envNodeBuilder);

    <T> ContainerNodeBuilderChildrenStep envNode(EnvNode<T> envNode);

    /**
     * Call {@link ContainerNodeBuilderChildrenStep#envNode(EnvNode)} to add the node.
     *
     * @param <T> The type
     * @return A new {@link EnvNodeBuilder}
     */
    <T> EnvNodeBuilder<T> envNode();

    default <T> ContainerNodeBuilderChildrenStep envNode(String key, TypeToken<T> typeToken,
            String environmentVariable) {
        return this.envNode((EnvNodeBuilder<T> builder) -> builder
                .key(key)
                .type(typeToken)
                .environmentVariable(environmentVariable));
    }

    default <T> ContainerNodeBuilderChildrenStep envNode(String key, Class<T> simpleType,
            String environmentVariable) {
        return this.envNode((EnvNodeBuilder<T> builder) -> builder
                .key(key)
                .type(simpleType)
                .environmentVariable(environmentVariable));
    }

    ContainerNodeBuilderChildrenStep containerNode(
            Consumer<? super ContainerNodeBuilder> containerNodeBuilder);

    ContainerNodeBuilderChildrenStep containerNode(ContainerNode containerNodeBuilder);

    /**
     * Call {@link ContainerNodeBuilderChildrenStep#containerNode(ContainerNode)} to add the ContainerNode.
     *
     * @return A new {@link ContainerNodeBuilder}
     */
    ContainerNodeBuilder containerNode();

    ContainerNodeBuilderChildrenStep collectionNode(
            Consumer<? super CollectionNodeBuilder> containerNodeBuilder);

    ContainerNodeBuilderChildrenStep collectionNode(CollectionNode containerNodeBuilder);

    /**
     * Call {@link ContainerNodeBuilderChildrenStep#collectionNode(CollectionNode)} to add the ContainerNode.
     *
     * @return A new {@link CollectionNodeBuilder}
     */
    CollectionNodeBuilder collectionNode();
}