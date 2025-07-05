import java.util.Scanner;

public class MinimalGridPath {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int kN = scanner.nextInt();

    char[][] grid = new char[kN][kN];
    for (int i = 0; i < kN; i++) grid[i] = scanner.next().toCharArray();

    String[][] dp = new String[kN][kN];
    for (int i = 0; i < kN; i++) {
      for (int j = 0; j < kN; j++) {
        dp[i][j] = "";
      }
    }

    // This is a classic dp implementation, storing best at every level.
    // However, storing huge amounts of memory (strings) for every step for larger
    // grids ends up timing out which made me use a dijstrika solution at
    // MinimalGridPathDijkstra.java

    dp[0][0] = String.valueOf(grid[0][0]);
    for (int i = 1; i < kN; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
    for (int j = 1; j < kN; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];

    for (int i = 1; i < kN; i++) {
      for (int j = 1; j < kN; j++) {
        String chooseTop = dp[i - 1][j] + grid[i][j];
        String chooseLeft = dp[i][j - 1] + grid[i][j];
        dp[i][j] = chooseTop.compareTo(chooseLeft) < 0 ? chooseTop : chooseLeft;
      }
    }

    System.out.println(dp[kN - 1][kN - 1]);
  }
}
