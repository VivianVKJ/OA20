package Amazon;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class findItem {
    public static void main(String[] args) {
        int numOfItems = 3;
        Map<String, Integer[]> map = new HashMap<>();
        map.put("item1", new Integer[]{10, 15}); //name relevance, price
        map.put("item2", new Integer[]{3, 4});
        map.put("item3", new Integer[]{17, 8});
        map.put("item4", new Integer[]{13, 15}); //name relevance, price
        map.put("item5", new Integer[]{5, 4});
        map.put("item6", new Integer[]{7, 8});
        int sortParameter = 1, sortOrder = 0, itemsPerPage = 2, pageNumber = 1;
        System.out.println(findItem(numOfItems, map, sortParameter, sortOrder, itemsPerPage, pageNumber).toString());
        //sortParameter: {0,1,2} pageNumber starts at 0, sortOrder=0: ascending
    }

    public static List<String> findItem(int numOfItems, Map<String, Integer[]> map, int sortParameter, int sortOrder, int itemsPerPage, int pageNumber) {
        List<String> sortedItem = new ArrayList<>(map.keySet());
        if (sortParameter == 0) {
            Collections.sort(sortedItem);
        } else Collections.sort(sortedItem, Comparator.comparingInt(x -> map.get(x)[sortParameter - 1]));
        if (sortOrder == 1) Collections.reverse(sortedItem);
        return sortedItem.subList(itemsPerPage * pageNumber, Math.min(itemsPerPage * (pageNumber + 1), sortedItem.size()));
    }
}
