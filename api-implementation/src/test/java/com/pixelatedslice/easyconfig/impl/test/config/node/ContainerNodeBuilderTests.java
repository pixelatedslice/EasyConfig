package com.pixelatedslice.easyconfig.impl.test.config.node;

import com.pixelatedslice.easyconfig.api.config.node.builder.Nodes;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@NullMarked
public class ContainerNodeBuilderTests {

    @Test
    public void NodeBuilder_should_build_container_node_with_key() {
        //ARRANGE
        final String key = "My Key";

        //ACT
        // Old: final var node = this.builder().key(key).build();
        final var node = Nodes.container(key).build();

        //ASSERT
        Assertions.assertEquals(key, node.key());
    }

    @SuppressWarnings("DataFlowIssue")
    @Test
    public void NodeBuilder_should_throw_exception_with_null_key() {
        //ARRANGE

        //ACT - ASSERT
        // Old: Assertions.assertThrows(NullPointerException.class, () ->
        //        this.builder().key(null).build());

        Assertions.assertThrows(NullPointerException.class, () -> Nodes.container(null).build());
    }

    @Test
    public void NodeBuilder_should_append_with_provided_key() {
        //ARRANGE
        final var key = "My first key";
        final var secondKey = "My second key";

        //ACT
        // Old: final var result = this.builder().key(key).append(secondKey).complete().build();
        final var result = Nodes.container(key).children(Nodes.container(key)).build();

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
        // Old: final var result = this.builder().key(key).append(secondKey).append(thirdKey).complete().complete()
        // .build();
        final var result = Nodes
                .container(key)
                .children(Nodes.container(secondKey).children(Nodes.container(thirdKey)))
                .build();

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
