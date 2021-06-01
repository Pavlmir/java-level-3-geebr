package lesson1;

import java.util.Arrays;

/**
 * Класс демонстрирующий работу функции замены элементов в массиве с использованием обобщения
 */
public class SwapArrayElements {

    /**
     * Метод меняющий местами два задаваемых элемента массива с соответствующими индексами
     *
     * @param array              - массив в котором происходит замена
     * @param firstElementIndex  - индекс первого элемента для обмена
     * @param secondElementIndex - индекс второго элемента для обмена
     * @param <T>                - тип элементов передаваемого массива
     */
    public static <T> void swapElements(T[] array, int firstElementIndex, int secondElementIndex) {
        T buffer = array[firstElementIndex];
        array[firstElementIndex] = array[secondElementIndex];
        array[secondElementIndex] = buffer;
    }

    /**
     * Метод распечатывающий содерщимое массива в консоль в строчку
     *
     * @param array - передаваемый массив для распечатки
     * @param <T>   - тип элементов передаваемого массива (обобщение)
     */
    public static <T> void printArray(T[] array) {
        System.out.println(Arrays.toString(array));
    }
}
