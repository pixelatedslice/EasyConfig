package com.pixelatedslice.easyconfig.impl.test.config.node.envKey;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvKeys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class HomeEnvKeyTests {

    @Test
    public void home_has_correct_key() {
        //ARRANGE
        var key = "HOME";

        //ACT
        var result = EnvKeys.HOME.key();

        //ASSERT
        Assertions.assertEquals(key, result);
    }

    @Test
    public void home_splits_valid_result_into_Lang_Result() throws IOException {
        //ARRANGE
        Path expected = new File(".").toPath().toRealPath();
        String input = expected.toString();

        //ACT
        var result = EnvKeys.HOME.adapter().apply(input);

        //ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void home_returns_null_when_invalid() {
        //ARRANGE
        String input = "!";

        //ACT
        var result = EnvKeys.HOME.adapter().apply(input);

        //ASSERT
        Assertions.assertNull(result);
    }
}
