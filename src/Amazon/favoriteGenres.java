package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class favoriteGenres {
    public static void main(String[] args) {
        Map<String, List<String>> userSongs = new HashMap<String, List<String>>() {{
            put("David", new ArrayList<>(Arrays.asList("song1", "song2", "song3", "song4", "song8")));
            put("Emma", new ArrayList<>(Arrays.asList("song5", "song6", "song7")));
        }};
        Map<String, List<String>> songGenres = new HashMap<String, List<String>>() {{
            put("Rock", new ArrayList<>(Arrays.asList("song1", "song3")));
            put("Dubstep", new ArrayList<>(Arrays.asList("song7")));
            put("Techno", new ArrayList<>(Arrays.asList("song2", "song4")));
            put("Pop", new ArrayList<>(Arrays.asList("song5", "song6")));
            put("Jazz", new ArrayList<>(Arrays.asList("song8", "song9")));
        }};
        Map<String, List<String>> emptyGenres = new HashMap<>();
        Map<String, List<String>> ans = favoriteGenre(userSongs, emptyGenres);
        for (String s : ans.keySet())
            System.out.println(s + "->" + ans.get(s).toString());
    }

    public static Map<String, List<String>> favoriteGenre(Map<String, List<String>> userSongs, Map<String, List<String>> songGenres) {
        Map<String, List<String>> ans = new HashMap<>();
        Map<String, String> songToGenre = new HashMap<>(); //song -> genre
        for (String genre : songGenres.keySet()) {
            List<String> songs = songGenres.get(genre);
            for (String song : songs) {
                songToGenre.put(song, genre);
            }
        }
        Map<String, Integer> count;
        int max;
        for (String user : userSongs.keySet()) {
            count = new HashMap();
            max = 0;
            ans.put(user, new ArrayList());
            List<String> songs = userSongs.get(user);
            for (String song : songs) {
                if (!songToGenre.containsKey(song)) continue;
                String genre = songToGenre.get(song);
                if (genre == null) continue;
                int c = count.getOrDefault(genre, 0) + 1;
                count.put(genre, c);
                max = Math.max(c, max);
            }
            for (String key : count.keySet()) {
                if (count.get(key) == max) {
                    ans.get(user).add(key);
                }
            }
        }
        return ans;
    }
}
