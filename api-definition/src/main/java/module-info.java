open module com.pixelatedslice.easyconfig.api {
    requires org.jetbrains.annotations;

    exports com.pixelatedslice.easyconfig.api.serialization.builtin to com.pixelatedslice.easyconfig.api.serialization.builtin.bukkit;

    exports com.pixelatedslice.easyconfig.api;
    exports com.pixelatedslice.easyconfig.api.config;
    exports com.pixelatedslice.easyconfig.api.config.node;
    exports com.pixelatedslice.easyconfig.api.config.section;
    exports com.pixelatedslice.easyconfig.api.exception;
    exports com.pixelatedslice.easyconfig.api.fileformat;
    exports com.pixelatedslice.easyconfig.api.fileformat.builtin;
    exports com.pixelatedslice.easyconfig.api.serialization;
}