import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoneySums {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] coins = new int[n];
    int totalSum = 0;

    for (int i = 0; i < n; i++) {
      coins[i] = scanner.nextInt();
      totalSum += coins[i];
    }
    scanner.close();

    // dp[i] represents if that sum can be formed
    boolean[] dp = new boolean[totalSum + 1];
    dp[0] = true; // we can always make 0 sum by including 0 coins

    for (int i = 0; i < coins.length; i++) {
      int curCoin = coins[i];
      for (int j = totalSum; j >= 0; j--) {
        if (dp[j] && (j + curCoin <= totalSum)) {
          dp[j + curCoin] = true;
        }
      }
    }

    List<Integer> allPossibleSums = new ArrayList<>();
    int totalPossible = 0;
    for (int i = 1; i < dp.length; i++) {
      boolean isCurrentSumPossible = dp[i];
      if (isCurrentSumPossible) {
        totalPossible++;
        allPossibleSums.add(i);
      }
    }

    System.out.println(totalPossible);
    for (int i = 0; i < allPossibleSums.size(); i++) {
      System.out.print(allPossibleSums.get(i) + ((i == allPossibleSums.size() - 1) ? "" : " "));
    }
  }
}
