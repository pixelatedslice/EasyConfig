import com.pixelatedslice.easyconfig.api.config.node.factory.NodeBuilders;

open module com.pixelatedslice.easyconfig.impl {
    requires com.google.common;
    requires org.jspecify;
    requires com.pixelatedslice.easyconfig.api;
    requires com.google.auto.service;
    requires com.google.errorprone.annotations;

    provides NodeBuilders with com.pixelatedslice.easyconfig.impl.config.node.factory.NodeBuildersImpl;
}