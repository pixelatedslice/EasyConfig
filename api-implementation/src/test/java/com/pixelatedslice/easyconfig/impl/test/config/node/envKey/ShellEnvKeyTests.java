package com.pixelatedslice.easyconfig.impl.test.config.node.envKey;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvKeys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ShellEnvKeyTests {

    @Test
    public void editor_has_correct_key() {
        //ARRANGE
        var key = "SHELL";

        //ACT
        var result = EnvKeys.SHELL.key();

        //ASSERT
        Assertions.assertEquals(key, result);
    }

    @Test
    public void editor_splits_valid_result_into_Lang_Result() throws IOException {
        //ARRANGE
        Path expected = new File(".").toPath().toRealPath();
        String input = expected.toString();

        //ACT
        var result = EnvKeys.SHELL.adapter().apply(input);

        //ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void editor_returns_null_when_invalid() {
        //ARRANGE
        String input = "!";

        //ACT
        var result = EnvKeys.SHELL.adapter().apply(input);

        //ASSERT
        Assertions.assertNull(result);
    }
}
