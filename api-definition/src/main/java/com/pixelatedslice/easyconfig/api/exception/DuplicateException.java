package com.pixelatedslice.easyconfig.api.exception;

import org.jspecify.annotations.NullMarked;


@NullMarked
public class DuplicateException extends IllegalArgumentException {

    public DuplicateException(Object original, Object replacement) {
        super(replacement + " cannot replace " + original);
    }
}
