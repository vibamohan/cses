import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Playlists {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int kN = scanner.nextInt();

    /**
     * It follows the sliding window pattern, make a map and increase window till it is all unique
     * Then shorten the start when it does not stay unique
     * The maximum length of such a window is your answer 
     */
    int[] songs = new int[kN];
    for (int i = 0; i < kN; i++) {
      songs[i] = scanner.nextInt();
    }
    scanner.close();

    int maxLen = 0;

    int start = 0;
    int end = 0;
    final Map<Integer, Integer> occurences = new HashMap<>();

    for (end = 0; end < kN; end++) {
      int curSong = songs[end];
      occurences.put(curSong, occurences.getOrDefault(curSong, 0) + 1);
      while (occurences.size() < (end - start + 1)) {
        occurences.put(songs[start], occurences.get(songs[start]) - 1);
        if (occurences.get(songs[start]) == 0) {
          occurences.remove(songs[start]);
        }
        start++;
      }

      maxLen = Math.max(maxLen, end - start + 1);
    }

    System.out.println(maxLen);
  }
}
