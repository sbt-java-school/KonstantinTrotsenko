package home3;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс служит для тестирования колекция List
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class TestListMethods {
    /**
     * Точка входа в класс
     *
     * @param args Массив строковых аргументов
     */
    public static void main(String[] args) {
        /**
         * Лист для тестирования методов
         */
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            arrayList.add(i);
        }
        System.out.println("Исходный ArrayList "+arrayList.toString());

        Collections.shuffle(arrayList);
        System.out.println("Метод shuffle "+ arrayList.toString());

        Collections.reverse(arrayList);
        System.out.println("Метод reverse "+ arrayList.toString());

        Collections.sort((ArrayList)arrayList);
        System.out.println("Метод sort "+ arrayList.toString());

        boolean contain = arrayList.contains(2);
        System.out.println("Метод contains " + contain);

        int index = Collections.binarySearch((ArrayList)arrayList, 10);
        System.out.println("Метод binarySearch "+index);

        Collections.replaceAll(arrayList, 2, 20);
        System.out.println("Метод replaceAll "+arrayList.toString());
    }
}
