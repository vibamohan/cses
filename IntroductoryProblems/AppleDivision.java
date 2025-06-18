import java.util.Scanner;

public class AppleDivision {
  /**
   * This is a mixture of a sort of greedy way and just going through subsets We make 2 choices, to
   * keep the element chosen in the first set or the second set, then advance the index pointer We
   * choose the minimum of the 2 at every step, so this makes it so that at the end of the
   * recursion, the minimum difference occrus
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    long[] apples = new long[n];
    for (int i = 0; i < n; i++) apples[i] = scanner.nextLong();

    System.out.println(appleDivide(apples, 0, 0, 0));
  }

  static long appleDivide(long[] apples, int ind, long s1, long s2) {
    if (ind == apples.length) {
      return Math.abs(s2 - s1);
    }

    long choose = appleDivide(apples, ind + 1, (long) s1 + apples[ind], s2);
    long exclude = appleDivide(apples, ind + 1, s1, (long) s2 + apples[ind]);

    return Math.min(choose, exclude);
  }
}
