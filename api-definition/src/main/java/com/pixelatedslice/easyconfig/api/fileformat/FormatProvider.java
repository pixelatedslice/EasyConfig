package com.pixelatedslice.easyconfig.api.fileformat;

public interface FormatProvider<F extends Format> {
    Class<F> formatClass();

    F formatInstance();
}