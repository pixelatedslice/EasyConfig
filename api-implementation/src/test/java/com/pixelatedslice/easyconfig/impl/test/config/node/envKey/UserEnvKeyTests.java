package com.pixelatedslice.easyconfig.impl.test.config.node.envKey;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvKeys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserEnvKeyTests {

    @Test
    public void lang_has_correct_key() {
        //ARRANGE
        var key = "USER";

        //ACT
        var result = EnvKeys.USER.key();

        //ASSERT
        Assertions.assertEquals(key, result);
    }

    @Test
    public void lang_splits_valid_result_into_Lang_Result() {
        //ARRANGE
        String input = "MyUser";

        //ACT
        var result = EnvKeys.USER.adapter().apply(input);

        //ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals(input, result);
    }
}
