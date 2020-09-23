package Robinhood;

import java.util.HashSet;
import java.util.Set;

public class oldButton {
    public static void main(String[] args) {
        int[] digits = {3, 5, 6, 7, 9};
        String[] words = {"flowers", "gdef"};
        workingButtons(digits, words);
    }

    public static boolean[] workingButtons(int[] digits, String[] words) {
        boolean[] ans = new boolean[words.length];
        Set<Integer> dict = new HashSet<>();
        for (int dig : digits) {
            if (dig > 1) {
                dict.add((dig - 2) * 3 + 0);
                dict.add((dig - 2) * 3 + 1);
                dict.add((dig - 2) * 3 + 2);
            }
            if (dig == 9) dict.add(26);
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            ans[i] = true;
            for (char c : word.toCharArray()) {
                if (!dict.contains(c - 'a')) ans[i] = false;
            }
        }
        return ans;
    }
}
