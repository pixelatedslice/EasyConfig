import com.pixelatedslice.easyconfig.api.serialization.BuiltInSerializer;

open module com.pixelatedslice.easyconfig.impl {
    uses BuiltInSerializer;
    requires com.google.common;
    requires org.jspecify;
    requires com.pixelatedslice.easyconfig.api;
    requires com.google.auto.service;
    requires com.google.errorprone.annotations;
}