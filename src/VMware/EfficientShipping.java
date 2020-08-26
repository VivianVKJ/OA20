package VMware;

import java.util.Arrays;
import java.util.HashMap;

public class EfficientShipping {
    public static void main(String[] args) {
        int track = 3;
        int[] boxes = {1, 2, 3};
        int[] unitPerBox = {3, 2, 1};
        System.out.println(efficientShipping(track, boxes, unitPerBox));
    }

    //Greedy + Sort
    public static int efficientShipping(int trackSize, int[] boxes, int[] unit) {
        int max = 0, count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < boxes.length; i++) {
            map.put(unit[i], boxes[i]);
        }
        Arrays.sort(unit);
        for(int i = boxes.length - 1; i >=0 && count < trackSize; i--){
            int num = map.get(unit[i]);
            num = count + num <= trackSize ? num : trackSize - count;
            max += num * unit[i];
            count += num;
        }
        return max;
    }

}
