// package IntroductoryProblems;

import java.util.Scanner;

public class Repetitions {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    char curChar = ' ';
    int curCount = 1;
    int maxCount = -1;

    char[] arr = scanner.nextLine().toCharArray();
    for (char i : arr) {
      if (i != curChar) {
        curChar = i;
        curCount = 0;
      }
      curCount++;
      maxCount = Math.max(curCount, maxCount);
    }

    System.out.println(maxCount);
  }
}
