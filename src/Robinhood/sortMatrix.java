package Robinhood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class sortMatrix {
    public static void main(String[] args) {
        int[][] m = {{1, 4, -2}, {-2, 3, 4}, {3, 1, 3}};
        int[][] ans = sortMatrixByOccurrences(m);
        for (int i = 0; i < m.length; i++)
            System.out.println(Arrays.toString(ans[i]));
    }

    public static int[][] sortMatrixByOccurrences(int[][] m) {
        int n = m.length, index = 0;
        int[][] ans = new int[n][n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                map.put(m[i][j], map.getOrDefault(m[i][j], 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>(map.size(), (x, y) -> (map.get(x) == map.get(y) ? x - y : map.get(x) - map.get(y)));
        for (int num : map.keySet()) pq.add(num);
        List<Integer> sorted = new ArrayList<>();
        while (!pq.isEmpty()) {
            int num = pq.poll(), time = map.get(num);
            while (time > 0) {
                sorted.add(num);
                time--;
            }
        }
        //right half
        for (int t = n - 1; t >= 0; t--) {
            int i = n - 1, j = t;
            while (i >= 0 && j < n) {
                ans[i--][j++] = sorted.get(index++);
            }
        }
        //left half
        for (int t = n - 2; t >= 0; t--) {
            int i = t, j = 0;
            while (i >= 0 && j < n) {
                ans[i--][j++] = sorted.get(index++);
            }
        }
        return ans;
    }


}
