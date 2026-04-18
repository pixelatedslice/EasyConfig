open module com.pixelatedslice.easyconfig.impl.fileformat.toml {
    requires com.google.common;
    requires org.jspecify;
    requires com.pixelatedslice.easyconfig.api;
    requires com.pixelatedslice.easyconfig.impl.fileformat.common;
    requires jdk.compiler;
    requires tools.jackson.dataformat.toml;
    requires tools.jackson.core;

    exports com.pixelatedslice.easyconfig.impl.fileformat.toml;
}