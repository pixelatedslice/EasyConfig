package com.pixelatedslice.easyconfig.test.fileformat;

import com.pixelatedslice.easyconfig.api.EasyConfig;
import com.pixelatedslice.easyconfig.api.config.file.ConfigFile;
import com.pixelatedslice.easyconfig.impl.fileformat.yaml.YamlFileFormatProvider;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

public class EnvTest {

    @Test
    public void shouldGetEnv() throws IOException {
        var easyConfig = EasyConfig.instance().copy();
        var configFile = ConfigFile.builder()
                .filePath(Path.of("env_test"))
                .env("u", "test_username", String.class)
                .env("p", "test_port", int.class)
                .build();

        var str = YamlFileFormatProvider.instance().writeToString(easyConfig, configFile);
        System.out.println(str);

        YamlFileFormatProvider.instance().parseFromString(easyConfig, configFile, str);
        System.out.println(configFile);
    }
}
