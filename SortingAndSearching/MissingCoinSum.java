import java.util.Arrays;
import java.util.Scanner;

public class MissingCoinSum {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int kNumCoins = scanner.nextInt();
    int[] coins = new int[kNumCoins];

    for (int i = 0; i < kNumCoins; i++) {
      coins[i] = scanner.nextInt();
    }

    /** IDK */
    Arrays.sort(coins);
    System.out.println(Arrays.toString(coins));

    int achievableSum = 0;
    for (int i = 0; i < kNumCoins; i++) {
      int curSum = achievableSum + 1;
      if (coins[i] > curSum) {
        System.out.println(curSum + 1);
        return;
      }
      achievableSum += i;
    }

    System.out.println(achievableSum + 1);
  }
}
