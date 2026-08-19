package com.pixelatedslice.easyconfig.impl.config;

import com.pixelatedslice.easyconfig.api.config.ConfigStructure;
import com.pixelatedslice.easyconfig.api.config.node.Node;
import org.jspecify.annotations.NullMarked;

@NullMarked
public record ConfigStructureImpl(Node root) implements ConfigStructure {

}
