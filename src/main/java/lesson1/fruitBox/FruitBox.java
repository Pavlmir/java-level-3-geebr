package lesson1.fruitBox;

import java.util.ArrayList;

/**
 * Класс Box, в который можно складывать фрукты.
 *
 * @param <T> - Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины.
 */
public class FruitBox<T extends Fruit> {
    private ArrayList<T> storage = new ArrayList<>(); //Для хранения фруктов внутри коробки можно использовать ArrayList;

    /**
     * метод добавления фрукта в коробку.
     *
     * @param fruit - добавяемый фрукт
     */
    public void addFruit(T fruit) {
        storage.add(fruit);
    }

    /**
     * метод добавления массива фруктов в коробку.
     *
     * @param fruits - добавяемый фрукт
     */
    public void addFruitAll(ArrayList<T> fruits) {
        storage.addAll(fruits);
    }

    /**
     * который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
     * (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
     *
     * @return - текущий вес коробки.
     */
    public double getWeight() {
        double weight = 0.0;
        for (T fruit : storage) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    /**
     * позволяет сравнить текущую коробку с той,
     * которую подадут в compare в качестве параметра,
     * (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
     *
     * @param box - коробка для сравнения
     * @return - true – если коробки равны по весу, false – в противном случае
     */
    public boolean compare(FruitBox<?> box) {
        return this.getWeight() == box.getWeight();
    }

    /**
     * Метод высыпающий фрукты из коробки, оставляя коробку пустой
     *
     * @return - массив с фруктами, что были в коробке
     */
    public ArrayList<T> empty() {
        ArrayList<T> fruits = storage;
        storage = new ArrayList<>(); //Опусташаем хранилище фруктов через создание нового объекта, так мы избегаем ненужного копирования
        return fruits;
    }

    /**
     * Метод позволяет
     * пересыпать фрукты из текущей коробки в другую
     * (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами).
     * Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
     *
     * @param box - передаваемая коробка из которой нужно пересыпать фрукты. Ф
     */
    public void pourFruit(FruitBox<T> box) {
        addFruitAll(box.empty());
    }
}