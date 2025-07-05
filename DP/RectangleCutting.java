import java.util.Scanner;

public class RectangleCutting {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int kXLength = scanner.nextInt();
    int kYLength = scanner.nextInt();

    // let dp[i][j] be the minimum number of cuts needed to cut a rectangle of size i x j
    int[][] dp = new int[kXLength + 1][kYLength + 1];
    dp[0][0] = 0;
    for (int i = 1; i <= kXLength; i++) {
      for (int j = 1; j <= kYLength; j++) {
        dp[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
      }
    }

    for (int i = 1; i <= kXLength; i++) {
      for (int j = 1; j <= kYLength; j++) {
        if (i == j) dp[i][j] = 0;
        else {
          for (int xCut = 1; xCut < i; xCut++)
            dp[i][j] = Math.min(dp[i][j], dp[xCut][j] + dp[i - xCut][j] + 1);
          for (int yCut = 1; yCut < j; yCut++)
            dp[i][j] = Math.min(dp[i][j], dp[i][yCut] + dp[i][j - yCut] + 1);
        }
      }
    }

    System.out.println(dp[kXLength][kYLength]);
    scanner.close();
  }
}
