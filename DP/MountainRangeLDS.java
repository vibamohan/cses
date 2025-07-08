import java.util.Scanner;

public class MountainRangeLDS {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int kMountains = scanner.nextInt();
    int[] mtns = new int[kMountains];
    for (int i = 0; i < kMountains; i++) mtns[i] = scanner.nextInt();
    scanner.close();

    int n = mtns.length;
    int[] left = new int[n];
    int[] right = new int[n];

    // Compute left DP
    for (int i = 1; i < n; i++) {
      if (mtns[i - 1] < mtns[i]) {
        left[i] = 0;
      } else if (mtns[i - 1] < mtns[i - 1 - left[i - 1]]) {
        left[i] = left[i - 1] + 1;
      } else {
        left[i] = 1;
      }
    }

    // Compute right DP
    for (int i = n - 2; i >= 0; i--) {
      if (mtns[i + 1] < mtns[i]) {
        right[i] = 0;
      } else if (i + 1 + right[i + 1] < n && mtns[i + 1] < mtns[i + 1 + right[i + 1]]) {
        right[i] = right[i + 1] + 1;
      } else {
        right[i] = 1;
      }
    }

    // Compute the maximum
    int max = 0;
    for (int i = 0; i < n; i++) {
      max = Math.max(max, 1 + left[i] + right[i]);
    }

    System.out.println(max);
  }
}
