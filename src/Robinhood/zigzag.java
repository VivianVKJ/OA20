package Robinhood;

import java.util.Arrays;

public class zigzag {
    public static void main(String[] args) {
        int[] numbers = {10000, 10000, 10000};
        System.out.println(Arrays.toString(zigzag(numbers)));
    }

    public static int[] zigzag(int[] numbers) {
        int n = numbers.length;
        int[] ans = new int[n - 2];
        for (int i = 1; i < n - 1; i++) {
            if ((numbers[i] - numbers[i - 1]) * (numbers[i] - numbers[i + 1]) > 0)
                ans[i - 1] = 1;
        }
        return ans;
    }
}
