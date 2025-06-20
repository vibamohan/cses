import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class JosephusII {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int kN = scanner.nextInt();
    final int kSkip = scanner.nextInt() + 1;
    scanner.close();

    Queue<Integer> q = new LinkedList<>();
    for (int i = 1; i <= kN; i++) q.add(i);

    while (!q.isEmpty()) {
      for (int i = 1; i < kSkip; i++) {
        q.add(q.poll());
      }
      System.out.print(q.poll() + " ");
    }
  }
}
