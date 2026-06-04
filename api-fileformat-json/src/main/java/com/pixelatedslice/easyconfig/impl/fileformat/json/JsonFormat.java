package com.pixelatedslice.easyconfig.impl.fileformat.json;

import com.pixelatedslice.easyconfig.api.config.BuiltConfig;
import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import com.pixelatedslice.easyconfig.api.format.Format;
import org.jspecify.annotations.NullMarked;
import com.pixelatedslice.easyconfig.impl.fileformat.ConfigUtils;
import org.jspecify.annotations.NonNull;

import java.io.Reader;
import java.io.Writer;
import java.util.Map;

@NullMarked
public class JsonFormat implements Format {
    @Override
    public @NonNull String @NonNull [] fileExtensions() {
        return new String[]{".json"};
    }

    @Override
    public void write(@NonNull BuiltConfig config, @NonNull Writer writer) {
        var entry = ConfigUtils.writeToDataMapper(config, Map.of());
        entry.getKey().writeValue(writer, entry.getValue());
    }

    @Override
    public @NonNull Config read(@NonNull ConfigStructure structure, @NonNull Reader reader) {
        //TODO
        return null;
    }
}
