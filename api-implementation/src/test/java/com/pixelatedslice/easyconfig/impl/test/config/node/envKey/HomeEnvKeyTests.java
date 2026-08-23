package com.pixelatedslice.easyconfig.impl.test.config.node.envKey;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvKeys;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@NullMarked
public class HomeEnvKeyTests {

    @Test
    public void home_has_correct_key() {
        //ARRANGE
        final var key = "HOME";

        //ACT
        final var result = EnvKeys.HOME.key();

        //ASSERT
        Assertions.assertEquals(key, result);
    }

    @Test
    public void home_splits_valid_result_into_Lang_Result() throws IOException {
        //ARRANGE
        final Path expected = new File(".").toPath().toRealPath();
        final String input = expected.toString();

        //ACT
        final var result = EnvKeys.HOME.adapter().apply(input);

        //ASSERT
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void home_returns_null_when_invalid() {
        //ARRANGE
        final String input = "!";

        //ACT
        final var result = EnvKeys.HOME.adapter().apply(input);

        //ASSERT
        Assertions.assertNull(result);
    }
}
