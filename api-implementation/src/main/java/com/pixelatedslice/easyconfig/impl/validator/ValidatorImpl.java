package com.pixelatedslice.easyconfig.impl.validator;

import com.pixelatedslice.easyconfig.api.validator.Validator;
import com.pixelatedslice.easyconfig.api.validator.ValidatorContext;
import org.jspecify.annotations.NonNull;

import java.util.function.BiConsumer;

public class ValidatorImpl<T> implements Validator<T> {
    private final BiConsumer<? super T, ? super ValidatorContext> validator;

    public ValidatorImpl(BiConsumer<? super T, ? super ValidatorContext> validator) {
        this.validator = validator;
    }

    @Override
    public void validate(@NonNull T value, @NonNull ValidatorContext context) {
        this.validator.accept(value, context);
    }
}

