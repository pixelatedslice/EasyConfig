package com.pixelatedslice.easyconfig.api.format.builtin;

import com.pixelatedslice.easyconfig.api.format.Format;

public sealed interface BuiltInFormat extends Format
        permits JsonFormat, TomlFormat, YamlFormat {
}