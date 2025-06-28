// package DP;

import java.util.Scanner;

public class CoinCombinations2 {
  static final int MOD = 1_000_000_007;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int kTargetSum = scanner.nextInt();
    int[] coins = new int[n];

    for (int i = 0; i < n; i++) coins[i] = scanner.nextInt();

    long[] numWaysForTarget = new long[kTargetSum + 1];
    numWaysForTarget[0] = 1;

    for (int coin : coins) {
      for (int sum = coin; sum <= kTargetSum; sum++) {
        if (sum - coin >= 0) {
          numWaysForTarget[sum] = (numWaysForTarget[sum] + numWaysForTarget[sum - coin]) % MOD;
        }
      }
    }

    System.out.println(numWaysForTarget[kTargetSum]);
  }
}
