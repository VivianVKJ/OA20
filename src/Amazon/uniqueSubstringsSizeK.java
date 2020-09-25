package Amazon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class uniqueSubstringsSizeK {
    public static void main(String[] args) {
        String s = "awaglknagawunagwkwagl";
        int k = 4;
        System.out.println(Arrays.asList(uniqueSubstringsSizeK(s, k).toString()));
    }

    public static Set<String> uniqueSubstringsSizeK(String s, int k) {
        Set<String> set = new HashSet<>();
        int[] record = new int[26];
        int l = 0, r = 0;
        while (l <= r && r < s.length()) {
            record[s.charAt(r) - 'a']++;
            while (record[s.charAt(r) - 'a'] != 1) {
                record[s.charAt(l) - 'a']--; //left goes right until no duplicate
                l++;
            }
            if (r - l + 1 == k) {
                set.add(s.substring(l, r + 1));
                record[s.charAt(l) - 'a']--;
                l++;
            }
            r++; //right goes right
        }
        return set;
    }
}
