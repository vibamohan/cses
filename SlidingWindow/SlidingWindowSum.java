import java.util.Scanner;

public class SlidingWindowSum {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    /** Standard sliding window pattern Keep expanding and summing till you exceed window */
    int n = sc.nextInt();
    int k = sc.nextInt();
    long x = sc.nextLong();
    long a = sc.nextLong();
    long b = sc.nextLong();
    long c = sc.nextLong();

    long[] window = new long[k];
    long windowSum = 0;
    long result = 0;
    int ptr = 0;

    for (int i = 0; i < n; i++) {
      windowSum += x;
      window[ptr] = x;

      if (i >= k - 1) {
        result ^= windowSum;
        windowSum -= window[(ptr + 1) % k];
      }

      ptr = (ptr + 1) % k;
      x = (a * x + b) % c;
    }

    System.out.println(result);
  }
}
