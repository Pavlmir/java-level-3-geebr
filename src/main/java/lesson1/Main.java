package lesson1;


import java.util.ArrayList;

public class Main {
    /**
     * Запуск тестового примера
     *
     * @param args
     */
    public static void main(String[] args) {
        //Тестируем массив с целочисленными
        Integer[] intArray = {7, 8, 10, 55, 1};
        System.out.println("Меняем местами два задаваемых элемента массива:");
        SwapArrayElements.printArray(intArray);
        SwapArrayElements.swapElements(intArray, 3, 4);
        SwapArrayElements.printArray(intArray);
        ArrayList<Integer> intArrayList = TransformArrayToArrayList.transformArrayToArrayList(intArray);
        TransformArrayToArrayList.printArray(intArrayList);
        System.out.println();

        //Тестируем массив с десятичными числами
        Double[] doubleArray = {7.0, 9.0, 1.0, 4.0, 3.0};
        System.out.println("Меняем местами два задаваемых элемента массива:");
        SwapArrayElements.printArray(doubleArray);
        SwapArrayElements.swapElements(doubleArray, 2, 3);
        SwapArrayElements.printArray(doubleArray);
        ArrayList<Double> doubleArrayList = TransformArrayToArrayList.transformArrayToArrayList(doubleArray);
        TransformArrayToArrayList.printArray(doubleArrayList);
        System.out.println();

        //Тестируем массив со строками
        String[] stringArray = {"Кекс", "Торт", "Эклер", "Круассан", "Крендель"};
        System.out.println("Меняем местами два задаваемых элемента массива:");
        SwapArrayElements.printArray(stringArray);
        SwapArrayElements.swapElements(stringArray, 0, 1);
        SwapArrayElements.printArray(stringArray);
        ArrayList<String> stringArrayList = TransformArrayToArrayList.transformArrayToArrayList(stringArray);
        TransformArrayToArrayList.printArray(stringArrayList);
        System.out.println();
    }
}
