package com.pixelatedslice.easyconfig.api.validator;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

@FunctionalInterface
public interface Validator<T> {
    static <T> Validator<T> empty() {
        return (@Nullable T _, @NonNull ValidatorContext _) -> {
        };
    }

    static <T> void validate(@NonNull T value, @NonNull Predicate<? super T> predicate,
            @NonNull ValidatorContext context) {
        if (!predicate.test(value)) {
            context.error(
                    "Invalid value: \"%s\". The input does not meet the requirements of the validation predicate.",
                    value
            );
        }
    }

    static void validate(int value, @NonNull IntPredicate predicate,
            @NonNull ValidatorContext context) {
        if (!predicate.test(value)) {
            context.error(
                    "Invalid value: \"%s\". The input does not meet the requirements of the validation predicate.",
                    value
            );
        }
    }

    static void validate(long value, @NonNull LongPredicate predicate,
            @NonNull ValidatorContext context) {
        if (!predicate.test(value)) {
            context.error(
                    "Invalid value: \"%s\". The input does not meet the requirements of the validation predicate.",
                    value
            );
        }
    }

    static void validate(double value, @NonNull DoublePredicate predicate,
            @NonNull ValidatorContext context) {
        if (!predicate.test(value)) {
            context.error(
                    "Invalid value: \"%s\". The input does not meet the requirements of the validation predicate.",
                    value
            );
        }
    }

    void validate(@Nullable T value, @NonNull ValidatorContext context);
}

