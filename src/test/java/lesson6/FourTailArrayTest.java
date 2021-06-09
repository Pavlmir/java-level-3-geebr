package lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FourTailArrayTest {

    @ParameterizedTest
    @MethodSource("fillSliceParams")
    void sliceTest(int[] in, int [] out) {
        Assertions.assertArrayEquals(out, FourTailArray.getFourTailArray(in));
    }

    static Stream<Arguments> fillSliceParams() {
        List<Arguments> list = new ArrayList<>();
        list.add(Arguments.arguments(new int[] {1,5,4,8,2,1,4,8,6,4,5,77,1}, new int[] {5,77,1}));
        list.add(Arguments.arguments(new int[] {4}, new int[] {}));
        list.add(Arguments.arguments(new int[] {1,5,4,8,2,1,4,8,6,5,77,1}, new int[] {8,6,5,77,1}));
        return list.stream();
    }

    @Test
    void getFourTailArray() {
        Assertions.assertThrows(RuntimeException.class, () -> FourTailArray.getFourTailArray(new int[] {1,2,54,5}));
    }
}