package com.pixelatedslice.easyconfig.api.config.node.factory.builder;

import com.pixelatedslice.easyconfig.api.config.node.env.EnvNode;
import com.pixelatedslice.easyconfig.api.validator.Validator;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Function;

@NullMarked
public interface NodeBuilderEnvStep {
    @NullMarked
    interface VariableStep<T> extends NodeBuilder {
        AdapterValidatorStep<T> variable(String variable);
    }

    @NullMarked
    interface AdapterValidatorStep<T> extends NodeBuilder.BuildStep<EnvNode<T>> {
        AdapterValidatorStep<T> adapter(@Nullable Function<String, @Nullable T> adapter);

        AdapterValidatorStep<T> validator(@Nullable Validator<T> validator);
    }

    @NullMarked
    interface PreDefined<T extends @Nullable Object> {
        String key();

        Function<String, @Nullable T> adapter();
    }
}
