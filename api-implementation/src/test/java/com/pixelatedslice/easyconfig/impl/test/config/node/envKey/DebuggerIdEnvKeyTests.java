package com.pixelatedslice.easyconfig.impl.test.config.node.envKey;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvKeys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DebuggerIdEnvKeyTests {

    @Test
    public void lang_has_correct_key() {
        //ARRANGE
        var key = "DEBUGGER_ID";

        //ACT
        var result = EnvKeys.DEBUGGER_ID.key();

        //ASSERT
        Assertions.assertEquals(key, result);
    }

    @Test
    public void lang_splits_valid_result_into_Lang_Result() {
        //ARRANGE
        String input = "MyDebugger";

        //ACT
        var result = EnvKeys.DEBUGGER_ID.adapter().apply(input);

        //ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals(input, result);
    }
}
