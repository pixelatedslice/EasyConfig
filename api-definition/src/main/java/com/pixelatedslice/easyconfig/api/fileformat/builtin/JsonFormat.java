package com.pixelatedslice.easyconfig.api.fileformat.builtin;

public final class JsonFormat implements BuiltInFormat {
    private static volatile JsonFormat INSTANCE;

    private JsonFormat() {
    }

    public static JsonFormat instance() {
        if (INSTANCE == null) {
            synchronized (JsonFormat.class) {
                if (INSTANCE == null) {
                    INSTANCE = new JsonFormat();
                }
            }
        }

        return INSTANCE;
    }

    @Override
    public String fileExtension() {
        return "json";
    }
}