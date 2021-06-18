package lesson6;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        FourTailArray f = new FourTailArray();
        int[] a = new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] b = f.getFourTailArray(a);
        System.out.printf("a %s \nb %s\n", Arrays.toString(a), Arrays.toString(b));

        NeedlesHunting n = new NeedlesHunting();
        int[] c = new int[]{2, 2, 3, 7};
        boolean d = n.hasNeedles(c);
        System.out.printf("c %s \nd %s", Arrays.toString(c), d);
    }
}
