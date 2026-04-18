package com.pixelatedslice.easyconfig.api.fileformat;

import com.pixelatedslice.easyconfig.api.fileformat.builtin.BuiltInFileFormat;
import org.jspecify.annotations.NonNull;

import java.nio.file.Path;
import java.util.Objects;

@SuppressWarnings("unused")
@FunctionalInterface
public interface FileFormat {
    static boolean isBuiltIn(@NonNull FileFormat fileFormat) {
        Objects.requireNonNull(fileFormat);
        return fileFormat instanceof BuiltInFileFormat;
    }

    String fileExtension();

    default Path pathWithExtension(@NonNull Path path) {
        Objects.requireNonNull(path);
        return path.resolveSibling(path.getFileName() + "." + this.fileExtension()).toAbsolutePath();
    }
}