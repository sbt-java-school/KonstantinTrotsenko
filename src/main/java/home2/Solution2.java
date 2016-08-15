package home2;

import java.io.*;

/**
 * Класс служит для вычисления колличества грузов помещенных на грузовик заданной грузоподъемности
 *
 * @version 2.0
 * @autor Trotsenko Konstantin
 */
public class Solution2 {
    /**
     * Точка входа в класс
     *
     * @param args Массив строковых аргументов
     * @throws Exception Исключений не выдает
     */
    public static void main(String[] args) {
        try {
            /**
             * Ститываем файл построчно
             */
            File file = new File("C:\\Users\\Airo\\IdeaProjects\\SbtHomeWork\\src\\main\\java\\home2\\input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            /**
             * Счтиваем первую строку, где указано первым параметром колличество грузов, вторым максимальный вес
             */
            String line1 = reader.readLine().trim();
            int[] input1 = stringToIntArray(line1);
            /**
             * Счтиваем вторую строку, грузы
             */
            String line2 = reader.readLine().trim();
            int[] input2 = stringToIntArray(line2);

            reader.close();
            /**
             * Выходные параметры, вес грузов
             */
            int totalWeight = 0;
            /**
             * Выходные параметры, колличество грузов
             */
            int count = 0;
            for (int i = 0; i < input1[0]; i++) {
                if (totalWeight + input2[i] <= input1[1]) {
                    totalWeight = totalWeight + input2[i];
                    count++;
                }
            }

            System.out.println(count + " " + totalWeight);
            /**
             * Записываем в файл колличество грузво и их вес
             */
            FileWriter writer = new FileWriter("C:\\Users\\Airo\\IdeaProjects\\SbtHomeWork\\src\\main\\java\\home2\\output.txt");
            writer.write(count + " " + totalWeight);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод принимает строку типа String в массив типа int
     *
     * @param s строка считываемая из файла
     * @return массив чисел типа int
     */
    private static int[] stringToIntArray(String s) {
        String[] arrayString = s.split(" ");
        int[] arrayInt = new int[arrayString.length];
        for (int i = 0; i < arrayInt.length; i++) {
            try {
                arrayInt[i] = Integer.parseInt(arrayString[i]);
            } catch (NumberFormatException nfe) {
            }
        }
        return arrayInt;
    }
}
