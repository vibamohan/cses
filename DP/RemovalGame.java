import java.util.Scanner;

public class RemovalGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int kN = scanner.nextInt();
        long[] nums = new long[kN];
        long sum = 0;

        for (int i = 0; i < kN; i++) { 
            nums[i] = scanner.nextInt();
            sum += nums[i];
        }

        // dp[i][j] represents max diff between players within range [l...r]

        long[][] dp = new long[nums.length][nums.length];
        // player 1 wants the max difference from the other player
        // like, they should be very low, i should be very high
        // player 2 wants to minimize this difference 
        // to catch up with player 1 (if played optimally)

        // we go left <-
        for (int l = nums.length - 1; l >= 0; l--) {
            // then we go right ->
            // We construct what happens for each window [l, r]
            for (int r = l; r < kN; r++) {
                if (l == r) dp[l][r] = nums[r];
                else {
                    // Recurrence is: max(x[l]-dp[l+1][r],x[r]-dp[l][r-1])
                    // Basically measuring, what is the max difference i can get?
                    dp[l][r] = Math.max(nums[l] - dp[l+1][r], nums[r] - dp[l][r-1]);
                }
            }
        }


        // reconstruct player1 score from average of the sum of all input values, and score1 and score2
        System.out.println((sum+dp[0][kN-1])/2);
    }
}
