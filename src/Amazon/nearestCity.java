package Amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class nearestCity {
    public static void main(String[] args) {
        int numOfCities = 3;
        String[] cities = new String[]{"c1", "c2", "c3"};
        int[] xCoordinates = new int[]{3, 2, 1};
        int[] yCoordinates = new int[]{3, 2, 3};
        int numOfQueries = 3;
        String[] queries = new String[]{"c1", "c2", "c3"};
        System.out.println(Arrays.asList(nearestCity(numOfCities, cities, xCoordinates, yCoordinates, numOfQueries, queries)).toString());
    }

    public static String[] nearestCity(int numOfCities, String[] cities, int[] xCoordinates, int[] yCoordinates, int numOfQueries, String[] queries) {
        String[] res = new String[queries.length];
        Map<Integer, TreeMap<Integer, String>> xMap = new HashMap<>(); //shared x: yCoor -> city name
        Map<Integer, TreeMap<Integer, String>> yMap = new HashMap<>();
        Map<String, int[]> nodeMap = new HashMap<>();
        for (int i = 0; i < cities.length; i++) {
            nodeMap.put(cities[i], new int[]{xCoordinates[i], yCoordinates[i]}); //city name -> [x, y]
            xMap.putIfAbsent(xCoordinates[i], new TreeMap<>());
            yMap.putIfAbsent(yCoordinates[i], new TreeMap<>());
            xMap.get(xCoordinates[i]).put(yCoordinates[i], cities[i]);
            yMap.get(yCoordinates[i]).put(xCoordinates[i], cities[i]);
        }
        for (int i = 0; i < cities.length; i++) {
            int x = nodeMap.get(cities[i])[0], y = nodeMap.get(cities[i])[1];
            TreeMap<Integer, String> ycoorMap = xMap.getOrDefault(x, new TreeMap<>());
            TreeMap<Integer, String> xcoorMap = yMap.getOrDefault(y, new TreeMap<>());
            Integer ylower = ycoorMap.lowerKey(y), yhigher = ycoorMap.higherKey(y);
            Integer xlower = xcoorMap.lowerKey(x), xhigher = xcoorMap.higherKey(x);
            int dist = Integer.MAX_VALUE;
            if (ylower != null && Math.abs(ylower - y) <= dist) {
                dist = Math.abs(ylower - y);
                res[i] = res[i] == null ? ycoorMap.get(ylower) : res[i].compareTo(ycoorMap.get(ylower)) > 0 ? ycoorMap.get(ylower) : res[i];
            }
            if (yhigher != null && Math.abs(yhigher - y) <= dist) {
                dist = Math.abs(yhigher - y);
                res[i] = res[i] == null ? ycoorMap.get(yhigher) : res[i].compareTo(ycoorMap.get(yhigher)) > 0 ? ycoorMap.get(yhigher) : res[i];
            }
            if (xlower != null && Math.abs(xlower - y) <= dist) {
                dist = Math.abs(xlower - x);
                res[i] = res[i] == null ? xcoorMap.get(xlower) : res[i].compareTo(xcoorMap.get(xlower)) > 0 ? ycoorMap.get(xlower) : res[i];
            }
            if (xhigher != null && Math.abs(xhigher - y) <= dist) {
                dist = Math.abs(xhigher - y);
                res[i] = res[i] == null ? xcoorMap.get(xhigher) : res[i].compareTo(xcoorMap.get(xhigher)) > 0 ? xcoorMap.get(xhigher) : res[i];
            }
            if (res[i] == null) res[i] = "None";
        }
        return res;
    }

}
