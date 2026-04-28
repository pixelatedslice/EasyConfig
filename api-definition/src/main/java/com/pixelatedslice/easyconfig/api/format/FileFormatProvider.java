package com.pixelatedslice.easyconfig.api.format;

import com.pixelatedslice.easyconfig.api.CopiedEasyConfig;
import com.pixelatedslice.easyconfig.api.config.file.ConfigFile;
import org.jspecify.annotations.NonNull;

import java.io.IOException;

public interface FileFormatProvider<F extends Format> extends FormatProvider<F> {
    <C extends ConfigFile> void save(@NonNull CopiedEasyConfig easyConfig, @NonNull C configFile) throws IOException;

    <C extends ConfigFile> void load(@NonNull CopiedEasyConfig easyConfig, @NonNull C configFile) throws IOException;

    <C extends ConfigFile> String writeToString(@NonNull CopiedEasyConfig easyConfig, @NonNull C configFile);

    <C extends ConfigFile> void parseFromString(
            @NonNull CopiedEasyConfig easyConfig,
            @NonNull C configFile,
            @NonNull String content
    ) throws IOException;
}