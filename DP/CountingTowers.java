import java.util.Scanner;

public class CountingTowers {
    final static int MOD = 1000000007;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int kTestCases = scanner.nextInt();

        int[] heights = new int[kTestCases];
        int maxHeight = 0;
        for (int curTestCase = 0; curTestCase < kTestCases; curTestCase++) {
            int kTargetHeight = scanner.nextInt();
            heights[curTestCase] = kTargetHeight;
            maxHeight = Math.max(maxHeight, kTargetHeight);
        }

        // Preprocessing step 
        // a represents the number of ways to build a tower with a straight top
        // b represents the number of ways to build a tower with a jagged top
        long[] a = new long[maxHeight + 1];
        long[] b = new long[maxHeight + 1]; 
        // basically says we only have one way to make a tower with height 1 which makes sense
        a[1] = 1;
        b[1] = 1;

        for (int i = 2; i <= maxHeight; i++) {
            // To get a straight top of height n:
            // Add a 2x1 block or 1x2 block, so 2 total ways
            a[i] = (2 * a[i - 1] + b[i - 1]) % MOD;

            // To get a jagged top of height n:
            // Add a 2x1, 1x2, or 1x1 block (mirrored), so 4 total ways 
            b[i] = (4 * a[i - 1] + b[i - 1]) % MOD;
        }

        // Our dp approach has memoized answers from 1...maxHeight
        // output each answer, this lets us solve o(n) 
        // without unnecssary recomputation between cases
        for (int curTestCase = 0; curTestCase < kTestCases; curTestCase++) {
            int kTargetHeight = heights[curTestCase];
            System.out.println((a[kTargetHeight] + b[kTargetHeight]) % MOD);
        }

    }
    
}
