package com.pixelatedslice.easyconfig.api.config.node.value.builder;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import com.pixelatedslice.easyconfig.api.utils.typetoken.TypeTokenUtils;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@FunctionalInterface
@NullMarked
public interface ValueNodeBuilderTypeStep<T> extends BuilderStep {
    ValueNodeBuilderValueStep<T> type(TypeToken<T> typeToken);

    default ValueNodeBuilderValueStep<T> type(Class<T> simpleType) {
        Objects.requireNonNull(simpleType);

        return this.type(TypeTokenUtils.getSimpleOrThrow(simpleType));
    }
}