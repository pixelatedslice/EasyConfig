package com.pixelatedslice.easyconfig.test;

import com.pixelatedslice.easyconfig.impl.fileformat.yaml.YamlFileFormatProvider;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;

public final class YamlTest extends FileFormatTest {
    private YamlTest() {
        super(YamlFileFormatProvider.instance());
    }

    @Test
    void fileOutputTest() throws IOException {
        this.outputAllFields();

        System.out.println(this.pathWithExtension);

        if (!Files.exists(this.pathWithExtension)) {
            this.fileFormatProvider.write(this.file);
        }

        this.fileFormatProvider.load(this.file);
        this.outputAllFields();
    }
}
