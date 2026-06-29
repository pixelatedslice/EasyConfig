package com.pixelatedslice.easyconfig.api.validator.option;

import com.pixelatedslice.easyconfig.api.exception.ValidationException;
import org.jspecify.annotations.NullMarked;

import java.util.Optional;

@SuppressWarnings("unused")
@NullMarked
public final class ValidationOptions {

    private ValidationOptions() {
    }

    public static <T> ValidateOption<T> returnEmpty() {
        return (_, _) -> Optional.empty();
    }

    public static <T> ValidateOption<T> throwExceptions() {
        return (_, context) -> {
            throw new ValidationException(context);
        };
    }

    public static <T> ValidateOption<T> ignoreValidation() {
        return (value, _) -> Optional.ofNullable(value);
    }
}
