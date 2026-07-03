import com.pixelatedslice.easyconfig.api.config.node.factory.NodeBuilders;
import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistry;

open module com.pixelatedslice.easyconfig.api {
    uses SerializerRegistry;
    uses com.pixelatedslice.easyconfig.api.format.Format;
    uses NodeBuilders;
    requires com.google.common;
    requires org.jspecify;
    requires com.google.errorprone.annotations;

    exports com.pixelatedslice.easyconfig.api.builder;
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
    exports com.pixelatedslice.easyconfig.api.config.node.factory.nodes;
    exports com.pixelatedslice.easyconfig.api.config.node.factory.nodes.common;
}