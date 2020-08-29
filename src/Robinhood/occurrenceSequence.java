package Robinhood;

import java.util.Arrays;

public class occurrenceSequence {
    public static void main(String[] args) {
        String sequence = "ababcbabc";
        String[] words = {"ab", "babc", "bca"};
        System.out.println(Arrays.toString(maxKOccurrences(sequence, words)));
    }

    public static int[] maxKOccurrences(String sequence, String[] words) {
        int n = words.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int j = 0;
            String word = words[i];
            while (j + word.length() < sequence.length()) {
                int temp = 0;
                while (j + word.length() <= sequence.length() && sequence.substring(j, j + word.length()).compareTo(word) == 0) {
                    temp += 1;
                    j += word.length();
                }
                ans[i] = Math.max(ans[i], temp);
                j++;
            }
        }
        return ans;
    }
}
