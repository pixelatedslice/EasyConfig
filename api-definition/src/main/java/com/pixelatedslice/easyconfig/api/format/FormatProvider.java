package com.pixelatedslice.easyconfig.api.format;

public interface FormatProvider<F extends Format> {
    Class<F> formatClass();

    F formatInstance();
}