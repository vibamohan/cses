import java.util.Scanner;

public class MaximumSubarraySum {
  /** Uses Kadane's algorithm (kind of like sliding window)
   * Basic idea is that you have two options, start a new subarray, or continue the current one
   * The only place where starting a new subarray is optimal is if the current sum is negative
   * Beyond this code, if you want to track WHERE the maximum subarray happens, just keep track of start and end in variables
  */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    final int kN = scanner.nextInt();
    long[] values = new long[kN];
    for (int i = 0; i < kN; i++) {
      values[i] = scanner.nextLong();
    }

    long curSum = 0;
    long maxSum = Long.MIN_VALUE;

    for (int i = 0; i < kN; i++) {
      if (curSum < 0) {
        curSum = 0;
      }
      curSum += values[i];
      maxSum = Math.max(maxSum, curSum);
    }

    System.out.println(maxSum);
  }
}
