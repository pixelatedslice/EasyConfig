package com.pixelatedslice.easyconfig.api.validator.option;

import com.pixelatedslice.easyconfig.api.exception.ValidationException;
import com.pixelatedslice.easyconfig.api.validator.ValidatorContext;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

@SuppressWarnings("unused")
@NullMarked
public final class ValidationOptions {

    private ValidationOptions() {
    }

    public static <T> ValidateOption<T> returnEmpty() {
        return (T _, ValidatorContext _) -> Optional.empty();
    }

    public static <T> ValidateOption<T> throwExceptions() {
        return (T _, ValidatorContext context) -> {
            throw new ValidationException(context);
        };
    }

    public static <T> ValidateOption<T> ignoreValidation() {
        return (@Nullable T value, ValidatorContext _) -> Optional.ofNullable(value);
    }
}
