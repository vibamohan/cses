import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

record Interval(int start, int end) {}
;

// class Interval {
//     int start;
//     int end;

//     public Interval(int start, int end) {
//         this.start = start;
//         this.end = end;
//     }

//     public int start() {
//         return start;
//     }

//     public int end() {
//         return end;
//     }
// }

public class MovieFestivals {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    Interval[] movieShowings = new Interval[n];

    /**
     * Sort movies by end, then skip the movies that overlap with the next start
     */
    for (int i = 0; i < n; i++) {
      movieShowings[i] = new Interval(scanner.nextInt(), scanner.nextInt());
    }

    Arrays.sort(
        movieShowings,
        new Comparator<Interval>() {
          public int compare(Interval i1, Interval i2) {
            return Integer.compare(i1.end(), i2.end());
          }
        });

    int watched = 0;
    int lastEnd = 0;

    for (int i = 0; i < n; i++) {
      if (movieShowings[i].start() >= lastEnd) {
        watched++;
        lastEnd = movieShowings[i].end();
      }
    }

    System.out.println(watched);
  }
}
