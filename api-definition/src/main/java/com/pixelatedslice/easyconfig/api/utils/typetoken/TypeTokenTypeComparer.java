package com.pixelatedslice.easyconfig.api.utils.typetoken;

import com.google.common.reflect.TypeToken;
import org.jspecify.annotations.NullMarked;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unused")

@NullMarked
final class TypeTokenTypeComparer {
    private TypeTokenTypeComparer() {
    }

    static <T> boolean hasCorrectType(T value, TypeToken<?> typeToken) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(typeToken);

        final var baseType = typeToken.getRawType();

        if (!baseType.isInstance(value)) {
            return false;
        }

        final var valueClass = value.getClass();

        if (TypeTokenUtils.matchingClass(Collection.class, baseType, valueClass)) {
            return iterable(value, typeToken);
        } else if (valueClass.isArray() && baseType.equals(valueClass)) {
            return iterable(value, typeToken);
        } else if (TypeTokenUtils.matchingClass(Map.class, baseType, valueClass)) {
            return map((Map<?, ?>) value, typeToken);
        }

        return typeToken.getRawType().isInstance(value);
    }

    private static boolean iterable(Object container, TypeToken<?> typeToken) {
        throw new RuntimeException();
    }

    private static boolean map(Map<?, ?> map, TypeToken<?> typeToken) {
        Objects.requireNonNull(map);
        Objects.requireNonNull(typeToken);

        if (map.isEmpty()) {
            return true;
        }

        final var generics = TypeTokenUtils.generics(typeToken);
        final var keyGeneric = generics.get(0);
        final var keyGenericClass = keyGeneric.getRawType();
        final var valueGeneric = generics.get(1);
        final var valueGenericClass = valueGeneric.getRawType();

        for (var entry : map.entrySet()) {
            final var key = entry.getKey();
            final var value = entry.getValue();

            if (!keyGenericClass.isInstance(key)
                    || !valueGenericClass.isInstance(value)
                    || !hasCorrectType(key, keyGeneric)
                    || !hasCorrectType(value, valueGeneric)
            ) {
                return false;
            }
        }

        return true;
    }
}
