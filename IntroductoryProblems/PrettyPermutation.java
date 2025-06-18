import java.util.Scanner;

public class PrettyPermutation {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();

    if (n == 1) {
      System.out.println(1);
      return;
    } else if (n < 4) {
      System.out.println("NO SOLUTION");
      return;
    }

    for (int i = 2; i <= n; i += 2) {
      System.out.print(i + " ");
    }

    for (int i = 1; i <= n; i += 2) {
      System.out.print(i + " ");
    }
  }
}
