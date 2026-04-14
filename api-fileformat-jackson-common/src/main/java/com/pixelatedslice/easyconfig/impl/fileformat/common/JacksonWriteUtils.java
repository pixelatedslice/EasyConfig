package com.pixelatedslice.easyconfig.impl.fileformat.common;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import tools.jackson.core.JsonGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

public final class JacksonWriteUtils {

    private JacksonWriteUtils() {
    }

    public static <T> void write(@NonNull JsonGenerator generator, @Nullable T value) {
        switch (value) {
            case null -> generator.writeNull();

            case String string -> generator.writeString(string);

            case Boolean bool -> generator.writeBoolean(bool);
            case boolean[] booleans -> writeArray(generator, booleans);

            case Short number -> generator.writeNumber(number);
            case short[] numbers -> writeArray(generator, numbers);

            case Integer number -> generator.writeNumber(number);
            case int[] numbers -> writeArray(generator, numbers);

            case Long number -> generator.writeNumber(number);
            case long[] numbers -> writeArray(generator, numbers);

            case BigInteger number -> generator.writeNumber(number);

            case Double number -> generator.writeNumber(number);
            case double[] numbers -> writeArray(generator, numbers);

            case Float number -> generator.writeNumber(number);
            case float[] numbers -> writeArray(generator, numbers);

            case BigDecimal number -> generator.writeNumber(number);

            case byte[] binary -> generator.writeBinary(binary);

            case Object[] objects -> writeArray(generator, objects);

            case Collection<?> collection -> writeArray(generator, collection.toArray());

            default -> throw new IllegalStateException("Value with unexpected type: " + value);
        }
    }

    private static <T> void writeArray(@NonNull JsonGenerator generator, @NonNull T @NonNull [] array) {
        generator.writeStartArray();
        for (var item : array) {
            write(generator, item);
        }
        generator.writeEndArray();
    }

    private static void writeArray(@NonNull JsonGenerator generator, boolean @NonNull [] array) {
        generator.writeStartArray();
        for (var item : array) {
            generator.writeBoolean(item);
        }
        generator.writeEndArray();
    }

    private static void writeArray(@NonNull JsonGenerator generator, short @NonNull [] array) {
        generator.writeStartArray();
        for (var item : array) {
            generator.writeNumber(item);
        }
        generator.writeEndArray();
    }

    private static void writeArray(@NonNull JsonGenerator generator, int @NonNull [] array) {
        generator.writeStartArray();
        for (var item : array) {
            generator.writeNumber(item);
        }
        generator.writeEndArray();
    }

    private static void writeArray(@NonNull JsonGenerator generator, long @NonNull [] array) {
        generator.writeStartArray();
        for (var item : array) {
            generator.writeNumber(item);
        }
        generator.writeEndArray();
    }

    private static void writeArray(@NonNull JsonGenerator generator, double @NonNull [] array) {
        generator.writeStartArray();
        for (var item : array) {
            generator.writeNumber(item);
        }
        generator.writeEndArray();
    }

    private static void writeArray(@NonNull JsonGenerator generator, float @NonNull [] array) {
        generator.writeStartArray();
        for (var item : array) {
            generator.writeNumber(item);
        }
        generator.writeEndArray();
    }
}
