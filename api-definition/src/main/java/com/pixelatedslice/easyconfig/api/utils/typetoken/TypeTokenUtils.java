package com.pixelatedslice.easyconfig.api.utils.typetoken;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.exception.TypeException;
import org.jspecify.annotations.NullMarked;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted"})
@NullMarked
public final class TypeTokenUtils {
    private TypeTokenUtils() {
    }

    public static <T> TypeToken<T> getSimpleOrThrow(Class<T> simpleType) {
        final var typeToken = TypeToken.of(simpleType);

        throwIfNotSimple(simpleType, typeToken);

        return typeToken;
    }

    public static void throwIfNotSimple(Class<?> simpleType, TypeToken<?> typeToken) {
        Objects.requireNonNull(simpleType);
        Objects.requireNonNull(typeToken);

        if (TypeTokenUtils.isSimpleTypeToken(typeToken)) {
            return;
        }

        throw TypeException.CLASS_USED_IN_PLACE_OF_TYPETOKEN(simpleType);
    }

    public static boolean isSimpleTypeToken(TypeToken<?> typeToken) {
        Objects.requireNonNull(typeToken);

        final Type type = typeToken.getType();

        if (!(type instanceof Class<?> clazz)) {
            return false;
        }

        Class<?> leafType = clazz;
        while (leafType.isArray()) {
            leafType = leafType.getComponentType();
        }

        return leafType.getTypeParameters().length == 0;
    }

    public static <T> boolean hasCorrectType(T value, TypeToken<?> typeToken) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(typeToken);

        return TypeTokenTypeComparer.hasCorrectType(value, typeToken);
    }

    public static boolean matches(TypeToken<?> typeTokenOne, TypeToken<?> typeTokenTwo) {
        Objects.requireNonNull(typeTokenOne);
        Objects.requireNonNull(typeTokenTwo);

        return typeTokenOne.equals(typeTokenTwo);
    }

    public static <T> boolean matchingClass(
            Class<T> targetType,
            TypeToken<?> typeToken,
            Class<?> type
    ) {
        Objects.requireNonNull(targetType);
        Objects.requireNonNull(typeToken);
        Objects.requireNonNull(type);

        return matchingClass(targetType, typeToken.getRawType(), type);
    }

    public static <T> boolean matchingClass(
            Class<T> targetType,
            Class<?> typeTokenRawType,
            Class<?> type
    ) {
        Objects.requireNonNull(targetType);
        Objects.requireNonNull(typeTokenRawType);
        Objects.requireNonNull(type);

        return typeTokenRawType.isAssignableFrom(type)
                && targetType.isAssignableFrom(type)
                && targetType.isAssignableFrom(typeTokenRawType);
    }

    public static List<TypeToken<?>> generics(TypeToken<?> typeToken) {
        Objects.requireNonNull(typeToken);

        final List<TypeToken<?>> generics = new ArrayList<>();
        for (var typeParameter : typeToken.getRawType().getTypeParameters()) {
            generics.add(typeToken.resolveType(typeParameter));
        }
        return Collections.unmodifiableList(generics);
    }
}
