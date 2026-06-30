package com.pixelatedslice.easyconfig.impl.test.config.node;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.builder.OldNodeBuilder;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@NullMarked
public class ValueNodeBuilderTests {

    private OldNodeBuilder.FirstStep builder() {
        return new ContainerNodeOriginalBuilder();
    }

    @Test
    public void NodeBuilder_should_build_value_node_with_expected() {
        //ARRANGE
        final String key = "My Key";
        final TypeToken<String> type = TypeToken.of(String.class);
        final var defaultValue = "default value";
        final var value = "value";

        //ACT
        final var node = this.builder()
                .key(key)
                .of(String.class)
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
                this.builder().key(null).build());
    }

    @Test
    public void NodeBuilder_should_throw_exception_with_null_typetoken() {
        //ARRANGE
        final var key = "My Key";

        System.getenv("");

        //ACT - ASSERT
        //noinspection DataFlowIssue
        Assertions.assertThrows(NullPointerException.class, () ->
                this.builder()
                        .key(key)
                        .of((TypeToken<?>) null)
                        .build());
    }

    @Test
    public void NodeBuilder_should_append_value_with_provided_key() {
        //ARRANGE
        final var key = "My first key";
        final var secondKey = "My second key";
        final var type = TypeToken.of(String.class);

        //ACT
        final var builder = this.builder().key(key).append(secondKey).of(String.class);
        final OldNodeBuilder.ContainerFinalStep.Original originalBuilder = builder.complete();
        final ContainerNode result = originalBuilder.build();

        //ASSERT
        Assertions.assertEquals(key, result.key());

        final Optional<ValueNode<String>> opNode = result.valueNode(String.class, secondKey);
        Assertions.assertTrue(opNode.isPresent());
        Assertions.assertEquals(secondKey, opNode.get().key());
        Assertions.assertInstanceOf(ValueNode.class, opNode.get());
        Assertions.assertEquals(type, opNode.get().typeToken());
    }
}
