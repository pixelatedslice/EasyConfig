package com.pixelatedslice.easyconfig.impl.test.config.node;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.Nodes;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@NullMarked
public class ValueNodeBuilderTests {
    @Test
    public void NodeBuilder_should_build_value_node_with_expected() {
        //ARRANGE
        final String key = "My Key";
        final TypeToken<String> type = TypeToken.of(String.class);
        final var defaultValue = "default value";
        final var value = "value";

        //ACT
        final var node = Nodes.value(String.class)
                .key(key)
                .defaultValue(defaultValue)
                .value(value)
                .build();

        //ASSERT
        Assertions.assertEquals(key, node.key());
        Assertions.assertEquals(type, node.typeToken());
        Assertions.assertEquals(defaultValue, node.defaultValue().orElse(null));
        Assertions.assertEquals(value, node.value().orElse(null));
    }

    @Test
    public void NodeBuilder_should_throw_exception_with_null_key() {
        //ARRANGE

        //ACT - ASSERT
        //noinspection DataFlowIssue
        Assertions.assertThrows(NullPointerException.class, () ->
                Nodes.value(String.class).key(null).build());
    }

    @Test
    public void NodeBuilder_should_throw_exception_with_null_typetoken() {
        //ARRANGE
        final var key = "My Key";

        System.getenv("");

        //ACT - ASSERT
        // Old: Assertions.assertThrows(NullPointerException.class, () ->
        //         this.builder()
        //                 .key(key)
        //                 .of((TypeToken<?>) null)
        //                 .build());
        //noinspection DataFlowIssue
        Assertions.assertThrows(NullPointerException.class, () -> Nodes.value((TypeToken<?>) null).key(key));
    }

    @Test
    public void NodeBuilder_should_append_value_with_provided_key() {
        //ARRANGE
        final var key = "My first key";
        final var secondKey = "My second key";
        final var type = TypeToken.of(String.class);

        //ACT
        // final var builder = this.builder().key(key).append(secondKey).of(String.class);
        // final OldNodeBuilder.ContainerFinalStep.Original originalBuilder = builder.complete();
        final ContainerNode result = Nodes.container(key).children(
                Nodes.value(String.class).key(secondKey)
        ).build();

        //ASSERT
        Assertions.assertEquals(key, result.key());

        final Optional<ValueNode<String>> opNode = result.valueNode(String.class, secondKey);
        Assertions.assertTrue(opNode.isPresent());
        Assertions.assertEquals(secondKey, opNode.get().key());
        Assertions.assertInstanceOf(ValueNode.class, opNode.get());
        Assertions.assertEquals(type, opNode.get().typeToken());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void NodeBuilder_should_update_value() {
        //ARRANGE
        final var key = "My first key";
        final var oldValue = "Old value";
        final var newValue = "Old value";
        final var type = TypeToken.of(String.class);

        //ACT
        final var result = Nodes.value(type).key(key).value(oldValue).build();
        final var beforeUpdating = result.value().get();

        try (var editable = result.editable()) {
            editable.setValue(newValue);
        }

        final var afterUpdating = result.value().get();

        //ASSERT
        Assertions.assertEquals(key, result.key());
        Assertions.assertEquals(type, result.typeToken());
        Assertions.assertEquals(oldValue, beforeUpdating);
        Assertions.assertEquals(newValue, afterUpdating);
    }
}
