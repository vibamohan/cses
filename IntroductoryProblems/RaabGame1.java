import java.util.*;

public class RaabGame1 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int t = scanner.nextInt();
    for (int testNum = 0; testNum < t; testNum++) {
      int n = scanner.nextInt();
      int a = scanner.nextInt();
      int b = scanner.nextInt();

      if (a + b != n && !(a == 0 && b == 0)) {
        System.out.println("NO");
        continue;
      }

      List<Integer> person1 = new ArrayList<>();
      List<Integer> person2 = new ArrayList<>();

      // All ties case
      if (a == 0 && b == 0) {
        for (int i = 1; i <= n; i++) {
          person1.add(i);
          person2.add(i);
        }
      } else {
        // a + b == n
        for (int i = 1; i <= n; i++) {
          person1.add(i);
        }
        for (int i = b + 1; i <= n; i++) {
          person2.add(i);
        }
        for (int i = 1; i <= b; i++) {
          person2.add(i);
        }
      }

      System.out.println("YES");
      for (int i = 0; i < n; i++) {
        System.out.print(person1.get(i));
        if (i < n - 1) System.out.print(" ");
      }
      System.out.println();
      for (int i = 0; i < n; i++) {
        System.out.print(person2.get(i));
        if (i < n - 1) System.out.print(" ");
      }
      System.out.println();
    }
  }
}
