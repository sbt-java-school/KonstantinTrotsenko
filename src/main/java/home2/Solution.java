package home2;

/**
 * Класс служит для вычисления колличества грузов помещенных на грузовик заданной грузоподъемности
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Solution {
    /**
     * Точка входа в класс
     *
     * @param args Массив строковых аргументов
     * @throws Exception Исключений не выдает
     */
    public static void main(String[] args) {
        /**
         * Входные параметры,1ое колличество грузов, 2ое грузоподъемность
         */
        int[] input1 = new int[]{5, 10};
        /**
         * Входные параметры,вес груза
         */
        int[] input2 = new int[]{5, 7, 3, 9, 1};
        /**
         * Выходные параметры, вес грузов
         */
        int totalWeight = 0;
        /**
         * Выходные параметры, колличество грузов
         */
        int count = 0;
        for (int i = 0; i < input1[0]; i++) {
            while (totalWeight + input2[i] < input1[1]) {
                totalWeight = totalWeight + input2[i];
                count++;
            }
        }
        System.out.println(count + " " + totalWeight);
    }
}
