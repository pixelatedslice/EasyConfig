package com.pixelatedslice.easyconfig.api.format.builtin;

public final class YamlFormat implements BuiltInFormat {
    private YamlFormat() {
    }

    public static YamlFormat instance() {
        return YamlFormatHolder.INSTANCE;
    }

    @Override
    public String fileExtension() {
        return "yml";
    }

    private static final class YamlFormatHolder {
        private static final YamlFormat INSTANCE = new YamlFormat();
    }
}