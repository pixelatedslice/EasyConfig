open module com.pixelatedslice.easyconfig.impl.fileformat.json {
    requires com.google.common;
    requires org.jspecify;
    requires com.pixelatedslice.easyconfig.api;
    requires com.pixelatedslice.easyconfig.impl.fileformat.common;
    requires jdk.compiler;

    exports com.pixelatedslice.easyconfig.impl.fileformat.json;
}