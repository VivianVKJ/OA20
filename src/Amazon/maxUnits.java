package Amazon;

import java.util.ArrayList;
import java.util.TreeMap;

public class maxUnits {
    public static void main(String[] args) {
        int num = 3;
        ArrayList<Integer> boxes = new ArrayList<>();
        boxes.add(2);
        boxes.add(5);
        boxes.add(3);
        int unitSize = 3;
        ArrayList<Integer> unitPerBox = new ArrayList<>();
        unitPerBox.add(3);
        unitPerBox.add(2);
        unitPerBox.add(1);
        int truckSize = 50;
        System.out.println(getMaxUnit(num, boxes, unitSize, unitPerBox, truckSize));
    }


    public static long getMaxUnit(int num, ArrayList<Integer> boxes, int unitSize, ArrayList<Integer> unitsPerBox, long truckSize) {
        long ans = 0l;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < num; i++)
            treeMap.put(unitsPerBox.get(i), boxes.get(i));
        while (!treeMap.isEmpty() && truckSize > 0) {
            long count = truckSize < treeMap.get(treeMap.lastKey()) ? truckSize : treeMap.get(treeMap.lastKey());
            ans += count * treeMap.lastKey();
            treeMap.pollLastEntry();
            truckSize -= count;
        }
        return ans;
    }
}
