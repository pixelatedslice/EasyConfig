package com.pixelatedslice.easyconfig.api.validator;

import org.jspecify.annotations.NullMarked;

import org.jspecify.annotations.Nullable;

import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

@SuppressWarnings({"unused", "LambdaUnfriendlyMethodOverload"})
@FunctionalInterface
@NullMarked
public interface Validator<T extends @Nullable Object> {
    static <T> Validator<T> empty() {
        return (@Nullable T _, ValidatorContext _) -> {
        };
    }

    static <T> void validate(T value, Predicate<? super T> predicate,
            ValidatorContext context) {
        if (!predicate.test(value)) {
            context.error(
                    "Invalid value: \"%s\". The input does not meet the requirements of the validation predicate.",
                    value
            );
        }
    }

    static void validate(int value, IntPredicate predicate,
            ValidatorContext context) {
        if (!predicate.test(value)) {
            context.error(
                    "Invalid value: \"%s\". The input does not meet the requirements of the validation predicate.",
                    value
            );
        }
    }

    static void validate(long value, LongPredicate predicate,
            ValidatorContext context) {
        if (!predicate.test(value)) {
            context.error(
                    "Invalid value: \"%s\". The input does not meet the requirements of the validation predicate.",
                    value
            );
        }
    }

    static void validate(double value, DoublePredicate predicate,
            ValidatorContext context) {
        if (!predicate.test(value)) {
            context.error(
                    "Invalid value: \"%s\". The input does not meet the requirements of the validation predicate.",
                    value
            );
        }
    }

    void validate(@Nullable T value, ValidatorContext context);
}

