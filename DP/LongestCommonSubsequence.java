import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class LongestCommonSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nLen = Integer.parseInt(st.nextToken());
        int mLen = Integer.parseInt(st.nextToken());

        int[] n = new int[nLen];
        int[] m = new int[mLen];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nLen; i++) n[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < mLen; i++) m[i] = Integer.parseInt(st.nextToken());

        int[][] dp = new int[mLen + 1][nLen + 1];
        // standard bottom up dp approach
        // dp[i][j] -> length of the longest common subsequence of m[0..i-1] and n[0..j-1]
        for (int i = 1; i <= mLen; i++) {
            for (int j = 1; j <= nLen; j++) {
                if (m[i - 1] == n[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[mLen][nLen]); // final length of lcs

        // print out example of such an lcs
        List<Integer> lcs = new ArrayList<>();
        int i = mLen, j = nLen;
        while (i > 0 && j > 0) {
            if (m[i - 1] == n[j - 1]) {
                lcs.add(m[i - 1]);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        Collections.reverse(lcs);

        for (int k = 0; k < lcs.size(); k++) {
            System.out.print(lcs.get(k));
            if (k < lcs.size() - 1) System.out.print(" ");
        }
        System.out.println();
    }
}
