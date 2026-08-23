package com.pixelatedslice.easyconfig.impl.test.serializer;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.serialization.Serializer;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistryOptions;
import com.pixelatedslice.easyconfig.impl.serializer.SerializerRegistryImpl;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@NullMarked
public class SerializerRegistryTests {

    @SuppressWarnings("unchecked")
    private <T> Serializer<T> mockSerializer(Class<T> clazz) {
        final var mockedSerializer = (Serializer<T>) Mockito.mock(Serializer.class);
        Mockito.when(mockedSerializer.type()).thenReturn(TypeToken.of(clazz));
        return mockedSerializer;
    }

    @Test
    public void SerializerRegistry_can_register_serializer() {
        // Arrange
        final var mockedSerializer = this.mockSerializer(String.class);
        final var serializerRegistry = new SerializerRegistryImpl(null);

        // Act
        serializerRegistry.register(mockedSerializer);

        // Assert
        final var result = serializerRegistry.serializerFor(String.class).orElseThrow();
        Assertions.assertEquals(mockedSerializer, result);
    }

    @Test
    public void SerializerRegistry_can_register_serializer_duplication_maintain_first() {
        // Arrange
        final var mockedSerializer = this.mockSerializer(String.class);
        final var copy = this.mockSerializer(String.class);
        final var serializerRegistry = new SerializerRegistryImpl(null);

        serializerRegistry.register(mockedSerializer);

        // Act
        serializerRegistry.register(option -> option.duplicateRegisterStyle(SerializerRegistryOptions.DuplicateRegisterStyle.KEEP_ORIGINAL),
                copy);

        // Assert
        final var result = serializerRegistry.serializerFor(String.class).orElseThrow();
        Assertions.assertEquals(mockedSerializer, result);
    }

    @Test
    public void SerializerRegistry_can_register_serializer_duplication_replace() {
        // Arrange
        final var mockedSerializer = this.mockSerializer(String.class);
        final var copy = this.mockSerializer(String.class);
        final var serializerRegistry = new SerializerRegistryImpl(null);

        serializerRegistry.register(mockedSerializer);

        // Act
        serializerRegistry.register(option -> option.duplicateRegisterStyle(SerializerRegistryOptions.DuplicateRegisterStyle.REPLACE),
                copy);

        // Assert
        final var result = serializerRegistry.serializerFor(String.class).orElseThrow();
        Assertions.assertEquals(copy, result);
    }

    @Test
    public void SerializerRegistry_can_register_serializer_duplication_throws() {
        // Arrange
        final var mockedSerializer = this.mockSerializer(String.class);
        final var copy = this.mockSerializer(String.class);
        final var serializerRegistry = new SerializerRegistryImpl(null);

        serializerRegistry.register(mockedSerializer);

        // Act
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            serializerRegistry.register(option -> option.duplicateRegisterStyle(SerializerRegistryOptions.DuplicateRegisterStyle.THROW),
                    copy);
        });

        // Assert
        final var result = serializerRegistry.serializerFor(String.class).orElseThrow();
        Assertions.assertEquals(mockedSerializer, result);
    }

    @Test
    public void SerializerRegistry_stream_from_child() {
        // Arrange
        final var mockedSerializer = this.mockSerializer(String.class);
        final var serializerRegistry = new SerializerRegistryImpl(null);

        serializerRegistry.register(mockedSerializer);

        // Act
        final var result = serializerRegistry.stream().findAny().orElseThrow();

        // Assert
        Assertions.assertEquals(mockedSerializer, result);
    }

    @Test
    public void SerializerRegistry_createChild_get_from_parent() {
        // Arrange
        final var mockedSerializer = this.mockSerializer(String.class);
        final var serializerRegistry = new SerializerRegistryImpl(null);
        final var child = new SerializerRegistryImpl(serializerRegistry);

        serializerRegistry.register(mockedSerializer);

        // Act
        final var result = child.serializerFor(String.class).orElseThrow();

        // Assert
        Assertions.assertEquals(mockedSerializer, result);
    }

    @Test
    public void SerializerRegistry_createChild_stream_from_child() {
        // Arrange
        final var mockedSerializer = this.mockSerializer(String.class);
        final var serializerRegistry = new SerializerRegistryImpl(null);
        final var child = new SerializerRegistryImpl(serializerRegistry);

        serializerRegistry.register(mockedSerializer);

        // Act
        final var result = child.stream().findAny().orElseThrow();

        // Assert
        Assertions.assertEquals(mockedSerializer, result);
    }
}
