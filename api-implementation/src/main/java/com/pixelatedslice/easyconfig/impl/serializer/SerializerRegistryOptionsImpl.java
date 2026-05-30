package com.pixelatedslice.easyconfig.impl.serializer;

import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistryOptions;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public class SerializerRegistryOptionsImpl implements SerializerRegistryOptions {

    private SerializerRegistryOptions.@NonNull DuplicateRegisterStyle duplicateRegistryStyle = DuplicateRegisterStyle.THROW;

    @Override
    public @NonNull SerializerRegistryOptions duplicateRegisterStyle(@NonNull DuplicateRegisterStyle style) {
        this.duplicateRegistryStyle = Objects.requireNonNull(style);
        return this;
    }

    @Override
    public SerializerRegistryOptions.@NonNull DuplicateRegisterStyle duplicateRegisterStyle() {
        return this.duplicateRegistryStyle;
    }
}
