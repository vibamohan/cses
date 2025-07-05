import java.util.Scanner;

public class ArrayDescription {
  static final int MOD = 1000000007;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int kArraySize = scanner.nextInt();
    final int kUpperBound = scanner.nextInt();

    int[] vals = new int[kArraySize];
    for (int i = 0; i < kArraySize; i++) vals[i] = scanner.nextInt();

    int[] prev = new int[kUpperBound + 2];

    // init first positions
    if (vals[0] == 0) {
      for (int v = 1; v <= kUpperBound; v++) prev[v] = 1;
    } else {
      prev[vals[0]] = 1;
    }

    // we use prev and cur for space optimization -- we only go as far back as one level
    // and we can overwrite the previous level

    for (int i = 1; i < kArraySize; i++) {
      int[] cur = new int[kUpperBound + 2];
      for (int v = 1; v <= kUpperBound; v++) {
        if (vals[i] == 0 || vals[i] == v) {
          cur[v] = ((prev[v - 1] + prev[v]) % MOD + prev[v + 1]) % MOD;
          // recurrence relation: is that the current value can be the previous value, or the
          // previous value + 1, or the previous value - 1
        }
      }
      prev = cur;
    }

    int finalAns = 0;
    for (int v = 1; v <= kUpperBound; v++) {
      finalAns = (finalAns + prev[v]) % MOD;
    }

    System.out.println(finalAns);
  }
}
