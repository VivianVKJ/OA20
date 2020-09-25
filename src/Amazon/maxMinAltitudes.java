package Amazon;

public class maxMinAltitudes {
    public static void main(String[] args) {
        int[][] num1 = new int[][]{{5, 1}, {4, 5}};
        int[][] num2 = new int[][]{{5, 1, 7}, {4, 8, 5}};
        int[][] num3 = new int[][]{{1, 9, 9}, {9, 9, 9}, {9, 9, 9}};
        int[][] num4 = new int[][]{{10, 7, 3}, {12, 11, 9}, {1, 2, 8}};
        int[][] num5 = new int[][]{{20, 20, 3}, {20, 3, 20}, {3, 20, 20}};
        int[][] num6 = new int[][]{{1, 2, 3}, {4, 5, 1}};
        int[][] num7 = new int[][]{{5, 1, 2, 8, 2, 3, 9}};
        int[][] num8 = new int[][]{{5, 1}};
        int[][] num9 = new int[][]{{5}, {1}};
        int[][] num10 = new int[][]{{1}};
        int[][] num11 = new int[][]{};
        int[][] num12 = new int[][]{{2, 0, 2, 3, 1}, {0, 2, 2, 3, 3}, {2, 3, 0, 2, 3}, {1, 1, 2, 3, 1}, {2, 2, 0, 0, 1}};


        System.out.println(0 == maxMinAltitudes(num12));
    }

    public static int maxMinAltitudes(int[][] grid) {
        int m = grid.length;
        if (m == 0) return -1;
        int n = grid[0].length;
        for (int i = 2; i < n; i++)
            grid[0][i] = Math.min(grid[0][i - 1], grid[0][i]);
        for (int i = 2; i < m; i++)
            grid[i][0] = Math.min(grid[i - 1][0], grid[i][0]);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (i == m - 1 && j == n - 1)
                    grid[i][j] = Math.max(grid[i - 1][j], grid[i][j - 1]);
                else {
                    int left = Math.min(grid[i][j], grid[i][j - 1]);
                    int up = Math.min(grid[i][j], grid[i - 1][j]);
                    grid[i][j] = Math.max(left, up);
                }
            }
        }
        return grid[m - 1][n - 1];
    }
}
