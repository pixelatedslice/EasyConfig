package com.pixelatedslice.easyconfig.impl.config;

import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import org.jspecify.annotations.NonNull;

public record ConfigStructureImpl(@NonNull Node root) implements ConfigStructure {

}
