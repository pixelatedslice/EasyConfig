package com.pixelatedslice.easyconfig.api.config.node.container;

import com.pixelatedslice.easyconfig.api.config.node.internal.Node;
import com.pixelatedslice.easyconfig.api.editable.EditableVariant;
import org.jspecify.annotations.NullMarked;

import java.util.Collection;
import java.util.List;

@SuppressWarnings({"unused", "UnusedReturnValue"})
@NullMarked
public interface EditableContainerNode extends EditableVariant {
    default EditableContainerNode addNodes(Node... nodes) {
        return this.addNodes(List.of(nodes));
    }

    EditableContainerNode addNodes(Collection<? extends Node> nodes);

    EditableContainerNode setNodes(Collection<? extends Node> nodes);

    default EditableContainerNode removeNodes(Node... nodes) {
        return this.removeNodes(List.of(nodes));
    }

    EditableContainerNode removeNodes(Collection<? extends Node> nodes);

    EditableContainerNode removeNodes(String... keys);

    EditableContainerNode clearNodes();
}
