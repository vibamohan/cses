import java.util.Arrays;
import java.util.Scanner;

public class StickLengths {
  public static void main(String[] args) {
    /**
     * Find median, then check deviations from median 
     */
    final Scanner scanner = new Scanner(System.in);
    int kN = scanner.nextInt();

    int[] sticks = new int[kN];
    for (int i = 0; i < kN; i++) {
      sticks[i] = scanner.nextInt();
    }

    Arrays.sort(sticks);
    final int median = sticks[kN / 2];

    long totalCost = 0;
    for (int i = 0; i < kN; i++) {
      totalCost += Math.abs(sticks[i] - median);
    }

    System.out.println(totalCost);
  }
}
