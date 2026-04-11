package com.pixelatedslice.easyconfig.impl.comments;

import com.pixelatedslice.easyconfig.api.comments.MutableAndCommentable;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractMutableAndCommentable implements MutableAndCommentable {
    protected final List<Consumer<Collection<String>>> commentUpdates = new ArrayList<>();

    @Override
    public void addComments(@NonNull String @NonNull ... comments) {
        this.commentUpdates.add((Collection<String> list) -> {
            Collections.addAll(list, comments);
        });
    }

    @Override
    public void removeComments(@NonNull String @NonNull ... comments) {
        this.commentUpdates.add((Collection<String> list) -> {
            list.removeAll(List.of(comments));
        });
    }

    @Override
    public void clearComments() {
        this.commentUpdates.add(Collection::clear);
    }
}
