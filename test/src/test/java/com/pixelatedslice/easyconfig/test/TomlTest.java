package com.pixelatedslice.easyconfig.test;

import com.pixelatedslice.easyconfig.impl.fileformat.toml.TomlFileFormatProvider;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;

public final class TomlTest extends FileFormatTest {
    private TomlTest() {
        super(TomlFileFormatProvider.instance());
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
