package Robinhood;

public class vowelsMatch {
    public static void main(String[] args) {
        String pattern = "010";
        String s = "amazing";
        System.out.println(binaryPatternMatching(pattern, s));
    }

    public static int binaryPatternMatching(String pattern, String s) {
        int ans = 0, len = pattern.length();
        char[] cArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (cArray[i] == 'a' || cArray[i] == 'e' || cArray[i] == 'i' || cArray[i] == 'o'
                    || cArray[i] == 'u' || cArray[i] == 'y') cArray[i] = '0';
            else cArray[i] = '1';
        }
        s = String.valueOf(cArray);
        for (int i = 0; i + len < s.length(); i++) {
            if (s.substring(i, i + len).compareTo(pattern) == 0) ans++;
        }
        return ans;
    }
}
