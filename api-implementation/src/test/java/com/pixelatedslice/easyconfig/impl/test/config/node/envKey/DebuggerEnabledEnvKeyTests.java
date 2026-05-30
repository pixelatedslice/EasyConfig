package com.pixelatedslice.easyconfig.impl.test.config.node.envKey;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvKeys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DebuggerEnabledEnvKeyTests {

    @Test
    public void debugger_enabled_has_correct_key() {
        //ARRANGE
        var key = "DEBUGGER_ENABLED";

        //ACT
        var result = EnvKeys.DEBUGGING_ENABLED.key();

        //ASSERT
        Assertions.assertEquals(key, result);
    }

    @Test
    public void lang_parses_correctly() {
        //ARRANGE
        String input = "true";

        //ACT
        var result = EnvKeys.DEBUGGING_ENABLED.adapter().apply(input);

        //ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void lang_returns_false_when_invalid() {
        //ARRANGE
        String input = "invalid";

        //ACT
        var result = EnvKeys.DEBUGGING_ENABLED.adapter().apply(input);

        //ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result);
    }
}
