package com.pixelatedslice.easyconfig.test.impl.fileformat;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.container.ContainerNode;
import com.pixelatedslice.easyconfig.api.config.node.factory.Nodes;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import com.pixelatedslice.easyconfig.impl.config.ConfigStructureImpl;
import com.pixelatedslice.easyconfig.impl.config.node.container.builder.ContainerNodeBuilder;
import com.pixelatedslice.easyconfig.impl.fileformat.ConfigUtils;
import com.pixelatedslice.easyconfig.impl.fileformat.serializer.StringSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.Optional;

public class ConfigUtilsReadIntoDataMapperTests {

    @Test
    public void reads_node_structure(){
        //Arrange
        var globalSerializer = Mockito.mock(SerializerRegistry.class);
        try(var staticMock = Mockito.mockStatic(SerializerRegistry.class)){
            staticMock.when(SerializerRegistry::global).thenReturn(globalSerializer);
            var stringSerializer = new StringSerializer();

            Mockito.when(globalSerializer.createChild()).thenReturn(globalSerializer);
            Mockito.when(globalSerializer.serializerFor(TypeToken.of(String.class))).thenReturn(Optional.of(stringSerializer));

            var node = new ContainerNodeBuilder("one")
                    .children(
                            Nodes.value(String.class).key("two"),
                            Nodes.value(String.class).key("three")
                    )
                    .build();
            var structure = (ConfigStructureImpl) node.toStructure();

            var json = "{\"one\": {\"two\": \"TwoValue\", \"three\": \"ThreeValue\"}}";
            var objectMapper = new ObjectMapper();
            var jsonNode = (ObjectNode) objectMapper.readTree(json);

            //Act
            var result = ConfigUtils.readIntoDataMapper(structure, objectMapper, jsonNode);

            //Assert
            var containerNode = (ContainerNode)(result.root());
            var valueNodeTwo = containerNode.node("one", "two").value(String.class).orElseThrow();
            var valueNodeThree = containerNode.node("one", "three").value(String.class).orElseThrow();
            Assertions.assertEquals("TwoValue", valueNodeTwo.value().orElseThrow());
            Assertions.assertEquals("ThreeValue", valueNodeThree.value().orElseThrow());
        }

    }
}
