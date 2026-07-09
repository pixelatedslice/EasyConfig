import com.pixelatedslice.easyconfig.api.config.node.factory.spi.NodeFactoryService;
import com.pixelatedslice.easyconfig.impl.config.node.factory.spi.NodeFactoryServiceImpl;

open module com.pixelatedslice.easyconfig.impl {
    requires com.google.common;
    requires org.jspecify;
    requires com.pixelatedslice.easyconfig.api;
    requires com.google.auto.service;
    requires com.google.errorprone.annotations;

    provides NodeFactoryService
            with NodeFactoryServiceImpl;
}