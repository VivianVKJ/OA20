package Robinhood;

public class reverseNumber {
    public static void main(String[] args) {
        int n = 214365;
        System.out.println(reverseNumber(n));
    }

    public static int reverseNumber(int n) {
        int index = 0;
        String num = Integer.toString(n);
        int len = num.length();
        StringBuilder s = new StringBuilder();
        while (index + 1 < len) {
            s.append(num.charAt(index + 1));
            s.append(num.charAt(index));
            index += 2;
        }
        if (s.length() != len) s.append(num.charAt(len - 1));
        return Integer.valueOf(s.toString());
    }
}
