// package DP;

import java.util.Arrays;
import java.util.Scanner;

public class MinimizingCoins {

  //   public static int minCoinsHelper(int[] coins, int target, Map<Integer, Integer> memo) {
  //     if (target == 0) return 0;
  //     if (target < 0) return Integer.MAX_VALUE;
  //     if (memo.containsKey(target)) return memo.get(target);

  //     int min = Integer.MAX_VALUE;
  //     for (int coin : coins) {
  //       int cur = minCoinsHelper(coins, target - coin, memo);
  //       if (cur != Integer.MAX_VALUE) {
  //         min = Math.min(min, cur + 1);
  //       }
  //     }
  //     memo.put(target, min);
  //     return min;
  //   }
  // works but inefficient, and may even cause overflow errors

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int kTargetSum = scanner.nextInt();
    int[] coins = new int[n];
    for (int i = 0; i < n; i++) coins[i] = scanner.nextInt();

    int[] minCoinsPerSum = new int[kTargetSum + 1];
    Arrays.fill(minCoinsPerSum, Integer.MAX_VALUE);
    minCoinsPerSum[0] = 0;
    for (int i = 1; i <= kTargetSum; i++) {
      for (int coin : coins) {
        if (i - coin >= 0 && minCoinsPerSum[i - coin] != Integer.MAX_VALUE) {
          minCoinsPerSum[i] = Math.min(minCoinsPerSum[i], minCoinsPerSum[i - coin] + 1);
        }
      }
    }

    int ans = minCoinsPerSum[kTargetSum];
    System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
  }
}
