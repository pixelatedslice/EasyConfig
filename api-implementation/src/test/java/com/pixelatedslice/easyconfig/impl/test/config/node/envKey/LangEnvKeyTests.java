package com.pixelatedslice.easyconfig.impl.test.config.node.envKey;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvKeys;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@NullMarked
public class LangEnvKeyTests {

    @Test
    public void lang_has_correct_key() {
        //ARRANGE
        final var key = "LANG";

        //ACT
        final var result = EnvKeys.LANG.key();

        //ASSERT
        Assertions.assertEquals(key, result);
    }

    @Test
    public void lang_splits_valid_result_into_Lang_Result() {
        //ARRANGE
        final String input = "en_GB.UTF-8";

        //ACT
        final var result = EnvKeys.LANG.adapter().apply(input);

        //ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals("en_gb", result.language().toString());
        Assertions.assertEquals("UTF-8", result.encoding());
    }

    @Test
    public void lang_returns_null_when_invalid_without_dot_input() {
        //ARRANGE
        final String input = "invalid";

        //ACT
        final var result = EnvKeys.LANG.adapter().apply(input);

        //ASSERT
        Assertions.assertNull(result);
    }
}
