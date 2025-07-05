// package DP;

import java.util.Scanner;

public class GridPaths1 {
  static final int MOD = 1_000_000_007;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int nDim = scanner.nextInt();

    char[][] grid = new char[nDim][nDim];
    for (int i = 0; i < nDim; i++) {
      grid[i] = scanner.next().toCharArray();
    }

    int[][] memo = new int[nDim][nDim];
    memo[0][0] = 0;

    for (int r = 0; r < nDim; r++) {
      for (int c = 0; c < nDim; c++) {
        if (grid[r][c] == '*') {
          memo[r][c] = 0;
        } else if (r == 0 && c == 0) {
          memo[r][c] = 1;
        } else {
          memo[r][c] = ((r > 0 ? memo[r - 1][c] : 0) + (c > 0 ? memo[r][c - 1] : 0)) % MOD;
        }
      }
    }

    System.out.println(memo[nDim - 1][nDim - 1]);
  }
}
