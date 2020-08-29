package Robinhood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class chessBoard {
    public static void main(String[] args) {
        int[][] numbers = {{1, 4, 3, 2}, {8, 4, 7, 1}, {1, 5, 2, 1}};
        int[][] queries = {{0, 1, 3}, {1, 0, 2}};
        numbers = sortChessSubquares(numbers, queries);
        for (int i = 0; i < numbers.length; i++)
            System.out.println(Arrays.toString(numbers[i]));
    }

    public static int[][] sortChessSubquares(int[][] nums, int[][] queries) {
        List<List<Integer>> board = new ArrayList<>();
        board.add(new ArrayList<>());
        board.add(new ArrayList<>());
        for (int[] query : queries) {
            int x = query[0], y = query[1], w = query[2];
            for (int i = x; i < x + w; i++) {
                for (int j = y; j < y + w; j++) {
                    board.get((i + j) % 2).add(nums[i][j]);
                }
            }
            Collections.sort(board.get(0));
            Collections.sort(board.get(1));
            for (int i = x; i < x + w; i++) {
                for (int j = y; j < y + w; j++) {
                    nums[i][j] = board.get((i + j) % 2).get(0);
                    board.get((i + j) % 2).remove(0);
                }
            }
        }
        return nums;
    }
}
