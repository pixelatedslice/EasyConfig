package com.pixelatedslice.easyconfig.api.format.builtin;

public final class TomlFormat implements BuiltInFormat {
    private TomlFormat() {
    }

    public static TomlFormat instance() {
        return TomlFormatHolder.INSTANCE;
    }

    @Override
    public String fileExtension() {
        return "toml";
    }

    private static final class TomlFormatHolder {
        private static final TomlFormat INSTANCE = new TomlFormat();
    }
}