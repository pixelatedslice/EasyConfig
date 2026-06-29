package com.pixelatedslice.easyconfig.impl.fileformat.json;

import com.pixelatedslice.easyconfig.api.config.BuiltConfig;
import com.pixelatedslice.easyconfig.api.config.Config;
import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import com.pixelatedslice.easyconfig.api.format.Format;
import org.jspecify.annotations.NullMarked;

import java.io.Reader;
import java.io.Writer;

@NullMarked
public class JsonFormat implements Format {
    @Override
    public String[] fileExtensions() {
        return new String[]{".json"};
    }

    @Override
    public void write(BuiltConfig config, Writer writer) {
        //TODO
    }

    @Override
    public Config read(ConfigStructure structure, Reader reader) {
        //TODO
        return null;
    }
}
