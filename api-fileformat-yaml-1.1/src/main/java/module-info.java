open module com.pixelatedslice.easyconfig.impl.fileformat.yaml {
    requires com.google.common;
    requires org.jspecify;
    requires com.pixelatedslice.easyconfig.api;
    requires com.pixelatedslice.easyconfig.impl;
    requires jdk.compiler;
    requires tools.jackson.dataformat.yaml;

    exports com.pixelatedslice.easyconfig.impl.fileformat.yaml;
}