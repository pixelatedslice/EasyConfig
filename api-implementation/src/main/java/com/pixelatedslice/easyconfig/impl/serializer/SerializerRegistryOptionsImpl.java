package com.pixelatedslice.easyconfig.impl.serializer;

import com.pixelatedslice.easyconfig.api.serialization.SerializerRegistryOptions;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
public class SerializerRegistryOptionsImpl implements SerializerRegistryOptions {

    private SerializerRegistryOptions.DuplicateRegisterStyle duplicateRegistryStyle = DuplicateRegisterStyle.THROW;

    @Override
    public SerializerRegistryOptions duplicateRegisterStyle(DuplicateRegisterStyle style) {
        this.duplicateRegistryStyle = Objects.requireNonNull(style);
        return this;
    }

    @Override
    public SerializerRegistryOptions.DuplicateRegisterStyle duplicateRegisterStyle() {
        return this.duplicateRegistryStyle;
    }
}
