package com.pixelatedslice.easyconfig.api.serialization;

public interface Serializable<T extends Serializable<T>> extends Serializer<T> {
}