package com.pixelatedslice.easyconfig.api.format.builtin;

public final class JsonFormat implements BuiltInFormat {
    private JsonFormat() {
    }

    public static JsonFormat instance() {
        return JsonFormatHolder.INSTANCE;
    }

    @Override
    public String fileExtension() {
        return "json";
    }

    public static final class JsonFormatHolder {
        private static final JsonFormat INSTANCE = new JsonFormat();

        private JsonFormatHolder() {
        }
    }
}