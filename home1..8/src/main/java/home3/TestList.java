package home3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс служит для тестирования колекция List
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class TestList {
    /**
     * Точка входа в класс
     *
     * @param args Массив строковых аргументов
     */
    public static void main(String[] args) {
        /**
         * Два типа коллекции для тестирования
         */
        List<Integer> arrayList = new ArrayList<Integer>();
        List<Integer> linkedList = new LinkedList<>();
        /**
         * Параметр для измерения времени
         */
        long time = 0;
        /**
         * Время добавления в ArrayList
         */
        time = fillList(arrayList);
        System.out.println("Время заполнения в ArrayList: " + time + " нано секунд");
        /**
         * Время удаления каждого второго числа в ArrayList
         */
        time = deleteEveryEven(arrayList);
        System.out.println("Время удаления в ArrayList: " + time + " нано секунд");
        /**
         * Время втавки в середину ArrayList
         */
        time = addToMiddle(arrayList);
        System.out.println("Время вставкив в середину ArrayList: " + time + " нано секунд");

        System.out.println();

        /**
         * Время добавления в LinkedList
         */
        time = fillList(linkedList);
        System.out.println("Время заполнения в LinkedList: " + time + " нано секунд");
        /**
         * Время удаления каждого второго числа в LinkedList
         */
        time = deleteEveryEven(linkedList);
        System.out.println("Время удаления в LinkedList: " + time + " нано секунд");
        /**
         * Время втавки в середину LinkedList
         */
        time = addToMiddle(linkedList);
        System.out.println("Время вставкив в середину LinkedList: " + time + " нано секунд");
    }

    /**
     * Метод считает время заполнеия коллекции типа List
     *
     * @param list лист для заполнения
     * @return время типа long в нано  секундах
     */
    public static long fillList(List list) {
        int maxValue = 10000;
        //Random rand = new Random();
        long start = System.nanoTime();
        for (int i = 0; i <= maxValue; i++) {
            //list.add(rand.nextInt(100));
            list.add(i);
        }
        long end = System.nanoTime();
        long traceTime = end - start;
        return traceTime;
    }

    /**
     * Метод считает время удаления каждого второго элемента из коллекции типа List
     *
     * @param list лист для удаления
     * @return время типа long в нано  секундах
     */
    public static long deleteEveryEven(List list) {
        long start = System.nanoTime();
        for (int i = 0; i < list.size(); i += 2) {
            list.remove(i);
        }
        long end = System.nanoTime();
        long traceTime = end - start;
        return traceTime;
    }

    /**
     * Метод вставляет 200 элементов в середину коллекции типа List
     *
     * @param list лист для вставки
     * @return время типа long в нано  секундах
     */
    public static long addToMiddle(List list) {
        long start = System.nanoTime();
        int middleList = list.size() / 2;
        for (int i = middleList; i < middleList + 200; i++) {
            list.add(middleList + i);
        }
        long end = System.nanoTime();
        long traceTime = end - start;
        return traceTime;
    }
}
