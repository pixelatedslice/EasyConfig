package com.pixelatedslice.easyconfig.api.utils.type_token;

import com.google.common.reflect.TypeToken;
import org.jspecify.annotations.NonNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TypeTokenUtils {
    private TypeTokenUtils() {
    }

    public static boolean isSimpleTypeToken(@NonNull TypeToken<?> typeToken) {
        Type type = typeToken.getType();

        if (!(type instanceof Class<?> clazz)) {
            return false;
        }

        Class<?> leafType = clazz;
        while (leafType.isArray()) {
            leafType = leafType.getComponentType();
        }

        return leafType.getTypeParameters().length == 0;
    }

    public static <T> boolean hasCorrectType(@NonNull T value, @NonNull TypeToken<?> typeToken) {
        return TypeTokenTypeComparer.hasCorrectType(value, typeToken);
    }

    public static boolean matches(@NonNull TypeToken<?> typeTokenOne, @NonNull TypeToken<?> typeTokenTwo) {
        return typeTokenOne.equals(typeTokenTwo);
    }

    public static <T> boolean matchingClass(
            @NonNull Class<T> targetType,
            @NonNull TypeToken<?> typeToken,
            @NonNull Class<?> type
    ) {
        return matchingClass(targetType, typeToken.getRawType(), type);
    }

    public static <T> boolean matchingClass(
            @NonNull Class<T> targetType,
            @NonNull Class<?> typeTokenRawType,
            @NonNull Class<?> type
    ) {
        return typeTokenRawType.isAssignableFrom(type)
                && targetType.isAssignableFrom(type)
                && targetType.isAssignableFrom(typeTokenRawType);
    }

    public static @NonNull List<@NonNull TypeToken<?>> generics(@NonNull TypeToken<?> typeToken) {
        List<TypeToken<?>> generics = new ArrayList<>();
        for (var typeParameter : typeToken.getRawType().getTypeParameters()) {
            generics.add(typeToken.resolveType(typeParameter));
        }
        return Collections.unmodifiableList(generics);
    }
}
