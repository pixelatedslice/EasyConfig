package com.pixelatedslice.easyconfig.impl.fileformat.common;

import com.google.common.reflect.TypeToken;
import com.pixelatedslice.easyconfig.api.utils.type_token.TypeTokenUtils;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public final class JacksonReadUtils {
    private static final TypeToken<BigDecimal> bigDecimalTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<BigInteger> bigIntegerTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<boolean[]> booleanArrayTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<Boolean> booleanTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<byte[]> byteArrayTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<Collection<?>> collectionTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<Deque<?>> dequeTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<double[]> doubleArrayTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<Double> doubleTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<float[]> floatArrayTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<Float> floatTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<int[]> integerArrayTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<Integer> integerTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<List<?>> listTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<long[]> longArrayTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<Long> longTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<Object[]> objectArrayTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<Set<?>> setTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<short[]> shortArrayTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<Short> shortTypeToken = new TypeToken<>() {
    };
    private static final TypeToken<String> stringTypeToken = new TypeToken<>() {
    };

    private JacksonReadUtils() {
    }

    // Ugly but couldn't think of a different way
    // TODO: Improve
    @SuppressWarnings("unchecked")
    public static <T> @Nullable T read(@NonNull JsonParser parser, TypeToken<T> expectedType) {
        if (parser.currentToken() == JsonToken.VALUE_NULL) {
            return null;
        } else if (TypeTokenUtils.matches(expectedType, stringTypeToken)) {
            return (T) parser.getString();
        } else if (objectArrayTypeToken.isSupertypeOf(expectedType)
        ) {
            return (T) readArray(parser, Objects.requireNonNull(expectedType.getComponentType()));
        } else if (collectionTypeToken.isSupertypeOf(expectedType)) {
            var typeArg = expectedType.resolveType(Collection.class.getTypeParameters()[0]);
            if (listTypeToken.isSupertypeOf(expectedType)) {
                return (T) readList(parser, typeArg);
            } else if (setTypeToken.isSupertypeOf(expectedType)) {
                return (T) readSet(parser, typeArg);
            } else if (dequeTypeToken.isSupertypeOf(expectedType)) {
                return (T) readDeque(parser, typeArg);
            }
        } else if (TypeTokenUtils.matches(expectedType, booleanTypeToken)) {
            return (T) (Object) parser.getBooleanValue();
        } else if (TypeTokenUtils.matches(expectedType, booleanArrayTypeToken)) {
            return (T) readBooleanArray(parser);
        } else if (TypeTokenUtils.matches(expectedType, shortTypeToken)) {
            return (T) (Object) parser.getShortValue();
        } else if (TypeTokenUtils.matches(expectedType, shortArrayTypeToken)) {
            return (T) readShortArray(parser);
        } else if (TypeTokenUtils.matches(expectedType, integerTypeToken)) {
            return (T) (Object) parser.getIntValue();
        } else if (TypeTokenUtils.matches(expectedType, integerArrayTypeToken)) {
            return (T) readIntArray(parser);
        } else if (TypeTokenUtils.matches(expectedType, longTypeToken)) {
            return (T) (Object) parser.getLongValue();
        } else if (TypeTokenUtils.matches(expectedType, longArrayTypeToken)) {
            return (T) readLongArray(parser);
        } else if (TypeTokenUtils.matches(expectedType, bigIntegerTypeToken)) {
            return (T) parser.getBigIntegerValue();
        } else if (TypeTokenUtils.matches(expectedType, doubleTypeToken)) {
            return (T) (Object) parser.getDoubleValue();
        } else if (TypeTokenUtils.matches(expectedType, doubleArrayTypeToken)) {
            return (T) readDoubleArray(parser);
        } else if (TypeTokenUtils.matches(expectedType, floatTypeToken)) {
            return (T) (Object) parser.getFloatValue();
        } else if (TypeTokenUtils.matches(expectedType, floatArrayTypeToken)) {
            return (T) readFloatArray(parser);
        } else if (TypeTokenUtils.matches(expectedType, bigDecimalTypeToken)) {
            return (T) parser.getDecimalValue();
        } else if (TypeTokenUtils.matches(expectedType, byteArrayTypeToken)) {
            return (T) parser.getBinaryValue();
        }

        throw new IllegalStateException("Value with unexpected type: " + expectedType);
    }

    private static <T> @NonNull List<?> readList(
            @NonNull JsonParser parser,
            @NonNull TypeToken<? extends T> expectedType
    ) {
        return new ArrayList<>(Arrays.asList(readArray(parser, expectedType)));
    }

    private static <T> @NonNull Set<?> readSet(
            @NonNull JsonParser parser,
            @NonNull TypeToken<? extends T> expectedType
    ) {
        return new HashSet<>(Arrays.asList(readArray(parser, expectedType)));
    }

    private static <T> @NonNull Deque<?> readDeque(
            @NonNull JsonParser parser,
            @NonNull TypeToken<? extends T> expectedType
    ) {
        return new ArrayDeque<>(Arrays.asList(readArray(parser, expectedType)));
    }

    @SuppressWarnings("unchecked")
    private static <T> @NonNull T @NonNull [] readArray(
            @NonNull JsonParser parser,
            @NonNull TypeToken<? extends T> expectedType
    ) {
        var list = new ArrayList<T>();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            list.add(read(parser, expectedType));
        }

        T[] arr = (T[]) java.lang.reflect.Array.newInstance(expectedType.getRawType(), list.size());
        return list.toArray(arr);
    }

    private static boolean @NonNull [] readBooleanArray(@NonNull JsonParser parser) {
        var list = new ArrayList<Boolean>();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            list.add(read(parser, booleanTypeToken));
        }

        var size = list.size();
        var arr = new boolean[size];

        for (var i = 0; i < size; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    private static short @NonNull [] readShortArray(@NonNull JsonParser parser) {
        var list = new ArrayList<Short>();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            list.add(read(parser, shortTypeToken));
        }

        var size = list.size();
        var arr = new short[size];

        for (var i = 0; i < size; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    private static int @NonNull [] readIntArray(@NonNull JsonParser parser) {
        var list = new ArrayList<Integer>();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            list.add(read(parser, integerTypeToken));
        }

        var size = list.size();
        var arr = new int[size];

        for (var i = 0; i < size; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    private static long @NonNull [] readLongArray(@NonNull JsonParser parser) {
        var list = new ArrayList<Long>();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            list.add(read(parser, longTypeToken));
        }

        var size = list.size();
        var arr = new long[size];

        for (var i = 0; i < size; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    private static double @NonNull [] readDoubleArray(@NonNull JsonParser parser) {
        var list = new ArrayList<Double>();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            list.add(read(parser, doubleTypeToken));
        }

        var size = list.size();
        var arr = new double[size];

        for (var i = 0; i < size; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    private static float @NonNull [] readFloatArray(@NonNull JsonParser parser) {
        var list = new ArrayList<Float>();
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            list.add(read(parser, floatTypeToken));
        }

        var size = list.size();
        var arr = new float[size];

        for (var i = 0; i < size; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }
}
