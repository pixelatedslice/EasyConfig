package com.pixelatedslice.easyconfig.impl.fileformat.toml;

import com.pixelatedslice.easyconfig.api.config.file.ConfigFile;
import com.pixelatedslice.easyconfig.api.fileformat.FileFormatProvider;
import com.pixelatedslice.easyconfig.api.fileformat.builtin.TomlFileFormat;
import com.pixelatedslice.easyconfig.impl.fileformat.common.JacksonConfigFile;
import org.jspecify.annotations.NonNull;
import tools.jackson.core.JsonEncoding;
import tools.jackson.core.ObjectReadContext;
import tools.jackson.core.ObjectWriteContext;
import tools.jackson.dataformat.toml.TomlFactory;

import java.io.IOException;
import java.nio.file.Files;

public final class TomlFileFormatProvider implements FileFormatProvider<TomlFileFormat> {
    private static final TomlFileFormat fileFormatInstance = TomlFileFormat.instance();
    private static volatile TomlFileFormatProvider INSTANCE;
    private final TomlFactory factory = new TomlFactory();

    private TomlFileFormatProvider() {
    }

    public static TomlFileFormatProvider instance() {
        if (INSTANCE == null) {
            synchronized (TomlFileFormatProvider.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TomlFileFormatProvider();
                }
            }
        }

        return INSTANCE;
    }

    @Override
    public Class<TomlFileFormat> fileFormatClass() {
        return TomlFileFormat.class;
    }

    @Override
    public TomlFileFormat fileFormatInstance() {
        return fileFormatInstance;
    }

    @Override
    public <C extends ConfigFile> void write(@NonNull C configFile
    ) throws IOException {
        var path = fileFormatInstance.pathWithExtension(configFile.filePathWithoutExtension());

        Files.createDirectories(path.getParent());
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        try (var outStream = Files.newOutputStream(path)) {
            try (var generator = this.factory.createGenerator(
                    ObjectWriteContext.empty(),
                    outStream,
                    JsonEncoding.UTF8
            )) {
                JacksonConfigFile.write(generator, configFile);
            }
        }
    }

    @Override
    public <C extends ConfigFile> void load(@NonNull C configFile)
            throws IOException {
        var path = fileFormatInstance.pathWithExtension(configFile.filePathWithoutExtension());
        if (!Files.exists(path)) {
            throw new IOException("The File does not exist!");
        }

        try (var inStream = Files.newInputStream(path)) {
            try (var parser = this.factory.createParser(ObjectReadContext.empty(), inStream)) {
                JacksonConfigFile.read(parser, configFile);
            }
        }
    }
}
