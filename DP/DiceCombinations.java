import java.util.Scanner;

public class DiceCombinations {
  static final int MOD = 1_000_000_007;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int kTargetSum = scanner.nextInt();
    long[] numWaysForTarget = new long[kTargetSum + 1];
    numWaysForTarget[0] = 1;
    // This is bottom up iterative dp, start with 0, and use the same recursive relation
    // Think of it as starting at the top of the stack and popping your way down
    for (int sum = 1; sum <= kTargetSum; sum++) {
      for (int dice = 1; dice <= 6; dice++) {
        if (sum - dice >= 0) {
          numWaysForTarget[sum] = (numWaysForTarget[sum] + numWaysForTarget[sum - dice]) % MOD;
        }
      }
    }

    System.out.println(numWaysForTarget[kTargetSum]);
  }
  // Initially tried a recursive solution, but recursion is less efficient than iterative.
  // Tried optimising with replacing HashMap memo (more memory, less efficient even though its o(1))
  // with array, but it wasn't enough
  //   public static void main(String[] args) {
  //     Scanner scanner = new Scanner(System.in);
  //     int kTargetSum = scanner.nextInt();
  //     long[] numWaysForTarget = new long[kTargetSum + 1];
  //     Arrays.fill(numWaysForTarget, -1); // Sentinel value
  //     long combinations = combinationsHelper(kTargetSum, numWaysForTarget);
  //     System.out.println(combinations);
  //   }

  //   private static long combinationsHelper(int kTargetSum, long[] numWaysForTarget) {
  //     if (kTargetSum == 0) return 1;
  //     if (kTargetSum < 0) return 0;

  //     if (numWaysForTarget[kTargetSum] != -1) {
  //       return numWaysForTarget[kTargetSum];
  //     }

  //     long ans = 0;
  //     for (int i = 1; i <= 6; i++) {
  //       ans = (ans + combinationsHelper(kTargetSum - i, numWaysForTarget)) % MOD;
  //     }

  //     numWaysForTarget[kTargetSum] = ans;
  //     return ans;
  //   }
}
