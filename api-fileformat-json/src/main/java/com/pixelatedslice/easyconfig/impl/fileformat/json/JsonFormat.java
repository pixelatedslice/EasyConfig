package com.pixelatedslice.easyconfig.impl.fileformat.json;

import com.pixelatedslice.easyconfig.api.config.BuiltConfig;
import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import com.pixelatedslice.easyconfig.api.format.Format;
import com.pixelatedslice.easyconfig.impl.fileformat.ConfigUtils;
import org.jspecify.annotations.NullMarked;
import tools.jackson.databind.ObjectMapper;

import java.io.Reader;
import java.io.Writer;
import java.util.Map;

@NullMarked
public class JsonFormat implements Format {
    @Override
    public String[] fileExtensions() {
        return new String[]{".json"};
    }

    @Override
    public void write(BuiltConfig config, Writer writer) {
        var entry = ConfigUtils.writeToDataMapper(config, Map.of());
        ObjectMapper objectMapper = entry.getKey();
        objectMapper.writeValue(writer, entry.getValue());
    }

    @Override
    public Config read(ConfigStructure structure, Reader reader) {
        //TODO
        return null;
    }
}
