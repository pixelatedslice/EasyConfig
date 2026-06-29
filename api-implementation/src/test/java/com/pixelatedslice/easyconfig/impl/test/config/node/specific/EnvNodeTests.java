package com.pixelatedslice.easyconfig.impl.test.config.node.specific;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.config.node.env.EnvAdapter;
import com.pixelatedslice.easyconfig.impl.config.node.env.EnvNodeImpl;
import com.pixelatedslice.easyconfig.impl.config.node.env.builder.OriginalEnvNodeBuilder;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@NullMarked
public class EnvNodeTests {

    @Test
    public void EnvNode_fails_when_creating_without_env_adapter() {
        //ARRANGE
        final var key = "key";
        final var token = TypeToken.of(String.class);
        final String envKey = "LANG";
        final var internalBuilder = new OriginalEnvNodeBuilder<>(key, token, envKey);

        //ACT - ASSERT
        Assertions.assertThrows(NullPointerException.class, () -> new EnvNodeImpl<>(internalBuilder));
    }

    @Test
    public void EnvNode_valid_when_creating() {
        //ARRANGE
        final String key = "key";
        final var token = TypeToken.of(String.class);
        final var envKey = "LANG";
        final EnvAdapter<String> adapter = t -> t;
        final var internalBuilder = new OriginalEnvNodeBuilder<>(key, token, envKey).adapter(adapter);

        //ACT
        final var result = new EnvNodeImpl<>(internalBuilder);

        //ASSERT
        Assertions.assertEquals(key, result.key());
        Assertions.assertEquals(envKey, result.envKey());
        Assertions.assertEquals(token, result.typeToken());
        Assertions.assertEquals(System.getenv(envKey), result.value().orElse(""));
    }

    @Test
    public void EnvNode_to_builder() {
        //ARRANGE
        final String key = "key";
        final var token = TypeToken.of(String.class);
        final var envKey = "LANG";
        final EnvAdapter<String> adapter = t -> t;
        final var internalBuilder = new OriginalEnvNodeBuilder<>(key, token, envKey).adapter(adapter);

        final var node = new EnvNodeImpl<>(internalBuilder);

        //ACT
        final var result = node.toBuilder();

        //ASSERT
        Assertions.assertInstanceOf(OriginalEnvNodeBuilder.class, result);
        final var castResult = (OriginalEnvNodeBuilder<?>) result;
        Assertions.assertEquals(key, castResult.key());
        Assertions.assertEquals(envKey, castResult.envKey());
        Assertions.assertEquals(token, castResult.type());
        Assertions.assertEquals(adapter, castResult.adapter());
    }
}
