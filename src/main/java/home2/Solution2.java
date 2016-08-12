package home2;

import java.io.*;

/**
 * Created by Airo on 10.08.2016.
 */
public class Solution2 {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\Airo\\IdeaProjects\\SbtHomeWork\\src\\main\\java\\home2\\input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line1 = reader.readLine().trim();
            int[] input1 = stringToIntArray(line1);

            String line2 = reader.readLine().trim();
            int[] input2 = stringToIntArray(line2);

            reader.close();

            int sum = 0;
            int count = 0;
            for (int i = 0; i < input1[0]; i++) {
                if (sum + input2[i] <= input1[1]) {
                    sum = sum + input2[i];
                    count++;
                }
            }

            System.out.println(count + " " + sum);

            FileWriter writer = new FileWriter("C:\\Users\\Airo\\IdeaProjects\\SbtHomeWork\\src\\main\\java\\home2\\output.txt");
            writer.write(count + " " + sum);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
