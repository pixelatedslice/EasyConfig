package com.pixelatedslice.easyconfig.api.fileformat.builtin;

public final class TomlFormat implements BuiltInFormat {
    private static volatile TomlFormat INSTANCE;

    private TomlFormat() {
    }

    public static TomlFormat instance() {
        if (INSTANCE == null) {
            synchronized (TomlFormat.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TomlFormat();
                }
            }
        }

        return INSTANCE;
    }

    @Override
    public String fileExtension() {
        return "toml";
    }
}