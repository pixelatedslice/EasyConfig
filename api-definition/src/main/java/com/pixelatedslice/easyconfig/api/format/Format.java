package com.pixelatedslice.easyconfig.api.format;

import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import com.pixelatedslice.easyconfig.api.config.TrueConfig;
import org.jspecify.annotations.NonNull;

import java.io.*;
import java.nio.file.Path;

public interface Format {
    @NonNull String @NonNull [] fileExtensions();

    default @NonNull String preferredFileExtension() {
        return fileExtensions()[0];
    }

    void write(@NonNull TrueConfig config, @NonNull Writer writer);

    default @NonNull String writeString(@NonNull TrueConfig config) {
        var writer = new StringWriter();
        write(config, writer);
        return writer.toString();
    }

    default void writeToFile(@NonNull TrueConfig config, @NonNull File file) throws IOException {
        write(config, new FileWriter(file));
    }

    default void writeToFile(@NonNull TrueConfig config, @NonNull Path path) throws IOException {
        writeToFile(config, path.toFile());
    }

    @NonNull Config read(@NonNull ConfigStructure structure, @NonNull Reader reader);

    default @NonNull Config readFile(@NonNull ConfigStructure structure, @NonNull File file) throws FileNotFoundException {
        return read(structure, new FileReader(file));
    }

    default @NonNull Config readFile(@NonNull ConfigStructure structure, @NonNull Path path) throws FileNotFoundException {
        return readFile(structure, path.toFile());
    }

    default @NonNull Config readString(@NonNull ConfigStructure structure, @NonNull String rawString) {
        var reader = new StringReader(rawString);
        return this.read(structure, reader);
    }
}