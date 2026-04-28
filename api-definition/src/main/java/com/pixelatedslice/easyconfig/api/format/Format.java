package com.pixelatedslice.easyconfig.api.format;

import com.pixelatedslice.easyconfig.api.format.builtin.BuiltInFormat;
import org.jspecify.annotations.NonNull;

import java.nio.file.Path;
import java.util.Objects;

@SuppressWarnings("unused")
@FunctionalInterface
public interface Format {
    static boolean isBuiltIn(@NonNull Format format) {
        Objects.requireNonNull(format);
        return format instanceof BuiltInFormat;
    }

    String fileExtension();

    default Path pathWithExtension(@NonNull Path path) {
        Objects.requireNonNull(path);
        return path.resolveSibling(path.getFileName() + "." + this.fileExtension()).toAbsolutePath();
    }
}