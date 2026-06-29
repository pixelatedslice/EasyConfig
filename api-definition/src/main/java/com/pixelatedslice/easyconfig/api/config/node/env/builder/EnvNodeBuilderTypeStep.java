package com.pixelatedslice.easyconfig.api.config.node.env.builder;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.builder.BuilderStep;
import com.pixelatedslice.easyconfig.api.utils.typetoken.TypeTokenUtils;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@FunctionalInterface
@NullMarked
public interface EnvNodeBuilderTypeStep<T> extends BuilderStep {
    EnvNodeBuilderEnvStep<T> type(TypeToken<T> typeToken);

    default EnvNodeBuilderEnvStep<T> type(Class<T> simpleType) {
        Objects.requireNonNull(simpleType);

        return this.type(TypeTokenUtils.getSimpleOrThrow(simpleType));
    }
}
