open module com.pixelatedslice.easyconfig.impl.fileformat.common {
    exports com.pixelatedslice.easyconfig.impl.fileformat;
    requires org.jspecify;
    requires tools.jackson.core;
    requires com.pixelatedslice.easyconfig.api;
    requires com.google.common;
    requires tools.jackson.databind;
    requires com.pixelatedslice.easyconfig.impl;
}