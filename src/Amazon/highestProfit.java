package Amazon;

public class highestProfit {
    public static void main(String[] args) {
        int numSuppliers = 5, order = 30;
        int[] inventory = new int[]{4, 8, 2, 10, 6};
        System.out.println(highestProfit(numSuppliers, order, inventory));
    }

    public static long highestProfit(int numSuppliers, int order, int[] inventory) {
        int[] supply = new int[100001];
        long profit = 0l;
        int maxIndex = 0;
        for (int inv : inventory) {
            supply[inv] += 1;
            maxIndex = Math.max(maxIndex, inv);
        }
        while (order > 0) {
            int count = Math.min(order, supply[maxIndex]);
            profit += count * maxIndex;
            supply[maxIndex - 1] += supply[maxIndex];
            maxIndex -= 1;
            order -= count;
        }
        return profit;
    }
}
