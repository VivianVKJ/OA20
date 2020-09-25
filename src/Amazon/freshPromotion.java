package Amazon;

import java.util.Arrays;
import java.util.List;

public class freshPromotion {
    public static void main(String[] args) {
        List<List<String>> codeList = Arrays.asList(Arrays.asList("anything", "anything", "anything", "apple"), Arrays.asList("banana", "anything", "banana"));
        List<String> shoppingCart = Arrays.asList("orange", "apple", "banana", "orange", "apple", "orange", "orange", "banana", "apple", "banana");
        System.out.println(freshPromotion(codeList, shoppingCart));
    }

    public static int freshPromotion(List<List<String>> codeList, List<String> shoppingCart) {
        if (codeList == null || codeList.size() == 0) {
            return 1;
        }
        if (shoppingCart == null || shoppingCart.size() == 0) {
            return 0;
        }
        int i = 0, j = 0;
        while (i < codeList.size() && j + codeList.get(i).size() <= shoppingCart.size()) {
            boolean match = true;
            for (int k = 0; k < codeList.get(i).size(); k++) {
                if (!codeList.get(i).get(k).equals("anything") && !shoppingCart.get(j + k).equals(codeList.get(i).get(k))) {
                    match = false;
                    break;
                }
            }
            if (match) j += codeList.get(i++).size();
            else j++;
        }
        return (i == codeList.size()) ? 1 : 0;
    }

}
