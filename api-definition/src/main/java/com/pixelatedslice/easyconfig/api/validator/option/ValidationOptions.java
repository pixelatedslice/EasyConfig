package com.pixelatedslice.easyconfig.api.validator.option;

import com.pixelatedslice.easyconfig.api.exception.ValidationException;

import java.util.Optional;

public class ValidationOptions {

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
