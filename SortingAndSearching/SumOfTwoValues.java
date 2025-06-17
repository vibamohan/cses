import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SumOfTwoValues {
  /**
   * Intuitively, if there is a complement later in the array, it should be found over the course of
   * the loop, since you need 2 numbers to sum to a target Maintain a map of where every number is
   * (duplication doesn't matter as any working solution is accepted) If the complement to current
   * input is found in the map, just output that and return If we cannot do this for the entire
   * loop, say it is IMPOSSIBLE
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int kArraySize = scanner.nextInt();
    final int kTarget = scanner.nextInt();

    Map<Integer, Integer> complements = new HashMap<>();
    for (int i = 0; i < kArraySize; i++) {
      int curNum = scanner.nextInt();
      int curComplement = kTarget - curNum;
      if (complements.containsKey(curComplement)) {
        System.out.println(complements.get(curComplement) + " " + (i + 1));
        return;
      }
      complements.put(curNum, i + 1);
    }

    System.out.println("IMPOSSIBLE");
  }
}
