package com.pixelatedslice.easyconfig.impl.test.config.node.envKey;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvKeys;
import com.pixelatedslice.easyconfig.impl.test.testUtils.CollectionAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class PathsEnvKeyTests {

    @Test
    public void paths_has_correct_key() {
        //ARRANGE
        var key = "PATH";

        //ACT
        var result = EnvKeys.PATHS.key();

        //ASSERT
        Assertions.assertEquals(key, result);
    }

    @Test
    public void paths_splits_valid_result_into_Lang_Result() throws IOException {
        //ARRANGE
        String file1 = new File("src/main").getAbsolutePath();
        String file2 = new File("src/test").getAbsolutePath();

        String input = file1 + ";" + file2;

        //ACT
        var result = EnvKeys.PATHS.adapter().apply(input);

        //ASSERT
        Assertions.assertNotNull(result);
        CollectionAssert.isEqualTo(List.of(Path.of(file1), Path.of(file2)), result, false);
    }

    @Test
    public void paths_returns_null_when_invalid() {
        //ARRANGE
        String input = "!";

        //ACT
        var result = EnvKeys.PATHS.adapter().apply(input);

        //ASSERT
        Assertions.assertNull(result);
    }
}
