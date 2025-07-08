import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class MountainRange {
  public static void main(String[] args) {

    //////////  THIS DOES NOT WORK BECAUSE IT ONLY CHECKS THE CURRENT MOUNTAIN AGAINST THE LAST
    // MOUNTAIN IN THE STACK
    //////////  HAVE UPDATED IN     /MountainRangeLDS.java
    ///         // Monotonic Stack Problem
    /// keeping this code here for reference
    // maintain a stack of mountains that are in decreasing order of height
    // for each mountain, check if it can glide from the last mountain in the stack
    // if it can, then update the dp array with the glide length from that mtn
    Scanner scanner = new Scanner(System.in);
    final int kMountains = scanner.nextInt();
    int[] mtns = new int[kMountains];
    for (int i = 0; i < kMountains; i++) mtns[i] = scanner.nextInt();
    Stack<Integer> stack = new Stack<>();
    int[] dp = new int[kMountains];
    Arrays.fill(dp, 1);
    int longestGlide = 1;
    for (int i = kMountains - 1; i >= 0; i--) {
      // remove all mountains that are lower than the current mountain
      while (!stack.empty() && mtns[stack.peek()] < mtns[i]) {
        stack.pop();
      }
      // if the stack is not empty, then the current mountain can glide from the last mountain in
      // the stack
      if (!stack.empty()) dp[i] = dp[stack.peek()] + 1;
      else dp[i] = 1;

      longestGlide = Math.max(dp[i], longestGlide);
      stack.push(i);
    }
    System.out.println(longestGlide);
  }
}
