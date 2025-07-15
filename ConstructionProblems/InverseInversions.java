// package ConstructionProblems;

import java.util.ArrayList;
import java.util.Scanner;

public class InverseInversions {
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long k = scanner.nextLong();

        long largestRemainingNumber = n;
        long smallestRemainingNumber = 1;

        for (int i = n - 1; i >= 0; i--) {
            if (i <= k) {
                System.out.println(largestRemainingNumber--);
                k -= i;
            } else {
                System.out.println(smallestRemainingNumber++);
            }
        }
    }
}
