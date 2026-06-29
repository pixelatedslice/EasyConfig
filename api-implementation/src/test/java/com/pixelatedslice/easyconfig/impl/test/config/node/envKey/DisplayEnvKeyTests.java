package com.pixelatedslice.easyconfig.impl.test.config.node.envKey;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvKeys;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@NullMarked
public class DisplayEnvKeyTests {

    @Test
    public void display_has_correct_key() {
        //ARRANGE
        final var key = "DISPLAY";

        //ACT
        final var result = EnvKeys.DISPLAY.key();

        //ASSERT
        Assertions.assertEquals(key, result);
    }

    @Test
    public void lang_parses_correctly() {
        //ARRANGE
        final String input = ":0";

        //ACT
        final var result = EnvKeys.DISPLAY.adapter().apply(input);

        //ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void lang_returns_null_when_invalid_without_dot_input() {
        //ARRANGE
        final String input = "invalid";

        //ACT
        final var result = EnvKeys.DISPLAY.adapter().apply(input);

        //ASSERT
        Assertions.assertNull(result);
    }
}
