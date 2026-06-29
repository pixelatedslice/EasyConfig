package com.pixelatedslice.easyconfig.api.config.node.env.builder;

import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.container.builder.ContainerNodeBuilderChildrenStep;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import org.jspecify.annotations.NullMarked;

import java.util.function.BiConsumer;
import java.util.function.Function;

@SuppressWarnings("unused")
@NullMarked
public interface EnvNodeBuilderSerializerStep<T> extends BuilderStep, EnvNodeBuilderValidatorStep<T> {
    EnvNodeBuilderValidatorStep<T> serializer(Serializer<T> serializer);

    EnvNodeBuilderValidatorStep<T> serializer(
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
        EnvNodeBuilderValidatorStep<T> serialize(
                BiConsumer<T, ContainerNodeBuilderChildrenStep> serialize
        );
    }

    @FunctionalInterface
    interface EndWithDeserializeStep<T> {
        EnvNodeBuilderValidatorStep<T> deserialize(
                Function<Node, T> deserialize
        );
    }
}