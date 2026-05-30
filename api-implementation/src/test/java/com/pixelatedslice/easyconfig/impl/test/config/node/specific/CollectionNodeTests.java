package com.pixelatedslice.easyconfig.impl.test.config.node.specific;

import com.pixelatedslice.easyconfig.impl.config.node.collection.CollectionNodeImpl;
import com.pixelatedslice.easyconfig.impl.config.node.collection.builder.CollectionNodeOriginalBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CollectionNodeTests {

    @Test
    public void CollectionNode_can_build_correctly() {
        //ARRANGE
        var builder = new CollectionNodeOriginalBuilder("key").append().complete();

        //ACT
        var result = builder.build();

        //ASSERT
        var children = result.stream().map(r -> r.container().orElseThrow()).toList();
        Assertions.assertEquals(1, children.size());
    }

    @Test
    public void CollectionNode_toBuilder() {
        //ARRANGE
        var node = (CollectionNodeImpl) new CollectionNodeOriginalBuilder("key").append().complete().build();

        //ACT
        var toBuilder = node.toBuilder();

        //ASSERT
        Assertions.assertEquals("key", toBuilder.key());
        Assertions.assertEquals(1, toBuilder.children().size());
    }
}
