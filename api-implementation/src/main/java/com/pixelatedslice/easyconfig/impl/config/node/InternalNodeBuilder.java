package com.pixelatedslice.easyconfig.impl.config.node;

import com.google.errorprone.annotations.CheckReturnValue;
import com.pixelatedslice.easyconfig.api.config.Config;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.stream.Collectors;

@NullMarked
public interface InternalNodeBuilder<Self> {

    @CheckReturnValue
    Self parent(@Nullable AbstractNode node);

    @Nullable AbstractNode parent();

    @CheckReturnValue
    Self config(@Nullable Config config);

    @Nullable
    Config config();

    @Nullable
    String key();

    Collection<InternalNodeBuilder<?>> children();

    void appendChild(InternalNodeBuilder<?> builder);

    AbstractNode build();

    default void buildChildren(AbstractNode built) {
        @SuppressWarnings("ResultOfMethodCallIgnored") final var immediateChildren = this
                .children()
                .stream()
                .peek((InternalNodeBuilder<?> builder) -> builder.parent(built))
                .collect(Collectors.toMap((InternalNodeBuilder<?> t) -> t, InternalNodeBuilder::build));
        for (var child : immediateChildren.values()) {
            built.internalAppendChild(child);
        }
        for (var entry : immediateChildren.entrySet()) {
            entry.getKey().buildChildren(entry.getValue());
        }

    }

}
