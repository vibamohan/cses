// package DP;

import java.util.Arrays;
import java.util.Scanner;

public class RemovingDigits {
  public static void main(String... args) {
    Scanner scanner = new Scanner(System.in);
    int kNum = scanner.nextInt();

    int[] dp = new int[kNum + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int sum = 0; sum <= kNum; sum++) {
      for (char c : String.valueOf(sum).toCharArray()) {
        int curDigit = c - '0';
        if (curDigit == 0) continue;
        int curRemaining = sum - curDigit;
        if (curRemaining >= 0 && dp[curRemaining] != Integer.MAX_VALUE) {
          dp[sum] = Math.min(dp[sum], 1 + dp[curRemaining]);
        }
      }
    }

    System.out.println(dp[kNum]);
  }
}
