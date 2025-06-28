// package DP;

import java.util.Scanner;

public class CoinCombinations1 {
  static final int MOD = 1_000_000_007;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int kTargetSum = scanner.nextInt();
    int[] coins = new int[n];

    for (int i = 0; i < n; i++) coins[i] = scanner.nextInt();

    long[] numWaysForTarget = new long[kTargetSum + 1];
    numWaysForTarget[0] = 1;
    // This is bottom up iterative dp, start with 0, and use the same recursive relation
    // Think of it as starting at the top of the stack and popping your way down
    for (int sum = 1; sum <= kTargetSum; sum++) {
      for (int coin : coins) {
        if (sum - coin >= 0) {
          numWaysForTarget[sum] = (numWaysForTarget[sum] + numWaysForTarget[sum - coin]) % MOD;
        }
      }
    }

    System.out.println(numWaysForTarget[kTargetSum]);
  }
}
