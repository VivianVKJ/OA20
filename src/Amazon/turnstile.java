package Amazon;

import java.util.LinkedList;
import java.util.Queue;

public class turnstile {
    public static void main(String[] args) {
        int numCustomers = 5;
        int[] arrTime = new int[]{0, 1, 1, 4, 4};
        int[] direction = new int[]{1, 0, 1, 0, 0};
        for (int i : turnstile(numCustomers, arrTime, direction)) {
            System.out.println(i);
        }
    }

    public static int[] turnstile(int numCustomers, int[] arrTime, int[] direction) {
        int[] ans = new int[numCustomers];
        Queue<Integer> exit = new LinkedList<>();
        Queue<Integer> enter = new LinkedList<>();
        for (int i = 0; i < numCustomers; i++) {
            if (direction[i] == 0) enter.offer(i); //index
            else exit.offer(i);
        }
        int time = 0, dir = 1; //exit = 1;
        while (!exit.isEmpty() && !enter.isEmpty()) {
            //conflict
            if (arrTime[enter.peek()] <= time && arrTime[exit.peek()] <= time) {
                if (dir == 1) ans[exit.poll()] = time;
                else {
                    ans[enter.poll()] = time;
                    dir = 0;
                }
            }
            //either side
            else {
                dir = arrTime[exit.peek()] <= arrTime[enter.peek()] ? 1 : 0;
                if (dir == 1) {
                    time = Math.max(time, arrTime[exit.peek()]);
                    ans[exit.poll()] = time;
                } else {
                    time = Math.max(time, arrTime[enter.peek()]);
                    ans[enter.poll()] = time;
                }
            }
            time += 1;
        }
        while (!exit.isEmpty()) {
            time = Math.max(time, arrTime[exit.peek()]);
            ans[exit.poll()] = time++;
        }
        while (!enter.isEmpty()) {
            time = Math.max(time, arrTime[enter.peek()]);
            ans[enter.poll()] = time++;
        }
        return ans;
    }
}
