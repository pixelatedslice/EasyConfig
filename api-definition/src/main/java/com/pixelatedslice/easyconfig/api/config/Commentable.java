package com.pixelatedslice.easyconfig.api.config;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Represents an object that supports the addition, removal, and retrieval of comments.
 * This interface provides a mechanism for associating textual comments with an object,
 * enabling better documentation or contextual descriptions.
 */
public interface Commentable {
    /**
     * Retrieves the collection of comments associated with the implementing object.
     *
     * @return a collection of comments as {@link Collection<String>}
     * @throws NullPointerException if a null value is encountered
     */
    @NotNull Collection<String> comments();

    /**
     * Adds a comment to the collection of comments.
     *
     * @param comment the comment to be added, must not be null
     * @throws NullPointerException if the comment is null
     */
    void addComment(@NotNull String comment);

    /**
     * Removes the specified comment from the collection of comments.
     * If the specified comment does not exist in the collection, this method has no effect.
     *
     * @param comment the comment to be removed, must not be null
     * @throws NullPointerException if the provided comment is null
     */
    void removeComment(@NotNull String comment);

    /**
     * Removes the comment at the specified index within the collection of comments.
     *
     * @param index the index of the comment to remove
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= comments.size())
     */
    void removeComment(int index);

    /**
     * Removes all comments associated with the implementing object.
     * After the execution of this method, the collection of comments will be empty.
     *
     * @throws NullPointerException if the internal comments collection is null
     */
    void clearComments();
}
