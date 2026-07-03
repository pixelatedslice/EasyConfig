package com.pixelatedslice.easyconfig.impl.test.config.node;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.CommonNodes;
import com.pixelatedslice.easyconfig.api.config.node.factory.nodes.Nodes;
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
        final var result = Nodes.INSTANCE.collection(key).build();

        //ASSERT
        Assertions.assertEquals(key, result.key());
    }

    @Test
    public void NodeBuilder_collection_builds_with_single() {
        //ARRANGE
        final String key = "First key";

        //ACT
        // Old: final var result = this.builder().key(key).collection().append().complete().build();
        final var result = Nodes.INSTANCE.collection(key).build();

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
        final var result = Node.of((Nodes n, CommonNodes _) ->
                n.collection(key).children(
                        n.container("index_0").children(n.container(thirdKey))
                )
        );

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

        final var firstNode = Nodes.INSTANCE.collection(key);
        final var secondNode = Nodes.INSTANCE.collection("Second key");
        final var thirdNode = Nodes.INSTANCE.container("Third key");
        final var fourthNode = Nodes.INSTANCE.container(fourKey);

        final var result = Node.of((Nodes n, CommonNodes _) ->
                firstNode.children(
                        secondNode.children(
                                thirdNode.children(
                                        fourthNode
                                )
                        )
                ));

        System.out.println("result = " + result);

        //ASSERT
        Assertions.assertEquals(key, result.key());
        final var opSecondCollection = result.atIndex(0).collectionNode();
        Assertions.assertTrue(opSecondCollection.isPresent());
        Assertions.assertEquals("Second key", opSecondCollection.get().key());
        System.out.println("opSecondCollection.get() = " + opSecondCollection.get());
        final var opThirdChildNode = opSecondCollection.get().atIndex(0).container();
        Assertions.assertTrue(opThirdChildNode.isPresent()); //index container
        System.out.println("opThirdChildNode.get() = " + opThirdChildNode.get());
        final var opFourChildNode = opThirdChildNode.get().containerNode(fourKey);
        Assertions.assertTrue(opFourChildNode.isPresent());
    }
}
