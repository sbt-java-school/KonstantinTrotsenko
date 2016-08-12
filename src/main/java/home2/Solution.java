package home2;

/**
 * Created by Airo on 09.08.2016.
 */
public class Solution {
    public static void main(String[] args) {
        int[] input1 = new int[]{5, 10};
        int[] input2 = new int[]{5, 7, 3, 9, 1};
        int sum = 0;
        int count = 0;
        for (int i = 0; i < input1[0]; i++) {
            while (sum + input2[i] < input1[1]) {
                sum = sum + input2[i];
                count++;
            }
        }
        System.out.println(count + " " + sum);
    }
}
