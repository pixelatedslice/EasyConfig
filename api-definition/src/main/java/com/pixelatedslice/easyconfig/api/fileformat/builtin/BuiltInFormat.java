package com.pixelatedslice.easyconfig.api.fileformat.builtin;

import com.pixelatedslice.easyconfig.api.fileformat.Format;

public sealed interface BuiltInFormat extends Format
        permits JsonFormat, TomlFormat, YamlFormat {
}