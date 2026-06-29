package com.pixelatedslice.easyconfig.api.config.node.value.builder;

import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.container.builder.ContainerNodeBuilderChildrenStep;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import org.jspecify.annotations.NullMarked;

import java.util.function.BiConsumer;
import java.util.function.Function;

@SuppressWarnings("unused")
@NullMarked
public interface ValueNodeBuilderSerializerStep<T> extends BuilderStep, ValueNodeBuilderValidatorStep<T> {
    ValueNodeBuilderValidatorStep<T> serializer(Serializer<T> serializer);

    ValueNodeBuilderValidatorStep<T> serializer(
            BiConsumer<T, ContainerNodeBuilderChildrenStep> serialize,
            Function<Node, T> deserialize
    );

    EndWithDeserializeStep<T> serialize(
            BiConsumer<T, ContainerNodeBuilderChildrenStep> serialize
    );

    EndWithSerializeStep<T> deserialize(
            Function<Node, T> deserialize
    );

    @FunctionalInterface
    interface EndWithSerializeStep<T> {
        ValueNodeBuilderValidatorStep<T> serialize(
                BiConsumer<T, ContainerNodeBuilderChildrenStep> serialize
        );
    }

    @FunctionalInterface
    interface EndWithDeserializeStep<T> {
        ValueNodeBuilderValidatorStep<T> deserialize(
                Function<Node, T> deserialize
        );
    }
}