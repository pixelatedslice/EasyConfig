package com.pixelatedslice.easyconfig.impl.test.config.node;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.builder.NodeBuilder;
import com.pixelatedslice.easyconfig.impl.config.node.container.builder.ContainerNodeOriginalBuilder;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

@NullMarked
public class EnvBuilderTests {

    private NodeBuilder.FirstStep builder() {
        return new ContainerNodeOriginalBuilder();
    }

    @Test
    public void NodeBuilder_can_build_node() {
        //ARRANGE
        final var key = "First key";
        final var typeToken = TypeToken.of(String.class);
        final var envKey = "envKey";
        final Function<String, String> adapter = t -> t;

        //ACT
        final var result = this.builder().key(key).of(String.class).env(envKey).adapter(adapter).build();

        //ASSERT
        Assertions.assertEquals(key, result.key());
        Assertions.assertEquals(typeToken, result.typeToken());
        Assertions.assertEquals(envKey, result.envKey());
        Assertions.assertEquals(adapter, result.adapter());
    }
}
