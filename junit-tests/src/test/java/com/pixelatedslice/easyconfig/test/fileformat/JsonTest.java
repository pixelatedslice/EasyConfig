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
            this.fileFormatProvider.save(this.easyConfig, this.file);
        }

        this.fileFormatProvider.load(this.easyConfig, this.file);
        this.outputAllFields();
    }

    @Test
    void stringTest() throws IOException {
        this.outputAllFields();

        var content = this.fileFormatProvider.writeToString(this.easyConfig, this.file);
        System.out.println(content);
        content = content.replace("admin", "nomen");

        this.fileFormatProvider.parseFromString(this.easyConfig, this.file, content);

        this.outputAllFields();
    }
}
