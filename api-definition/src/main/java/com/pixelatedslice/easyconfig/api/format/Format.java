package com.pixelatedslice.easyconfig.api.format;

import com.pixelatedslice.easyconfig.api.config.BuiltConfig;
import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import org.jspecify.annotations.NullMarked;

import java.io.*;
import java.nio.file.Path;

@NullMarked
public interface Format {
    String[] fileExtensions();

    default String preferredFileExtension() {
        return this.fileExtensions()[0];
    }

    void write(BuiltConfig config, Writer writer);

    default String writeString(BuiltConfig config) {
        final var writer = new StringWriter();
        this.write(config, writer);
        return writer.toString();
    }

    default void writeToFile(BuiltConfig config, File file) throws IOException {
        this.write(config, new FileWriter(file));
    }

    default void writeToFile(BuiltConfig config, Path path) throws IOException {
        this.writeToFile(config, path.toFile());
    }

    Config read(ConfigStructure structure, Reader reader);

    default Config readFile(ConfigStructure structure, File file) throws FileNotFoundException {
        return this.read(structure, new FileReader(file));
    }

    default Config readFile(ConfigStructure structure, Path path) throws FileNotFoundException {
        return this.readFile(structure, path.toFile());
    }

    default Config readString(ConfigStructure structure, String rawString) {
        final var reader = new StringReader(rawString);
        return this.read(structure, reader);
    }
}