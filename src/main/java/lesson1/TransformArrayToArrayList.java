package lesson1;

import java.util.ArrayList;

public class TransformArrayToArrayList {
    /**
     * Метод преобразующий массив в ArrayList
     *
     * @param array - массив который нужно преобразовать
     * @param <T>   - обобщенный тип элементов массива
     * @return - объект типа ArrayList полученный после преобразования
     */
    public static <T> ArrayList<T> transformArrayToArrayList(T[] array) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T element : array) {
            arrayList.add(element);
        }
        return arrayList;
    }

    /**
     * Метод распечатывающий содерщимое массива в консоль в строчку
     *
     * @param array - передаваемый массив для распечатки
     * @param <T>   - тип элементов передаваемого массива (обобщение)
     */
    public static <T> void printArray(ArrayList<T> array) {
        System.out.println("Преобразуем массив в ArrayList:");
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
