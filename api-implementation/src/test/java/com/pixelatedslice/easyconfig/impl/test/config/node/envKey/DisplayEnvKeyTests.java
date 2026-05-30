package com.pixelatedslice.easyconfig.impl.test.config.node.envKey;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvKeys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DisplayEnvKeyTests {

    @Test
    public void display_has_correct_key() {
        //ARRANGE
        var key = "DISPLAY";

        //ACT
        var result = EnvKeys.DISPLAY.key();

        //ASSERT
        Assertions.assertEquals(key, result);
    }

    @Test
    public void lang_parses_correctly() {
        //ARRANGE
        String input = ":0";

        //ACT
        var result = EnvKeys.DISPLAY.adapter().apply(input);

        //ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void lang_returns_null_when_invalid_without_dot_input() {
        //ARRANGE
        String input = "invalid";

        //ACT
        var result = EnvKeys.DISPLAY.adapter().apply(input);

        //ASSERT
        Assertions.assertNull(result);
    }
}
