import java.util.ArrayList;
import java.util.Scanner;

public class IncreasingSubsequence {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int kN = scanner.nextInt();
    int[] arr = new int[kN];

    for (int i = 0; i < kN; i++) {
      arr[i] = scanner.nextInt();
    }

    // Binary search approach
    ArrayList<Integer> lis = new ArrayList<>();
    for (int i = 0; i < kN; i++) {
      int num = arr[i];
      int left = 0, right = lis.size();
      while (left < right) {
        int mid = (left + right) / 2;
        if (lis.get(mid) < num) {
          left = mid + 1;
        } else {
          right = mid;
        }
      }

      if (left == lis.size()) {
        lis.add(num);
      } else {
        lis.set(left, num);
      }
    }

    System.out.println(lis.size());

    // Too slow, dp is o(n^2)
    // int[] dp = new int[kN];
    // dp[0] = 1;
    // int maxLength = 1;
    // for (int i = 1; i < kN; i++) {
    //     dp[i] = 1; // Each element is a subsequence of length 1
    //     for (int j = 0; j < i; j++) {
    //         if (arr[i] > arr[j]) {
    //             dp[i] = Math.max(dp[i], dp[j] + 1);
    //         }
    //     }
    //     maxLength = Math.max(maxLength, dp[i]);
    // }

    // System.out.println(maxLength);

  }
}
