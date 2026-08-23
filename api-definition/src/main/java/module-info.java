open module com.pixelatedslice.easyconfig.api {
    uses com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;
    uses com.pixelatedslice.easyconfig.api.format.Format;
    uses com.pixelatedslice.easyconfig.api.config.node.factory.spi.NodeFactoryService;
    requires com.google.common;
    requires org.jspecify;
    requires com.google.errorprone.annotations;
    requires org.jetbrains.annotations;

    exports com.pixelatedslice.easyconfig.api.config.node;
    exports com.pixelatedslice.easyconfig.api.config.node.collection;
    exports com.pixelatedslice.easyconfig.api.config.node.container;
    exports com.pixelatedslice.easyconfig.api.config.node.value;
    exports com.pixelatedslice.easyconfig.api.config.node.env;
    exports com.pixelatedslice.easyconfig.api.editable;
    exports com.pixelatedslice.easyconfig.api.exception;
    exports com.pixelatedslice.easyconfig.api.format;
    exports com.pixelatedslice.easyconfig.api.serialization;
    exports com.pixelatedslice.easyconfig.api.utils.typetoken;
    exports com.pixelatedslice.easyconfig.api.utils.primitive;
    exports com.pixelatedslice.easyconfig.api.validator;
    exports com.pixelatedslice.easyconfig.api.validator.option;
    exports com.pixelatedslice.easyconfig.api.validator.null_policy;
    exports com.pixelatedslice.easyconfig.api.config;
    exports com.pixelatedslice.easyconfig.api.config.node.factory;
    exports com.pixelatedslice.easyconfig.api.config.node.factory.builder;
    exports com.pixelatedslice.easyconfig.api.config.node.factory.spi;
    exports com.pixelatedslice.easyconfig.api.serialization.context;
    exports com.pixelatedslice.easyconfig.api.config.node.serializer;
}