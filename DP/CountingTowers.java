import java.util.Scanner;

public class CountingTowers {
  static final int MOD = 1000000007;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int kTestCases = scanner.nextInt();

    // special case of tiling dp
    int[] heights = new int[kTestCases];
    int maxHeight = 0;
    for (int curTestCase = 0; curTestCase < kTestCases; curTestCase++) {
      int kTargetHeight = scanner.nextInt();
      heights[curTestCase] = kTargetHeight;
      maxHeight = Math.max(maxHeight, kTargetHeight);
    }

    long[] dp = new long[maxHeight + 1];
    dp[0] = 1;
    for (int curH = 1; curH <= maxHeight; curH++) {
      dp[curH] = 0;
      for (int k = 1; k <= curH; k++) {
        dp[curH] = (dp[curH] + dp[curH - k]) % MOD;
      }
    }

    // Our dp approach has memoized answers from 1...maxHeight
    // output each answer, this lets us solve o(n)
    // without unnecssary recomputation between cases
    for (int curTestCase = 0; curTestCase < kTestCases; curTestCase++) {
      int kTargetHeight = heights[curTestCase];
      System.out.println(dp[kTargetHeight] % MOD);
    }
  }
}
