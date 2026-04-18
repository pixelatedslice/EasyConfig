package com.pixelatedslice.easyconfig.test.fileformat;

import com.pixelatedslice.easyconfig.impl.fileformat.json.JsonFileFormatProvider;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;

public final class JsonTest extends FileFormatTest {
    private JsonTest() {
        super(JsonFileFormatProvider.instance());
    }

    @Test
    void fileOutputTest() throws IOException {
        this.outputAllFields();

        System.out.println(this.pathWithExtension);

        if (!Files.exists(this.pathWithExtension)) {
            this.fileFormatProvider.write(this.easyConfig, this.file);
        }

        this.fileFormatProvider.load(this.easyConfig, this.file);
        this.outputAllFields();
    }
}
