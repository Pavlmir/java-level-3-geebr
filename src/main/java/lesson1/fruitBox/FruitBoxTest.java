package lesson1.fruitBox;

import java.util.ArrayList;
import java.util.Arrays;

public class FruitBoxTest {
    public static void main(String[] args) {
        FruitBox<Apple> appleBox = new FruitBox<>();
        ArrayList<Apple> apples = new ArrayList<>(Arrays.asList(new Apple(1.2), new Apple(1.1)));
        appleBox.addFruitAll(apples);

        FruitBox<Apple> appleBox2 = new FruitBox<>();
        ArrayList<Apple> apples2 = new ArrayList<>(Arrays.asList(new Apple(1.3), new Apple(1.0)));
        appleBox2.addFruitAll(apples2);

        FruitBox<Orange> orangeBox = new FruitBox<>();
        ArrayList<Orange> oranges = new ArrayList<>(Arrays.asList(new Orange(2.3), new Orange(2.3)));
        orangeBox.addFruitAll(oranges);

        System.out.println("Вес коробки-1 с яблоками: " + appleBox.getWeight());
        System.out.println("Вес коробки-2 с яблоками: " + appleBox2.getWeight());
        appleBox.pourFruit(appleBox2); //Пересыпаем яблоки из коробки-1 в коробки-2
        System.out.println("После пересыпания:");
        System.out.println("Вес коробки-1 с яблоками: " + appleBox.getWeight());
        System.out.println("Вес коробки-2 с яблоками: " + appleBox2.getWeight());
        System.out.println("Вес коробки-1 с апельсинами: " + orangeBox.getWeight());

        boolean isBoxesWeightEqual = appleBox.compare(orangeBox);
        System.out.println("Сравниваем по весу коробку яблок с апельсинами: " + isBoxesWeightEqual);
    }
}
