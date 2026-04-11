package com.pixelatedslice.easyconfig.api.fileformat.builtin;

public final class XmlFileFormat implements BuiltInFileFormat {
    private static volatile XmlFileFormat INSTANCE;

    private XmlFileFormat() {
    }

    public static XmlFileFormat instance() {
        if (INSTANCE == null) {
            synchronized (XmlFileFormat.class) {
                if (INSTANCE == null) {
                    INSTANCE = new XmlFileFormat();
                }
            }
        }

        return INSTANCE;
    }

    @Override
    public String fileExtension() {
        return "xml";
    }
}