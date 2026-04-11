package com.pixelatedslice.easyconfig.impl.comments;

import com.pixelatedslice.easyconfig.api.comments.Commentable;
import org.jspecify.annotations.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class AbstractCommentable implements Commentable {
    private final @NonNull List<@NonNull String> comments;

    protected AbstractCommentable(@NonNull List<@NonNull String> comments) {
        Objects.requireNonNull(comments);
        this.comments = comments;
    }

    @Override
    public @NonNull List<@NonNull String> comments() {
        synchronized (this.comments) {
            return List.copyOf(this.comments);
        }
    }

    protected void updateComments(
            @NonNull Iterable<? extends @NonNull Consumer<@NonNull Collection<@NonNull String>>> commentUpdates
    ) {
        synchronized (this.comments) {
            for (var consumer : commentUpdates) {
                consumer.accept(this.comments);
            }
        }
    }
}
