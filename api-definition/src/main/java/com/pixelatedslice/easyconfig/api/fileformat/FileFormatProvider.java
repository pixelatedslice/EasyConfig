package com.pixelatedslice.easyconfig.api.fileformat;

import com.pixelatedslice.easyconfig.api.config.file.ConfigFile;
import org.jspecify.annotations.NonNull;

import java.io.IOException;

public interface FileFormatProvider<F extends FileFormat> {
    Class<F> fileFormatClass();

    F fileFormatInstance();

    <C extends ConfigFile> void write(@NonNull C configFile) throws IOException;

    <C extends ConfigFile> void load(@NonNull C configFile) throws IOException;
}