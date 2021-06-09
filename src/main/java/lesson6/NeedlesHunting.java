package lesson6;

public class NeedlesHunting {
    public static boolean hasNeedles(int[] in) {
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 1 || in[i] == 4)
                return true;
        }
        throw new RuntimeException("В массиве есть 1 или 4.");
    }
}