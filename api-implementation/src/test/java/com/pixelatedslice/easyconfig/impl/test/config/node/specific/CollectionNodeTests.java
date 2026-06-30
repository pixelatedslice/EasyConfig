package com.pixelatedslice.easyconfig.impl.test.config.node.specific;

import com.pixelatedslice.easyconfig.impl.config.node.collection.CollectionNodeImpl;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@NullMarked
public class CollectionNodeTests {

    @Test
    public void CollectionNode_can_build_correctly() {
        //ARRANGE
        final var builder = new CollectionNodeOriginalBuilder("key").append().complete();

        //ACT
        final var result = builder.build();

        //ASSERT
        final var children = result.stream().map(r -> r.container().orElseThrow()).toList();
        Assertions.assertEquals(1, children.size());
    }

    @Test
    public void CollectionNode_toBuilder() {
        //ARRANGE
        final var node = (CollectionNodeImpl) new CollectionNodeOriginalBuilder("key").append().complete().build();

        //ACT
        final var toBuilder = node.toBuilder();

        //ASSERT
        Assertions.assertEquals("key", toBuilder.key());
        Assertions.assertEquals(1, toBuilder.children().size());
    }
}
