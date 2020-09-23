package Amazon;

import java.util.ArrayDeque;

public class diskSpaceAnalysis {
    public static void main(String[] args) {
        int numComputer = 7;
        int[] hardDiskSpace = {2, 4, 3, 7, 8, 6, 5};
        int segmentLength = 4;
        System.out.println(diskSpaceAnalysis(numComputer, hardDiskSpace, segmentLength));
    }

    public static int diskSpaceAnalysis(int numComputer, int[] hardDiskSpace, int segmentLength) {
        int max = Integer.MIN_VALUE;
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        for (int i = 0; i < numComputer; i++) {
            while (!deq.isEmpty() && deq.peekFirst() < i - segmentLength + 1)
                deq.pollFirst();
            while (!deq.isEmpty() && hardDiskSpace[deq.peekLast()] > hardDiskSpace[i])
                deq.pollLast();
            deq.offer(i);
            if (i >= segmentLength - 1)
                max = Math.max(max, hardDiskSpace[deq.peek()]);
        }
        return max;
    }
}
