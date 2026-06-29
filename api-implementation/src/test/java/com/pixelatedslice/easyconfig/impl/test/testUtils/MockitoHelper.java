package com.pixelatedslice.easyconfig.impl.test.testUtils;

import org.jspecify.annotations.NullMarked;

import org.mockito.Mockito;

@NullMarked
public class MockitoHelper {

    public static <T> T whenReturn(T methodCall, T value) {
        Mockito.when(methodCall).thenReturn(value);
        return value;
    }
}
