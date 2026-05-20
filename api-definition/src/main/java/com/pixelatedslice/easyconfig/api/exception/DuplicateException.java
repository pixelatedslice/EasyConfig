package com.pixelatedslice.easyconfig.api.exception;

import org.jspecify.annotations.NonNull;

public class DuplicateException extends IllegalArgumentException {

    public DuplicateException(@NonNull Object original, @NonNull Object replacement) {
        super(replacement + " cannot replace " + original);
    }
}
