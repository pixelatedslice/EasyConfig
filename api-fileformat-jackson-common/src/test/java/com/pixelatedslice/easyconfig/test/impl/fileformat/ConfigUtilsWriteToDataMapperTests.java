package com.pixelatedslice.easyconfig.test.impl.fileformat;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import com.pixelatedslice.easyconfig.api.config.node.factory.Nodes;
import com.pixelatedslice.easyconfig.api.config.node.serializer.SerializerNode;
import com.pixelatedslice.easyconfig.api.config.node.value.ValueNode;
import com.pixelatedslice.easyconfig.api.exception.SerializeException;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
import com.pixelatedslice.easyconfig.api.serialization.context.SerializeContext;
import com.pixelatedslice.easyconfig.impl.config.node.container.builder.ContainerNodeBuilder;
import com.pixelatedslice.easyconfig.impl.fileformat.ConfigUtils;
import com.pixelatedslice.easyconfig.impl.fileformat.serializer.StringSerializer;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.Map;
import java.util.Optional;

public class ConfigUtilsWriteToDataMapperTests {

    @Test
    public void creates_node_structure() {
        //Arrange
        var globalSerializer = Mockito.mock(SerializerRegistry.class);
        try (var staticMock = Mockito.mockStatic(SerializerRegistry.class)) {
            staticMock.when(SerializerRegistry::global).thenReturn(globalSerializer);
            var stringSerializer = new StringSerializer();
            Mockito.when(globalSerializer.createChild()).thenReturn(globalSerializer);
            Mockito.when(globalSerializer.serializerFor(TypeToken.of(String.class))).thenReturn(Optional.of(stringSerializer));

            var node = new ContainerNodeBuilder("")
                    .key("one")
                    .children(
                            Nodes.value(String.class).key("two").defaultValue("TwoValue"),
                            Nodes.value(String.class).key("three").value("ThreeValue")
                    )
                    .build();
            var config = node.toStructure();
            //Act
            var result = ConfigUtils.writeToDataMapper(config, Map.of()).getValue();

            //Assert
            Assertions.assertEquals("TwoValue", result.findValue("one").findValue("two").stringValue());
            Assertions.assertEquals("ThreeValue", result.findValue("one").findValue("three").stringValue());
        }
    }

    @Test
    public void handles_wrapped_serializer() {
        //Arrange
        var globalSerializer = Mockito.mock(SerializerRegistry.class);
        try (var staticMock = Mockito.mockStatic(SerializerRegistry.class)) {
            staticMock.when(SerializerRegistry::global).thenReturn(globalSerializer);
            var stringSerializer = new StringSerializer();
            Serializer<File> fileSerializer = new Serializer<File>() {
                @Override
                public @NonNull TypeToken<File> type() {
                    return TypeToken.of(File.class);
                }

                @Override
                public void buildStructure(@NonNull SerializerNode node) {
                    throw new RuntimeException("not called");
                }

                @Override
                public void deserialize(@NonNull File value, @NonNull SerializerNode builder, @NonNull SerializeContext context) {
                    builder.set(value.getPath());
                }

                @Override
                public @NonNull File serialize(@NonNull Node rootNode, SerializerNode node, @NonNull SerializeContext context) throws SerializeException {
                    if (!(rootNode instanceof ValueNode<?>)) {
                        throw new SerializeException.InvalidNodeTypeException(rootNode);
                    }
                    var path = node.read(String.class);
                    return new File(path);
                }
            };
            Mockito.when(globalSerializer.createChild()).thenReturn(globalSerializer);
            Mockito.when(globalSerializer.serializerFor(TypeToken.of(String.class))).thenReturn(Optional.of(stringSerializer));
            Mockito.when(globalSerializer.serializerFor(String.class)).thenReturn(Optional.of(stringSerializer));

            var node = new ContainerNodeBuilder("")
                    .key("one")
                    .children(
                            Nodes.value(String.class).key("two").defaultValue("TwoValue"),
                            Nodes.value(File.class).key("three").value(new File("child")).serializer(fileSerializer)
                    )
                    .build();
            var config = node.toStructure();
            //Act
            var result = ConfigUtils.writeToDataMapper(config, Map.of()).getValue();

            //Assert
            Assertions.assertEquals("TwoValue", result.findValue("one").findValue("two").stringValue());
            Assertions.assertEquals("child", result.findValue("one").findValue("three").stringValue());
        }
    }
}
