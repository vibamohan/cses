import java.util.Scanner;

public class IncreasingArray {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();

    long lastNum = scanner.nextInt();
    long moves = 0;

    /*
     * Just loops through the array and 
     * adds the difference needed to stay increasing
     */
    for (int i = 0; i < n - 1; i++) {
      long curNum = scanner.nextLong();
      if (curNum < lastNum) {
        moves += lastNum - curNum;
        curNum = lastNum;
      }
      lastNum = curNum;
    }

    scanner.close();
    System.out.println(moves);
  }
}
