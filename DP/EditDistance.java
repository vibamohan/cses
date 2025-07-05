import java.util.Scanner;

public class EditDistance {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String start = scanner.next();
    String target = scanner.next();

    int sl = start.length();
    int tl = target.length();

    int[] dp = new int[tl + 1];
    for (int i = 0; i <= tl; i++) {
      dp[i] = i; // base case: if start is empty, we need to insert all characters of target
    }

    for (int i = 1; i <= sl; i++) {
      int leftup = dp[0];
      dp[0] = i;
      for (int j = 1; j <= tl; j++) {
        int tmp = dp[j];
        if (start.charAt(i - 1) == target.charAt(j - 1)) {
          dp[j] = leftup;
        } else {
          dp[j] = Math.min(Math.min(dp[j - 1], dp[j]) + 1, leftup + 1);
        }
        leftup = tmp;
      }
    }

    System.out.println(dp[tl]);
  }
}
