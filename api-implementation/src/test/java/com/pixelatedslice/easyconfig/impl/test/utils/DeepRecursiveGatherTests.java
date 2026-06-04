package com.pixelatedslice.easyconfig.impl.test.utils;

import com.pixelatedslice.easyconfig.impl.utils.DeepRecursiveGatherer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DeepRecursiveGatherTests {

    @Test
    public void canFindAll() {
        //Arrange
        List<Object> first = List.of(1, 2, 3, 4, 5);
        List<Object> last = List.of(6, 7, 8, 9, 10);
        List<Object> combined = List.of(first, last);
        List<Object> target = List.of(combined, combined);


        //Act
        var result = target.stream().gather(new DeepRecursiveGatherer<>(l -> {
            if (l instanceof List list) {
                return list;
            }
            return List.of();
        })).toList();

        //Assert
        Assertions.assertEquals(25, result.size()); //(10*2)+2+2+1
    }
}
