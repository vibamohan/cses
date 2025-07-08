import java.util.Scanner;

public class TwoSets2 {
  public static final int MOD = 1_000_000_007;

  public static void main(String[] args) {
    final Scanner scanner = new Scanner(System.in);
    int kN = scanner.nextInt();
    scanner.close();

    int kTotalSum = kN * (kN + 1) / 2;
    int[][] dp = new int[kN + 1][kTotalSum + 1];
    dp[0][0] = 1;
    if (kTotalSum % 2 != 0) {
      System.out.println(0);
      return;
    }
    // dp[i][j] = what num of sets can make that sum with nums 0...i

    for (int target = 0; target <= kTotalSum / 2; target++) {
      for (int used = 1; used <= kN; used++) {
        dp[used][target] = dp[used - 1][target];
        if (target - used >= 0) {
          dp[used][target] = (dp[used - 1][target] + dp[used - 1][target - used]) % MOD;
          // ways = (not include that number keeping the sum same) or (include the number (decrease
          // sum by i for deficit))

        }
      }
    }

    // NOTE: https://www.geeksforgeeks.org/dsa/modular-division/
    // Dividing a number by 2 then modding is NOT what we want
    // Instead MULTIPLY by MODULAR INVERSE of 2, then MOD
    // Mod inverse of two is 500000004
    // learn about how to do it later :>

    System.out.println((int) ((long) dp[kN][kTotalSum / 2] * 500000004 % MOD));
  }
}
