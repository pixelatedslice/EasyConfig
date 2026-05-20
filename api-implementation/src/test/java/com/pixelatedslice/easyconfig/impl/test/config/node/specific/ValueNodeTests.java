package com.pixelatedslice.easyconfig.impl.test.config.node.specific;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.api.validator.option.ValidationOptions;
import com.pixelatedslice.easyconfig.impl.config.node.value.ValueNodeImpl;
import com.pixelatedslice.easyconfig.impl.config.node.value.builder.ValueNodeOriginalBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ValueNodeTests {

    @Test
    public void ValueNode_can_create() {
        // Arrange
        var builder = new ValueNodeOriginalBuilder<>(TypeToken.of(String.class), "key");

        // Act
        var node = new ValueNodeImpl<>(builder);

        // Assert
        Assertions.assertEquals(TypeToken.of(String.class), node.typeToken());
        Assertions.assertEquals("key", node.key());
    }

    @Test
    public void ValueNode_valueOrDefault_can_receive_default_when_no_value_present() {
        // Arrange
        var builder = new ValueNodeOriginalBuilder<>(TypeToken.of(String.class), "key");
        builder.defaultValue("default");

        // Act
        var node = new ValueNodeImpl<>(builder);

        // Assert
        Assertions.assertEquals(TypeToken.of(String.class), node.typeToken());
        Assertions.assertEquals("key", node.key());
        Assertions.assertEquals("default", node.valueOrDefault().orElseThrow());
        Assertions.assertEquals("default", node.defaultValue().orElseThrow());
        Assertions.assertEquals(Optional.empty(), node.value());
    }

    @Test
    public void ValueNode_valueOrDefault_can_receive_value_when_no_default_present() {
        // Arrange
        var builder = new ValueNodeOriginalBuilder<>(TypeToken.of(String.class), "key");
        builder.value("value");

        // Act
        var node = new ValueNodeImpl<>(builder);

        // Assert
        Assertions.assertEquals(TypeToken.of(String.class), node.typeToken());
        Assertions.assertEquals("key", node.key());
        Assertions.assertEquals("value", node.valueOrDefault().orElseThrow());
        Assertions.assertEquals("value", node.value().orElseThrow());
        Assertions.assertEquals(Optional.empty(), node.defaultValue());
    }

    @Test
    public void ValueNode_valueOrDefault_can_receive_value_when_both_default_and_value_are_present() {
        // Arrange
        var builder = new ValueNodeOriginalBuilder<>(TypeToken.of(String.class), "key");
        builder.value("value");
        builder.defaultValue("default");

        // Act
        var node = new ValueNodeImpl<>(builder);

        // Assert
        Assertions.assertEquals(TypeToken.of(String.class), node.typeToken());
        Assertions.assertEquals("key", node.key());
        Assertions.assertEquals("value", node.valueOrDefault().orElseThrow());
        Assertions.assertEquals("default", node.defaultValue().orElseThrow());
        Assertions.assertEquals("value", node.value().orElseThrow());
    }

    @Test
    public void ValueNode_value_validate_fails(){
        // Arrange
        var builder = new ValueNodeOriginalBuilder<>(TypeToken.of(String.class), "key");
        builder.value("value");
        builder.validator((_, context) -> context.error("Big error"));
        var node = new ValueNodeImpl<>(builder);

        // Act
        var result = node.value(ValidationOptions.returnEmpty());

        // Assert
        Assertions.assertEquals(Optional.empty(), result);
    }

    @Test
    public void ValueNode_editable_can_edit_value(){
        // Arrange
        var builder = new ValueNodeOriginalBuilder<>(TypeToken.of(String.class), "key");
        builder.value("Old value");
        var node = new ValueNodeImpl<>(builder);

        // Act
        try(var editable = node.editable()){
            editable.setValue("New value");
        }

        // Assert
        Assertions.assertEquals("New value", node.value().orElseThrow());
    }

    @Test
    public void ValueNode_toBuilder_retains_values(){
        // Arrange
        var builder = new ValueNodeOriginalBuilder<>(TypeToken.of(String.class), "key");
        builder.value("value");
        builder.defaultValue("default value");
        var node = new ValueNodeImpl<>(builder);

        // Act
        var newBuilder = node.toBuilder();

        // Assert
        Assertions.assertEquals("value", newBuilder.value());
        Assertions.assertEquals("default value", newBuilder.defaultValue());
        Assertions.assertEquals("key", newBuilder.key());
        Assertions.assertEquals(TypeToken.of(String.class), newBuilder.type());
    }
}
