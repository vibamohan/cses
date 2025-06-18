// package IntroductoryProblems;

import java.util.Scanner;

public class WeirdAlgorithm {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();

    int curNum = n;
    System.out.print(curNum + " ");
    while (curNum != 1) {
      if (curNum % 2 == 0) {
        curNum = curNum / 2;
      } else {
        curNum = curNum * 3 + 1;
      }

      System.out.print(curNum);

      if (curNum != 1) {
        System.out.print(" ");
      }
    }
  }
}
