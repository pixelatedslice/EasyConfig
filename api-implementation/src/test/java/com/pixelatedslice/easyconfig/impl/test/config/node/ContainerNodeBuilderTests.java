package com.pixelatedslice.easyconfig.impl.test.config.node;

import com.pixelatedslice.easyconfig.api.config.node.builder.OldNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@NullMarked
public class ContainerNodeBuilderTests {

    private OldNodeBuilder.FirstStep builder() {
        return new ContainerNodeOriginalBuilder();
    }

    @Test
    public void NodeBuilder_should_build_container_node_with_key() {
        //ARRANGE
        final String key = "My Key";

        //ACT
        final var node = this.builder().key(key).build();

        //ASSERT
        Assertions.assertEquals(key, node.key());
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
    public void NodeBuilder_should_append_with_provided_key() {
        //ARRANGE
        final var key = "My first key";
        final var secondKey = "My second key";

        //ACT
        final var result = this.builder().key(key).append(secondKey).complete().build();

        //ASSERT
        Assertions.assertEquals(key, result.key());
        final var opNode = result.containerNode(secondKey);
        Assertions.assertTrue(opNode.isPresent());
        Assertions.assertEquals(secondKey, opNode.get().key());
    }

    @Test
    public void NodeBuilder_should_append_child_with_provided_key() {
        //ARRANGE
        final var key = "My first key";
        final var secondKey = "My second key";
        final var thirdKey = "My third key";

        //ACT
        final var result = this.builder().key(key).append(secondKey).append(thirdKey).complete().complete().build();

        //ASSERT
        Assertions.assertEquals(key, result.key());
        final var opNode = result.containerNode(secondKey);
        Assertions.assertTrue(opNode.isPresent());
        Assertions.assertEquals(secondKey, opNode.get().key());
        final var opSecondNode = opNode.get().containerNode(thirdKey);
        Assertions.assertTrue(opSecondNode.isPresent());
        Assertions.assertEquals(thirdKey, opSecondNode.get().key());
    }
}
