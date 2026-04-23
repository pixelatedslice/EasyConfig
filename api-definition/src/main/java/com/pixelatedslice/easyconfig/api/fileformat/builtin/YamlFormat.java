package com.pixelatedslice.easyconfig.api.fileformat.builtin;

public final class YamlFormat implements BuiltInFormat {
    private static volatile YamlFormat INSTANCE;

    private YamlFormat() {
    }

    public static YamlFormat instance() {
        if (INSTANCE == null) {
            synchronized (YamlFormat.class) {
                if (INSTANCE == null) {
                    INSTANCE = new YamlFormat();
                }
            }
        }

        return INSTANCE;
    }

    @Override
    public String fileExtension() {
        return "yml";
    }
}