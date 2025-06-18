// package IntroductoryProblems;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MissingNumber {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();

    Set<Integer> hs = new HashSet<>();

    for (int i = 1; i <= n; i++) {
      hs.add(i);
    }

    for (int i = 0; i < n - 1; i++) {
      hs.remove(scanner.nextInt());
    }

    System.out.println(hs.toArray()[0]);
  }
}
