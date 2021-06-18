package lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NeedlesHuntingTest {

    @Test
    void hasNeedles() {
        Assertions.assertTrue(NeedlesHunting.hasNeedles(new int[] {2,3,3,1,4,4,1}));
    }
}