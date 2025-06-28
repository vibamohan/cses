import java.util.Scanner;

public class SlidingWindowXOR {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    /** Standard sliding window pattern Keep expanding and summing till you exceed window */
    int n = sc.nextInt();
    int k = sc.nextInt();
    int x = sc.nextInt();
    int a = sc.nextInt();
    int b = sc.nextInt();
    int c = sc.nextInt();

    // describes the orders of setting each bit of a standard integer
    int[] lastSeen = new int[32];

    long result = 0;
    int ptr = 0;

    for (int i = 0; i < n; i++) {

      for (int bit = 0; bit < 32; bit++) {
        if (((x >> bit) & 1) == 1) {
          lastSeen[bit] = bit;
        }
      }

      if (i >= k - 1) {
        long or = 0;
        for (int bt = 0; bt < 32; bt++) {
          if (lastSeen[bt] >= i - k + 1) {
            or |= (1 << bt);
          }
        }
        result ^= or;
      }

      ptr = (ptr + 1) % k;
      x = (a * x + b) % c;
    }

    System.out.println(result);
  }
}
