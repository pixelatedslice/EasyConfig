package com.pixelatedslice.easyconfig.impl.test.config.node.specific;

import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.factory.CommonTypes;
import com.pixelatedslice.easyconfig.api.config.node.factory.Nodes;
import com.pixelatedslice.easyconfig.impl.config.node.container.ContainerNodeImpl;
import com.pixelatedslice.easyconfig.impl.config.node.container.builder.ContainerNodeBuilder;
import com.pixelatedslice.easyconfig.impl.test.testUtils.CollectionAssert;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

@NullMarked
public class ContainerNodeTests {

    @SuppressWarnings("DataFlowIssue")
    @Test
    public void ContainerNode_fails_when_creating_without_key() {
        //ARRANGE
        final var internalBuilder = new ContainerNodeBuilder(null);

        //ACT - ASSERT
        Assertions.assertThrows(NullPointerException.class, () -> new ContainerNodeImpl(internalBuilder));
    }

    @Test
    public void ContainerNode_can_maintain_values() {
        //ARRANGE
        final var key = "key";
        final var internalBuilder = new ContainerNodeBuilder(key);

        //ACT
        final var result = new ContainerNodeImpl(internalBuilder);

        //ASSERT
        Assertions.assertEquals(key, result.key());
        Assertions.assertTrue(result.isRootNode(), "is not root node");
    }

    @Test
    public void ContainerNode_to_builder() {
        //ARRANGE
        final var key = "key";
        final var internalBuilder = new ContainerNodeBuilder(key);

        final Node node = new ContainerNodeImpl(internalBuilder);

        //ACT
        final var result = node.toBuilder();

        //ASSERT
        Assertions.assertInstanceOf(ContainerNodeBuilder.class, result);
        final var castResult = (ContainerNodeBuilder) result;
        Assertions.assertEquals(key, castResult.key());
    }

    @Test
    public void ContainerNode_editable_addNode() {
        //ARRANGE
        final var key = "original";
        final var originalNode = new ContainerNodeBuilder(key).build();

        final var toAdd = new ContainerNodeBuilder("adding").build();

        //ACT
        try (var editable = originalNode.editable()) {
            editable.addNodes(toAdd);
        }

        //ASSERT
        CollectionAssert.isEqualTo(List.of(toAdd), originalNode.children(), true);
    }

    @Test
    public void ContainerNode_editable_clearNodes() {
        //ARRANGE
        final var key = "original";
        final var originalNode = Nodes
                .container(key)
                .builtChildren(Nodes.emptyValue(CommonTypes.STRING, "added"))
                .build();

        //ACT
        try (var editable = originalNode.editable()) {
            editable.clearNodes();
        }

        //ASSERT
        CollectionAssert.isEqualTo(Collections.emptyList(), originalNode.children(), true);
    }

    @Test
    public void ContainerNode_editable_removeNode() {
        //ARRANGE
        final var key = "original";
        final var originalNode = Nodes
                .container(key)
                .builtChildren(
                        Nodes.emptyValue(CommonTypes.STRING, "added"),
                        Nodes.emptyValue(CommonTypes.STRING, "second")
                ).build();
        final var toRemain = originalNode.children().getLast();
        final var toRemove = originalNode.children().getFirst();

        //ACT
        try (var editable = originalNode.editable()) {
            editable.removeNodes(toRemove);
        }

        //ASSERT
        CollectionAssert.isEqualTo(List.of(toRemain), originalNode.children(), true);
    }

    @Test
    public void ContainerNode_editable_setNode() {
        //ARRANGE
        final var key = "original";
        final var originalNode = Nodes
                .container(key)
                .builtChildren(Nodes.emptyValue(CommonTypes.STRING, "added"),
                        Nodes.emptyValue(CommonTypes.STRING, "second"))
                .build();

        final var toSet = new ContainerNodeBuilder("set").build();

        //ACT
        try (var editable = originalNode.editable()) {
            editable.setNodes(List.of(toSet));
        }

        //ASSERT
        CollectionAssert.isEqualTo(List.of(toSet), originalNode.children(), true);
    }
}
