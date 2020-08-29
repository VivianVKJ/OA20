package Robinhood;

import java.util.HashSet;
import java.util.LinkedList;

public class longestSubarrayCheck {
    public static void main(String[] args) {
        int[] a = {1, 1, 5, 1, 2};
        int[] b = {1, 2};
        int[] c = {2, 1};
        System.out.println(longestSubarrayCheck(a, b, c) == true ? "true" : "false");
    }

    public static boolean longestSubarrayCheck(int[] a, int[] b, int[] c) {
        HashSet<Integer> dict = new HashSet<>();
        for (int num : c) dict.add(num);
        LinkedList<Integer> blist = new LinkedList<>();
        for (int num : b) blist.add(num);
        HashSet<LinkedList<Integer>> match = new HashSet<>();
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            LinkedList<Integer> current = new LinkedList<>();
            int r = i;
            while (r < a.length && dict.contains(a[r])) current.add(a[r++]);
            if (current.size() > max) {
                match.clear();
                match.add(current);
                max = current.size();
            } else if (current.size() == max) match.add(current);
        }
        return match.contains(blist);
    }
}
