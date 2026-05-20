package com.pixelatedslice.easyconfig.api.validator.option;

import com.pixelatedslice.easyconfig.api.validator.ValidatorContext;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

@FunctionalInterface
public interface ValidateOption<Value> {

    Optional<Value> onValidationError(@Nullable Value value, ValidatorContext context);

}
