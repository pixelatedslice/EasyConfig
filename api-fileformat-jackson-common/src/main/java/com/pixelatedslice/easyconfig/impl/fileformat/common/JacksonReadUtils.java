package com.pixelatedslice.easyconfig.impl.fileformat.common;

import com.google.common.reflect.TypeToken;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JsonToken;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public final class JacksonReadUtils {
    private static final Map<TypeToken<?>, TypeReader> FLAT_REGISTRY = new HashMap<>();
    private static final Map<Class<?>, TypeTokenTypeReader> TYPE_TOKEN_REGISTRY = new HashMap<>();
    private static final TypeToken<Collection<?>> collectionTypeToken = new TypeToken<>() {
    };

    static {
        // Register Primitives
        FLAT_REGISTRY.put(TypeToken.of(BigDecimal.class), JsonParser::getDecimalValue);
        FLAT_REGISTRY.put(TypeToken.of(BigInteger.class), JsonParser::getBigIntegerValue);
        FLAT_REGISTRY.put(TypeToken.of(boolean[].class), JacksonReadUtils::readBooleanArray);
        FLAT_REGISTRY.put(TypeToken.of(Boolean.class), JsonParser::getBooleanValue);
        FLAT_REGISTRY.put(TypeToken.of(byte[].class), JsonParser::getBinaryValue);
        TYPE_TOKEN_REGISTRY.put(Deque.class, JacksonReadUtils::readDeque);
        FLAT_REGISTRY.put(TypeToken.of(double[].class), JacksonReadUtils::readDoubleArray);
        FLAT_REGISTRY.put(TypeToken.of(Double.class), JsonParser::getDoubleValue);
        FLAT_REGISTRY.put(TypeToken.of(float[].class), JacksonReadUtils::readFloatArray);
        FLAT_REGISTRY.put(TypeToken.of(Float.class), JsonParser::getFloatValue);
        FLAT_REGISTRY.put(TypeToken.of(int[].class), JacksonReadUtils::readIntArray);
        FLAT_REGISTRY.put(TypeToken.of(Integer.class), JsonParser::getIntValue);
        TYPE_TOKEN_REGISTRY.put(List.class, JacksonReadUtils::readList);
        FLAT_REGISTRY.put(TypeToken.of(long[].class), JacksonReadUtils::readLongArray);
        FLAT_REGISTRY.put(TypeToken.of(Long.class), JsonParser::getLongValue);
        TYPE_TOKEN_REGISTRY.put(Object[].class, JacksonReadUtils::readArray);
        TYPE_TOKEN_REGISTRY.put(Set.class, JacksonReadUtils::readSet);
        FLAT_REGISTRY.put(TypeToken.of(short[].class), JacksonReadUtils::readShortArray);
        FLAT_REGISTRY.put(TypeToken.of(Short.class), JsonParser::getShortValue);
        FLAT_REGISTRY.put(TypeToken.of(String.class), JsonParser::getString);
    }

    private JacksonReadUtils() {
    }

    @SuppressWarnings("unchecked")
    public static <T> @Nullable T read(@NonNull JsonParser parser, TypeToken<T> expectedType) throws IOException {
        if (parser.currentToken() == JsonToken.VALUE_NULL) {
            return null;
        }

        var reader = FLAT_REGISTRY.get(expectedType);
        if (reader != null) {
            return (T) reader.read(parser);
        }

        var ttReader = TYPE_TOKEN_REGISTRY.get(expectedType.getRawType());
        if (ttReader != null) {
            if (collectionTypeToken.isSupertypeOf(expectedType)) {
                TypeToken<?> componentType = expectedType.resolveType(Collection.class.getTypeParameters()[0]);
                return (T) ttReader.read(parser, componentType);
            }

            return (T) ttReader.read(parser, expectedType.getComponentType());
        }

        throw new IllegalStateException("Value with unexpected type: " + expectedType);
    }

    private static <T> @NonNull List<?> readList(
            @NonNull JsonParser parser,
            @NonNull TypeToken<? extends T> expectedType
    ) throws IOException {
        return new ArrayList<>(Arrays.asList(readArray(parser, expectedType)));
    }

    private static <T> @NonNull Set<?> readSet(
            @NonNull JsonParser parser,
            @NonNull TypeToken<? extends T> expectedType
    ) throws IOException {
        return new HashSet<>(Arrays.asList(readArray(parser, expectedType)));
    }

    private static <T> @NonNull Deque<?> readDeque(
            @NonNull JsonParser parser,
            @NonNull TypeToken<? extends T> expectedType
    ) throws IOException {
        return new ArrayDeque<>(Arrays.asList(readArray(parser, expectedType)));
    }

    @SuppressWarnings("unchecked")
    private static <T> @NonNull T @NonNull [] readArray(
            @NonNull JsonParser parser,
            @NonNull TypeToken<? extends T> expectedType
    ) throws IOException {
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
            list.add(parser.getBooleanValue());
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
            list.add(parser.getShortValue());
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
            list.add(parser.getIntValue());
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
            list.add(parser.getLongValue());
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
            list.add(parser.getDoubleValue());
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
            list.add(parser.getFloatValue());
        }

        var size = list.size();
        var arr = new float[size];

        for (var i = 0; i < size; i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }

    @FunctionalInterface
    private interface TypeReader {
        Object read(JsonParser parser) throws IOException;
    }

    @FunctionalInterface
    private interface TypeTokenTypeReader {
        <T> Object read(JsonParser parser, TypeToken<T> expectedType) throws IOException;
    }
}
