package com.pixelatedslice.easyconfig.impl.test.serializer;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistryOptions;
import com.pixelatedslice.easyconfig.impl.serializer.SerializerRegistryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SerializerRegistryTests {

    @SuppressWarnings("unchecked")
    private <T> Serializer<T> mockSerializer(Class<T> clazz) {
        var mockedSerializer = (Serializer<T>) Mockito.mock(Serializer.class);
        Mockito.when(mockedSerializer.type()).thenReturn(TypeToken.of(clazz));
        return mockedSerializer;
    }

    @Test
    public void SerializerRegistry_can_register_serializer() {
        // Arrange
        var mockedSerializer = mockSerializer(String.class);
        var serializerRegistry = new SerializerRegistryImpl(null);

        // Act
        serializerRegistry.register(mockedSerializer);

        // Assert
        var result = serializerRegistry.serializerFor(String.class).orElseThrow();
        Assertions.assertEquals(mockedSerializer, result);
    }

    @Test
    public void SerializerRegistry_can_register_serializer_duplication_maintain_first() {
        // Arrange
        var mockedSerializer = mockSerializer(String.class);
        var copy = mockSerializer(String.class);
        var serializerRegistry = new SerializerRegistryImpl(null);

        serializerRegistry.register(mockedSerializer);

        // Act
        serializerRegistry.register(option -> option.duplicateRegisterStyle(SerializerRegistryOptions.DuplicateRegisterStyle.KEEP_ORIGINAL), copy);

        // Assert
        var result = serializerRegistry.serializerFor(String.class).orElseThrow();
        Assertions.assertEquals(mockedSerializer, result);
    }

    @Test
    public void SerializerRegistry_can_register_serializer_duplication_replace() {
        // Arrange
        var mockedSerializer = mockSerializer(String.class);
        var copy = mockSerializer(String.class);
        var serializerRegistry = new SerializerRegistryImpl(null);

        serializerRegistry.register(mockedSerializer);

        // Act
        serializerRegistry.register(option -> option.duplicateRegisterStyle(SerializerRegistryOptions.DuplicateRegisterStyle.REPLACE), copy);

        // Assert
        var result = serializerRegistry.serializerFor(String.class).orElseThrow();
        Assertions.assertEquals(copy, result);
    }

    @Test
    public void SerializerRegistry_can_register_serializer_duplication_throws() {
        // Arrange
        var mockedSerializer = mockSerializer(String.class);
        var copy = mockSerializer(String.class);
        var serializerRegistry = new SerializerRegistryImpl(null);

        serializerRegistry.register(mockedSerializer);

        // Act
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serializerRegistry.register(option -> option.duplicateRegisterStyle(SerializerRegistryOptions.DuplicateRegisterStyle.THROW), copy);
        });

        // Assert
        var result = serializerRegistry.serializerFor(String.class).orElseThrow();
        Assertions.assertEquals(mockedSerializer, result);
    }

    @Test
    public void SerializerRegistry_stream_from_child() {
        // Arrange
        var mockedSerializer = mockSerializer(String.class);
        var serializerRegistry = new SerializerRegistryImpl(null);

        serializerRegistry.register(mockedSerializer);

        // Act
        var result = serializerRegistry.stream().findAny().orElseThrow();

        // Assert
        Assertions.assertEquals(mockedSerializer, result);
    }

    @Test
    public void SerializerRegistry_createChild_get_from_parent() {
        // Arrange
        var mockedSerializer = mockSerializer(String.class);
        var serializerRegistry = new SerializerRegistryImpl(null);
        var child = new SerializerRegistryImpl(serializerRegistry);

        serializerRegistry.register(mockedSerializer);

        // Act
        var result = child.serializerFor(String.class).orElseThrow();

        // Assert
        Assertions.assertEquals(mockedSerializer, result);
    }

    @Test
    public void SerializerRegistry_createChild_stream_from_child() {
        // Arrange
        var mockedSerializer = mockSerializer(String.class);
        var serializerRegistry = new SerializerRegistryImpl(null);
        var child = new SerializerRegistryImpl(serializerRegistry);

        serializerRegistry.register(mockedSerializer);

        // Act
        var result = child.stream().findAny().orElseThrow();

        // Assert
        Assertions.assertEquals(mockedSerializer, result);
    }
}
