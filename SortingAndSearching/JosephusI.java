import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class JosephusI {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int kN = scanner.nextInt();
    scanner.close();

    Queue<Integer> q = new LinkedList<>();
    for (int i = 1; i <= kN; i++) q.add(i);

    boolean removing = false;
    while (!q.isEmpty()) {
      int cur = q.poll();
      if (removing) {
        System.out.print(cur + " ");
      } else {
        q.add(cur);
      }

      removing = !removing;
    }

    System.out.close();
  }
}
