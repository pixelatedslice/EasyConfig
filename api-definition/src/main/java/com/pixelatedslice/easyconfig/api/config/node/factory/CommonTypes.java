package com.pixelatedslice.easyconfig.api.config.node.factory;

import com.google.common.reflect.TypeToken;
import org.jspecify.annotations.NullMarked;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.util.UUID;

@SuppressWarnings("unused")
@NullMarked
public interface CommonTypes {
    TypeToken<BigDecimal> BIG_DECIMAL = TypeToken.of(BigDecimal.class);
    TypeToken<BigInteger> BIG_INTEGER = TypeToken.of(BigInteger.class);
    TypeToken<Boolean> BOOLEAN = TypeToken.of(boolean.class);
    TypeToken<Byte> BYTE = TypeToken.of(byte.class);
    TypeToken<Character> CHARACTER = TypeToken.of(char.class);
    TypeToken<Double> DOUBLE = TypeToken.of(double.class);
    TypeToken<Duration> DURATION = TypeToken.of(Duration.class);
    TypeToken<Float> FLOAT = TypeToken.of(float.class);
    TypeToken<Integer> INTEGER = TypeToken.of(int.class);
    TypeToken<Long> LONG = TypeToken.of(long.class);
    TypeToken<Short> SHORT = TypeToken.of(short.class);
    TypeToken<String> STRING = TypeToken.of(String.class);
    TypeToken<UUID> UUID = TypeToken.of(UUID.class);
}
