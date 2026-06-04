package com.pixelatedslice.easyconfig.api.format;

import com.pixelatedslice.easyconfig.api.config.BuiltConfig;
import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import org.jspecify.annotations.NullMarked;
import com.pixelatedslice.easyconfig.api.config.node.NodeBuilder;
import org.jspecify.annotations.NonNull;

import java.io.*;
import java.nio.file.Path;

@NullMarked
public interface Format {
    @NonNull String @NonNull [] fileExtensions();

    default @NonNull String preferredFileExtension() {
        return this.fileExtensions()[0];
    }

    void write(@NonNull BuiltConfig config, @NonNull Writer writer);

    default @NonNull String writeString(@NonNull BuiltConfig config) {
        var writer = new StringWriter();
        write(config, writer);
        return writer.toString();
    }

    default void writeToFile(@NonNull BuiltConfig config, @NonNull File file) throws IOException {
        write(config, new FileWriter(file));
    }

    default void writeToFile(@NonNull BuiltConfig config, @NonNull Path path) throws IOException {
        writeToFile(config, path.toFile());
    }

    Config read(ConfigStructure structure, Reader reader);

    default Config readFile(ConfigStructure structure, File file)
            throws FileNotFoundException {
        return this.read(structure, new FileReader(file));
    }

    default Config readFile(ConfigStructure structure, Path path)
            throws FileNotFoundException {
        return this.readFile(structure, path.toFile());
    }

    default Config readString(ConfigStructure structure, String rawString) {
        final var reader = new StringReader(rawString);
        return this.read(structure, reader);
    }
}