module com.pixelatedslice.easyconfig.impl.serialization.builtin.bukkit {
    uses com.pixelatedslice.easyconfig.api.config.section.ConfigSectionBuilder;
    requires org.bukkit;
    requires com.pixelatedslice.easyconfig.api;
    requires org.jspecify;
    requires com.google.common;

    exports com.pixelatedslice.easyconfig.impl.serialization.builtin.bukkit;
}