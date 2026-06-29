package com.pixelatedslice.easyconfig.impl.test.utils;

import com.pixelatedslice.easyconfig.impl.utils.DistinctByGatherer;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@NullMarked
public class DistinctByGathererTests {

    @Test
    public void DistinctByGatherer_filter_and_remove_entries() {
        //ARRANGE
        final var original = List.of("A", "B", "CD", "EF", "GHI", "JKL");
        final var expected = List.of("A", "CD", "GHI");

        //ACT
        final var result = original.stream().gather(new DistinctByGatherer<>(String::length)).toList();

        //ASSERT
        Assertions.assertEquals(expected, result);

    }

    @Test
    public void DistinctByGatherer_split_filter_and_remove_entries() {
        //ARRANGE
        final var original = List.of("A", "B", "CD", "EF", "GHI", "JKL");
        final var expected = List.of("A", "CD", "GHI");

        //ACT
        final var result = original.stream().parallel().gather(new DistinctByGatherer<>(String::length)).toList();

        //ASSERT
        Assertions.assertEquals(expected, result);

    }
}
