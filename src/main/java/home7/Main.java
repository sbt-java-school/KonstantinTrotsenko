package home7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Class to test different GC
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class Main {

    /**
     * The entry point into the class
     *
     * @param args The array of string arguments
     */
    public static void main(String[] args) {
        instructions();
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String command = null;
            try {
                command = reader.readLine().trim();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (command) {
                case "A":
                    createArray();
                    break;
                case "M":
                    createMap();
                    break;
                case "AM":
                    createArray();
                    createMap();
                    break;
                case "Q":
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                default:
                    System.out.println("Invalid input!");
                    instructions();
                    break;
            }
        }
    }

    /**
     * Method shows instructions
     */
    public static void instructions() {
        System.out.println("Input 'A' - to create array, 'M' - to create map, " +
                "'AM' - to create array and map, 'Q' - to exit.");
    }

    /**
     * Method creates Map with Test objects
     */
    private static void createMap() {
        Map<Integer, Test> map = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            map.put(i, new Test(i, "value " + i));
        }
        System.out.println("Map created!");
    }

    /**
     * Method creates array [100000][1000] size
     */
    private static void createArray() {
        int[][] array = new int[100000][1000];
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 1000; j++) {
                array[i][j] = random.nextInt(101);
            }
        }
        System.out.println("Array created!");
    }

    /**
     * Class for filling map
     */
    public static class Test {
        int a;
        String b;

        public Test(int a, String b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "a=" + a +
                    ", b='" + b + '\'' +
                    '}';
        }
    }
}
