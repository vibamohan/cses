import java.util.Scanner;

public class TwoKnights {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int k = 1; k <= n; k++) {
            int totalWays = (k * k) * (k * k - 1);
            int attackWays = 4 * (k - 1) * (k - 2);
            System.out.println(totalWays - attackWays);
        }

        // Visualize the board, you have n^2 options to place the first knight, and n^2 - 1 for the other
        // Following this, we have the attackways where since a knight moves 1 in one direction, and 2 in the other
        // we can multiply this by 4, due to the 4 ways you can attack in a 3x2, and 2x3 configuration ! 
        // This shows you don't need dfs and can derive a smart math formula for questions like thsi
    }
}
