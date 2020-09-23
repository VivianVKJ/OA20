package Amazon;

import java.util.HashSet;
import java.util.Set;

public class uniquePairs {
    public static void main(String[] args) {
        int[] nums = {0};
        int target = 0;
        System.out.println(uniquePairs(nums, target));
    }

    public static int uniquePairs(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        int count = 0;
        for (int num : nums) {
            if (set.contains(target - num) && !seen.contains(num)) {
                count++;
                seen.add(target - num);
                seen.add(num);
            } else if (!set.contains(num)) {
                set.add(num);
            }
        }
        return count;
    }

}
