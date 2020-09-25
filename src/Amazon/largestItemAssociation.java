package Amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

public class largestItemAssociation {
    static class PairString {
        String first;
        String second;

        PairString(String f, String s) {
            this.first = f;
            this.second = s;
        }
    }

    public static void main(String[] args) {
        List<PairString> items = new ArrayList<>();
        items.add(new PairString("item1", "item2"));
        items.add(new PairString("item3", "item4"));
        items.add(new PairString("item4", "item5"));
        System.out.println(largestItemAssociation(items).toString());

    }

    public static List<String> largestItemAssociation(List<PairString> itemAssociation) {
        // write your code here
        Map<String, List<String>> assocMap = new HashMap<>();
        //Map with all items and child association of every item 1->2, 2-> , 3->4, 4->5 ,5->
        for (PairString p : itemAssociation) {
            if (!assocMap.containsKey(p.first))
                assocMap.put(p.first, new ArrayList<>());
            assocMap.get(p.first).add(p.second);
            if (!assocMap.containsKey(p.second))
                assocMap.put(p.second, new ArrayList<>());
        }

        List<List<String>> groups = new ArrayList<>();
        int maxSize = Integer.MIN_VALUE;
        for (String key : assocMap.keySet()) {
            Queue<String> q = new LinkedList<>();
            TreeSet<String> treeSet = new TreeSet<>();
            q.offer(key);
            while (!q.isEmpty()) {
                String curr = q.poll();
                treeSet.add(curr);
                List<String> next = assocMap.get(curr);
                for (String n : next) q.offer(n);
            }
            maxSize = Math.max(maxSize, treeSet.size());
            if (groups.size() != 0 && groups.get(0).size() != maxSize)
                groups.clear();
            groups.add(new ArrayList<>(treeSet));
        }

        Collections.sort(groups, (o1, o2) -> {
            int result = 0;
            for (int i = 0; i < o1.size() && result == 0; i++) {
                result = o1.get(i).compareTo(o2.get(i));
            }
            return result;
        });
        return groups.get(0);
    }

}
