package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class closestPair {
    static class PairInt {
        int first;
        int second;

        PairInt(int f, int s) {
            this.first = f;
            this.second = s;
        }
    }

    public static void main(String[] args) {
        List<PairInt> points = Arrays.asList(
                new PairInt(1, 1), new PairInt(1, 9), new PairInt(2, 5),
                new PairInt(3, 1), new PairInt(4, 4), new PairInt(5, 8), new PairInt(6, 2));
        System.out.println(closetPair(points));
    }

    public static double closetPair(List<PairInt> points) {
        Collections.sort(points, (p1, p2) -> (p1.first > p2.first) ? 1 : (p1.second == p2.second) ? 0 : -1);
        return divide(0, points.size() - 1, points);
    }

    public static double divide(int l, int r, List<PairInt> points) {
        double min = 1e20;
        if (l == r) return min;
        if (l + 1 == r) return distance(points.get(l), points.get(r));

        int m = (l + r) / 2;
        double lm = divide(l, m, points);
        double rm = divide(l, m, points);
        min = lm <= rm ? lm : rm;

        List<Integer> validPoints = new ArrayList<>();
        for (int i = l; i <= r; i++)
            if (Math.abs(points.get(m).first - points.get(i).first) <= min) validPoints.add(i);

        for (int i = 0; i < validPoints.size() - 1; i++) {
            for (int j = i + 1; j < validPoints.size(); j++) {
                if (Math.abs(points.get(validPoints.get(i)).second - points.get(validPoints.get(j)).second) > min)
                    continue;
                double temp = distance(points.get(validPoints.get(i)), points.get(validPoints.get(j)));
                min = (temp < min) ? temp : min;
            }
        }
        return min;
    }

    public static double distance(PairInt p1, PairInt p2) {
        return Math.sqrt((p2.second - p1.second) * (p2.second - p1.second) + (p2.first - p1.first) * (p2.first - p1.first));
    }

}
