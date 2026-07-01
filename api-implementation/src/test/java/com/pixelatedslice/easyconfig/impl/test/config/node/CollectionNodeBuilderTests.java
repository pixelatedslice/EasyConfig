package com.pixelatedslice.easyconfig.impl.test.config.node;

import com.pixelatedslice.easyconfig.api.config.node.builder.Nodes;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@NullMarked
public class CollectionNodeBuilderTests {

    @Test
    public void NodeBuilder_collection_builds_empty() {
        //ARRANGE
        final String key = "First key";

        //ACT
        // Old: final var result = this.builder().key(key).collection().build();
        final var result = Nodes.collection(key).build();

        //ASSERT
        Assertions.assertEquals(key, result.key());
    }

    @Test
    public void NodeBuilder_collection_builds_with_single() {
        //ARRANGE
        final String key = "First key";

        //ACT
        // Old: final var result = this.builder().key(key).collection().append().complete().build();
        final var result = Nodes.collection(key).children().build();

        //ASSERT
        Assertions.assertEquals(key, result.key());
        final var opChildNode = result.atIndex(0).plainNode();
        // Old: Assertions.assertTrue(opChildNode.isPresent());
        // Old: Assertions.assertEquals("index_0", opChildNode.get().key());
        Assertions.assertFalse(opChildNode.isPresent());
    }

    @Test
    public void NodeBuilder_collection_builds_with_single_with_container() {
        //ARRANGE
        final String key = "First key";
        final String thirdKey = "Third key";

        //ACT
        // Old: final var result = this.builder().key(key).collection().append().append(thirdKey).complete().complete
        // ().build();
        final var result = Nodes.collection(key).children(
                Nodes.container("index_0").children(Nodes.container(thirdKey))
        ).build();

        //ASSERT
        Assertions.assertEquals(key, result.key());
        final var opChildNode = result.atIndex(0).container();
        Assertions.assertTrue(opChildNode.isPresent());
        Assertions.assertEquals("index_0", opChildNode.get().key());
        final var opSecondChildNode = opChildNode.get().containerNode(thirdKey);
        Assertions.assertTrue(opSecondChildNode.isPresent());
        Assertions.assertEquals(thirdKey, opSecondChildNode.get().key());
    }

    @Test
    public void NodeBuilder_collection_builds_with_single_with_collection_with_container() {
        //ARRANGE
        final String key = "First key";
        final String fourKey = "Four key";

        //ACT
        // Old: final var result = this.builder()
        //         .key(key)
        //         .collection()
        //         .append()
        //           .collection()
        //             .append()
        //              .append(fourKey)
        //             .complete()
        //           .complete()
        //         .complete()
        //         .build();
        final var result = Nodes.collection(key)
                .children(Nodes.collection("index_0")
                        .children(Nodes.container("test")
                                .children(
                                        Nodes.container(fourKey)
                                )
                        )
                ).build();

        System.out.println("result = " + result);

        //ASSERT
        Assertions.assertEquals(key, result.key());
        final var opSecondCollection = result.atIndex(0).collectionNode();
        Assertions.assertTrue(opSecondCollection.isPresent());
        Assertions.assertEquals("index_0", opSecondCollection.get().key());
        System.out.println("opSecondCollection.get() = " + opSecondCollection.get());
        final var opThirdChildNode = opSecondCollection.get().atIndex(0).container();
        Assertions.assertTrue(opThirdChildNode.isPresent()); //index container
        System.out.println("opThirdChildNode.get() = " + opThirdChildNode.get());
        final var opFourChildNode = opThirdChildNode.get().containerNode(fourKey);
        Assertions.assertTrue(opFourChildNode.isPresent());
    }
}
